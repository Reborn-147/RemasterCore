package net.remaster147.remastercore.block

import net.minecraft.block.material.Material
import net.minecraft.item.itemgroup.ItemGroup
import net.minecraft.utils.Identifier

class SampleBlock : Block(BlockSettingsBuilder().apply {
    setMaterial(Material.STONE)
    setHardness(1.5f)
    setResistance(10.0f)
    setItemGroup(ItemGroup.BUILDING_BLOCKS)
})