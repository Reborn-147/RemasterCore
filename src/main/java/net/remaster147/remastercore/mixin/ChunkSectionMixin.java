package net.remaster147.remastercore.mixin;

import net.minecraft.utils.Identifier;
import net.minecraft.world.chunk.ChunkNibbleArray;
import net.minecraft.world.chunk.ChunkSection;
import net.remaster147.remastercore.block.BlockState;
import net.remaster147.remastercore.registry.BlockRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.Serializable;

@Mixin(ChunkSection.class)
public abstract class ChunkSectionMixin {
    @Shadow @Mutable private ChunkNibbleArray skyLight;

    @Shadow private ChunkNibbleArray field_4748;

    @Shadow public abstract int getYOffset();

    @Shadow private byte[] blocks;
    @Unique
    private BlockState[] blockStates;

    @Inject(method = "<init>(IZ)V", at = @At(value = "FIELD", target = "Lnet/minecraft/world/chunk/ChunkSection;yOffset:I", ordinal = 0, shift = At.Shift.AFTER), cancellable = true)
    public void init(int i, boolean bl, CallbackInfo ci) {
        blockStates = new BlockState[Integer.MAX_VALUE];

        if(bl) {
            skyLight = new ChunkNibbleArray(blockStates.length, 4);
        }

        ci.cancel();
    }

    @Unique
    public BlockState getBlock(int x, int y, int z) {
        BlockState blockState = blockStates[y << 8 | z << 4 | x];
        return this.field_4748 != null ? new BlockState(
                BlockRegistry.INSTANCE.get(this.field_4748.get(x, y, z) << 8 | this.blocks[y << 8 | z << 4 | x] & 255 )) : blockState;
    }
}
