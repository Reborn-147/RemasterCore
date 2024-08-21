package net.remaster147.remastercore

import net.fabricmc.api.ModInitializer
import net.minecraft.item.itemgroup.ItemGroup
import net.minecraft.utils.Identifier
import net.remaster147.remastercore.block.TestBlock
import net.remaster147.remastercore.item.BlockItem
import net.remaster147.remastercore.item.Item
import net.remaster147.remastercore.registry.BlockRegistry
import net.remaster147.remastercore.registry.ItemRegistry

object RemasterCore : ModInitializer {
    override fun onInitialize() {
        val block = TestBlock()
        BlockRegistry.register(Identifier("remastercore", "test_block"), block)
        ItemRegistry.register(Identifier("remastercore", "test_block"), BlockItem(block,
            Item.ItemSettingBuilder().setMaxCount(64).setMaxDamage(0).setItemGroup(ItemGroup.BUILDING_BLOCKS)
        ))

        BlockRegistry.registerAll()
        ItemRegistry.registerAll()
    }
}