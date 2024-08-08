package net.remaster147.remastercore.mixin.impl;

import net.minecraft.world.chunk.ChunkNibbleArray;
import net.minecraft.world.chunk.ChunkSection;
import net.remaster147.remastercore.block.BlockState;
import net.remaster147.remastercore.mixin.IChunkSection;
import net.remaster147.remastercore.registry.BlockRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChunkSection.class)
public class ChunkSectionMixin implements IChunkSection {
    @Shadow @Mutable private ChunkNibbleArray skyLight;

    @Unique
    private int[] newBlocks;
    @Shadow private ChunkNibbleArray blockData;
    @Shadow private ChunkNibbleArray blockLight;

    @Inject(method = "<init>(IZ)V", at = @At(value = "FIELD", target = "Lnet/minecraft/world/chunk/ChunkSection;yOffset:I", ordinal = 0, shift = At.Shift.AFTER))
    public void init(int i, boolean bl, CallbackInfo ci) {
        newBlocks = new int[4096];
        blockData = new ChunkNibbleArray(this.newBlocks.length, 4);
        blockLight = new ChunkNibbleArray(this.newBlocks.length, 4);

        if (bl) {
            this.skyLight = new ChunkNibbleArray(this.newBlocks.length, 4);
        }
    }

    @Override
    public int[] remasterCore$getBlocks() {
        return newBlocks;
    }

    @Override
    public void remasterCore$setBlocks(int[] blocks) {
        newBlocks = blocks;
    }
}
