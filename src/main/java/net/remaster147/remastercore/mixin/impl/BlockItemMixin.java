package net.remaster147.remastercore.mixin.impl;

import net.remaster147.remastercore.mixin.IBlockItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(net.minecraft.item.BlockItem.class)
public class BlockItemMixin implements IBlockItem {
    @Shadow private int blockItemId;

    @Override
    public int remasterCore$getBlockItemId() {
        return blockItemId;
    }

    @Override
    public void remasterCore$setBlockItemId(int blockItemId) {
        this.blockItemId = blockItemId;
    }
}
