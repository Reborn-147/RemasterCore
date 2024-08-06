package net.remaster147.remastercore.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.remaster147.remastercore.registry.BlockRegistry;
import net.minecraft.utils.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;


@Mixin(Block.class)
abstract
class BlockMixin {
    @Shadow private String translationKey;

    @Shadow @Mutable @Final public static Block[] BLOCKS = new Block[Integer.MAX_VALUE];
    @Shadow @Mutable @Final public static final boolean[] field_493 = new boolean[Integer.MAX_VALUE];
    @Shadow @Mutable @Final public static final int[] field_494 = new int[Integer.MAX_VALUE];
    @Shadow @Mutable @Final public static final boolean[] field_495 = new boolean[Integer.MAX_VALUE];
    @Shadow @Mutable @Final public static final int[] field_496 = new int[Integer.MAX_VALUE];
    @Shadow @Mutable @Final public static final boolean[] field_497 = new boolean[Integer.MAX_VALUE];
    @Shadow @Mutable @Final public static boolean[] field_498 = new boolean[Integer.MAX_VALUE];

    @Mutable
    @Shadow @Final public Material material;

    @Shadow protected abstract void setBoundingBox(float minX, float minY, float minZ, float maxX, float maxY, float maxZ);

    @Unique
    public Map<Identifier, Block> getBlocks() {
        return BlockRegistry.INSTANCE.getAll();
    }

    @Unique
    public Map<Identifier, Block> getVanillaBlocks() {
        return BlockRegistry.INSTANCE.getBlocksByNamespace("minecraft");
    }

    @Inject(method = "<init>(ILnet/minecraft/block/material/Material;)V", at = @At(value = "FIELD", shift = At.Shift.BEFORE, ordinal = 1, target = "Lnet/minecraft/block/Block;BLOCKS:[Lnet/minecraft/block/Block;"), cancellable = true)
    protected void moveBlockRegistry(int i, Material material, CallbackInfo ci) {
        this.material = material;
        this.setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);

        BlockRegistry.INSTANCE.register(new Identifier("minecraft", this.translationKey), (Block) (Object)this);

        ci.cancel();
    }

    @Inject(method = "isSolid", at = @At("HEAD"))
    private static void isSolid(int id, CallbackInfoReturnable<Boolean> cir) {

    }
}