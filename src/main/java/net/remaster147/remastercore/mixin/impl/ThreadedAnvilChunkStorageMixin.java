package net.remaster147.remastercore.mixin.impl;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkNibbleArray;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.ThreadedAnvilChunkStorage;
import net.remaster147.remastercore.mixin.IChunkSection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.logging.Logger;

@Mixin(ThreadedAnvilChunkStorage.class)
public class ThreadedAnvilChunkStorageMixin {
    @Redirect(method = "putChunk", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/chunk/Chunk;getBlockStorage()[Lnet/minecraft/world/chunk/ChunkSection;"))
    private ChunkSection[] getBlockStorage(Chunk instance) {
        return new ChunkSection[0];
    }

    @Redirect(method = "putChunk", at = @At(value = "INVOKE", target = "Lnet/minecraft/nbt/NbtCompound;put(Ljava/lang/String;Lnet/minecraft/nbt/NbtElement;)V", ordinal = 0, shift = At.Shift.BEFORE))
    private void putChunk(Chunk chunk, World world, NbtCompound nbt) {
        ChunkSection[] sections = chunk.getBlockStorage();
        NbtList var5 = new NbtList("Sections");

        for (ChunkSection var10 : sections) {
            if (var10 != null) {
                IChunkSection injectSection = (IChunkSection) var10;

                NbtCompound var11 = new NbtCompound();
                var11.putByte("Y", (byte)(var10.getYOffset() >> 4 & 0xFF));
                var11.putIntArray("Blocks", injectSection.remasterCore$getBlocks());
                if (var10.method_3944() != null) {
                    var11.putByteArray("Add", var10.method_3944().bytes);
                }

                var11.putByteArray("Data", var10.getBlockData().bytes);
                var11.putByteArray("BlockLight", var10.getBlockLight().bytes);
                if (!world.dimension.hasNoSkylight) {
                    var11.putByteArray("SkyLight", var10.getSkyLight().bytes);
                } else {
                    var11.putByteArray("SkyLight", new byte[var10.getBlockLight().bytes.length]);
                }

                var5.method_1217(var11);
            }
        }

        nbt.put("Sections", var5);
    }

    //clear chunk section put
    @Redirect(method = "putChunk", at = @At(value = "INVOKE", target = "Lnet/minecraft/nbt/NbtCompound;put(Ljava/lang/String;Lnet/minecraft/nbt/NbtElement;)V", ordinal = 0))
    private void cancelClearChunk(NbtCompound instance, String nbt, NbtElement nbtElement) {
        System.out.println("Cancel clear chunk");
    }

    @Redirect(method = "getChunk", at = @At(value = "INVOKE", target = "Lnet/minecraft/nbt/NbtCompound;getList(Ljava/lang/String;)Lnet/minecraft/nbt/NbtList;", ordinal = 0))
    private NbtList resetNbtList(NbtCompound instance, String s) {
        return new NbtList();
    }

    //cancel chunk section
    @Redirect(method = "getChunk", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/chunk/Chunk;setLevelChunkSections([Lnet/minecraft/world/chunk/ChunkSection;)V"))
    private void cancelChunk(Chunk instance, ChunkSection[] sections) {
        System.out.println("Cancel clear chunk");
    }

    @Inject(method = "getChunk", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/chunk/Chunk;setLevelChunkSections([Lnet/minecraft/world/chunk/ChunkSection;)V", shift = At.Shift.BEFORE))
    private void setLevelChunkSections(World world, NbtCompound nbt, CallbackInfoReturnable<Chunk> cir) {
        int var3 = nbt.getInt("xPos");
        int var4 = nbt.getInt("zPos");
        Chunk var5 = new Chunk(world, var3, var4);

        NbtList var6 = nbt.getList("Sections");
        byte var7 = 16;
        ChunkSection[] var8 = new ChunkSection[var7];
        boolean var9 = !world.dimension.hasNoSkylight;

        for (int var10 = 0; var10 < var6.size(); var10++) {
            NbtCompound var11 = (NbtCompound)var6.method_1218(var10);
            byte var12 = var11.getByte("Y");
            ChunkSection var13 = new ChunkSection(var12 << 4, var9);
            IChunkSection injectSection = (IChunkSection) var13;
            injectSection.remasterCore$setBlocks(var11.getIntArray("Blocks"));
            if (var11.contains("Add")) {
                var13.method_3928(new ChunkNibbleArray(var11.getByteArray("Add"), 4));
            }

            var13.setBlockData(new ChunkNibbleArray(var11.getByteArray("Data"), 4));
            var13.setBlockLight(new ChunkNibbleArray(var11.getByteArray("BlockLight"), 4));
            if (var9) {
                var13.setSkyLight(new ChunkNibbleArray(var11.getByteArray("SkyLight"), 4));
            }

            var13.calculateCounts();
            var8[var12] = var13;
        }

        var5.setLevelChunkSections(var8);
    }

}
