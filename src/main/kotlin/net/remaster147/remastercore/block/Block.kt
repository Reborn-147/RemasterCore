package net.remaster147.remastercore.block

import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.item.itemgroup.ItemGroup
import net.minecraft.utils.Identifier

abstract class Block(val settings: BlockSettingsBuilder) {
    class BlockSettingsBuilder {
        var material: Material = Material.AIR
        var hardness: Float = 0.0f
        var resistance: Float = 0.0f
        var blockLight: Int = 0
        var itemGroup: ItemGroup = ItemGroup.BUILDING_BLOCKS

        fun setMaterial(material: Material): BlockSettingsBuilder {
            this.material = material
            return this
        }

        fun setHardness(hardness: Float): BlockSettingsBuilder {
            this.hardness = hardness
            return this
        }

        fun setResistance(resistance: Float): BlockSettingsBuilder {
            this.resistance = resistance
            return this
        }

        fun setItemGroup(itemGroup: ItemGroup): BlockSettingsBuilder {
            this.itemGroup = itemGroup
            return this
        }

        fun setBlockLight(blockLight: Int): BlockSettingsBuilder {
            this.blockLight = blockLight
            return this
        }
    }
}