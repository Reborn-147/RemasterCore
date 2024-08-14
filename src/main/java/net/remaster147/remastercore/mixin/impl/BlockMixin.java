package net.remaster147.remastercore.mixin.impl;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.remaster147.core.IBlock;
import net.remaster147.remastercore.registry.BlockRegistry;
import net.minecraft.utils.Identifier;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;


@Mixin(Block.class)
abstract class BlockMixin implements IBlock {
    @Shadow private String translationKey;

    @Shadow @Mutable @Final public static final boolean[] field_493 = new boolean[Short.MAX_VALUE];
    @Shadow @Mutable @Final public static final int[] field_494 = new int[Short.MAX_VALUE];
    @Shadow @Mutable @Final public static final boolean[] field_495 = new boolean[Short.MAX_VALUE];
    @Shadow @Mutable @Final public static final int[] field_496 = new int[Short.MAX_VALUE];
    @Shadow @Mutable @Final public static final boolean[] field_497 = new boolean[Short.MAX_VALUE];
    @Shadow @Mutable @Final public static boolean[] field_498 = new boolean[Short.MAX_VALUE];

    @Shadow protected abstract void setBoundingBox(float minX, float minY, float minZ, float maxX, float maxY, float maxZ);

    @Shadow public abstract String getTranslationKey();

    @Shadow @Final public Material material;

    @Unique
    public Map<Identifier, IBlock> getBlocks() {
        return BlockRegistry.INSTANCE.getAll();
    }

    @Unique
    public Map<Identifier, IBlock> getVanillaBlocks() {
        return BlockRegistry.INSTANCE.getBlocksByNamespace("minecraft");
    }

    @Inject(method = "<init>(ILnet/minecraft/block/material/Material;)V", at = @At("TAIL"))
    private void init(CallbackInfo ci) {
        if (getTranslationKey() != null) {
            if(material == Material.PORTAL) {
                BlockRegistry.INSTANCE.register(new Identifier("minecraft", "portal"), this);

                return;
            }
            BlockRegistry.INSTANCE.register(new Identifier("minecraft", getTranslationKey()), this);
        }
    }

    /*@Inject(method = "isSolid", at = @At("HEAD"))
    private static void isSolid(int id, CallbackInfoReturnable<Boolean> cir) {

    }*/
}