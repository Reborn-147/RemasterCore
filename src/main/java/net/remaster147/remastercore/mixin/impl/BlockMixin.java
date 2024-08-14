package net.remaster147.remastercore.mixin.impl;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.utils.Identifier;
import net.remaster147.remastercore.mixin.IBlock;
import net.remaster147.remastercore.registry.BlockRegistry;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Block.class)
abstract class BlockMixin implements IBlock {
    @Shadow private String translationKey;

    @Shadow @Mutable @Final public static final boolean[] field_493 = new boolean[Short.MAX_VALUE];
    @Shadow @Mutable @Final public static final int[] field_494 = new int[Short.MAX_VALUE];
    @Shadow @Mutable @Final public static final boolean[] field_495 = new boolean[Short.MAX_VALUE];
    @Shadow @Mutable @Final public static final int[] field_496 = new int[Short.MAX_VALUE];
    @Shadow @Mutable @Final public static final boolean[] field_497 = new boolean[Short.MAX_VALUE];
    @Shadow @Mutable @Final public static boolean[] field_498 = new boolean[Short.MAX_VALUE];

    @Shadow public abstract String getTranslationKey();

    @Shadow @Mutable @Final public Material material;

    @Shadow @Mutable @Final public int id;

    @Shadow protected abstract void setBoundingBox(float minX, float minY, float minZ, float maxX, float maxY, float maxZ);


    /*@Inject(method = "<init>(ILnet/minecraft/block/material/Material;)V", at = @At(value = "FIELD", target = "Lnet/minecraft/block/Block;material:Lnet/minecraft/block/material/Material;", shift = At.Shift.BEFORE))
    protected void initBlock(int i, Material material, CallbackInfo ci) {
        this.material = material;

        if (getTranslationKey() != null) {
            BlockRegistry.INSTANCE.register(new Identifier("minecraft", getTranslationKey()), (Block) (Object) this);
        }
        else {
            if(material == Material.PORTAL) {
                BlockRegistry.INSTANCE.register(new Identifier("minecraft", "portal"), (Block) (Object) this);
            }
        }
    }*/

    /*@Inject(method = "isSolid", at = @At("HEAD"))
    private static void isSolid(int id, CallbackInfoReturnable<Boolean> cir) {

    }*/
}