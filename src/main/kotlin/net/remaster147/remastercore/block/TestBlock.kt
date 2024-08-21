package net.remaster147.remastercore.block

import net.minecraft.block.material.Material

class TestBlock : Block(
    BlockSettingsBuilder()
        .setMaterial(Material.STONE)
        .setHardness(1.5f)
        .setResistance(6.0f)
        .setBlockLight(0)
) {
}