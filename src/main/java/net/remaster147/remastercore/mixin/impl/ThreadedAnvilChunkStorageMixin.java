package net.remaster147.remastercore.mixin.impl;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.minecraft.nbt.NbtByteArray;
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
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ThreadedAnvilChunkStorage.class)
public class ThreadedAnvilChunkStorageMixin {
    @Redirect(method = "putChunk", at = @At(value = "INVOKE", target = "Lnet/minecraft/nbt/NbtCompound;putByteArray(Ljava/lang/String;[B)V", ordinal = 0))
    private void putChunk(NbtCompound instance, String value, byte[] bytes, @Local(name = "var10") ChunkSection section) {
        IChunkSection injectSection = (IChunkSection) section;

        if(!section.isEmpty()) {
            instance.putByteArray("Blocks", section.getBlocks());
        }

        instance.putIntArray("Blocks", injectSection.remasterCore$getBlocks());
    }

    @Redirect(method = "getChunk", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/chunk/ChunkSection;setBlocks([B)V"))
    private void getChunk(ChunkSection instance, byte[] bytes, @Local(name = "var11") NbtCompound var11) {
        IChunkSection injectSection = (IChunkSection) instance;

        if(var11.getType() == 7) {
            instance.setBlocks(bytes);
            return;
        }

        injectSection.remasterCore$setBlocks(var11.getIntArray("Blocks"));
    }
}
