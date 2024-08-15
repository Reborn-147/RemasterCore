package net.remaster147.remastercore.registry

import net.remaster147.remastercore.block.Block
import net.remaster147.remastercore.event.EventListener
import net.remaster147.remastercore.event.EventManager
import net.remaster147.remastercore.event.EventPriority
import net.remaster147.remastercore.event.core.MinecraftPostEvent
import net.remaster147.remastercore.item.BlockItem
import net.remaster147.remastercore.item.Item

object ItemRegistry : Registry<Item>() {
    val items = mutableMapOf<Item, Int>()

    fun registerAll() {
        EventManager.addListener(MinecraftPostEvent::class, EventListener {
            var index = net.minecraft.item.Item.ITEMS.indexOfLast { it != null } + 1

            //Modded Items
            val modItems = registry.entries.map { it.key to it.value }

            modItems.sortedBy { it.first.id }.forEach {
                val settings = it.second.settings

                if(it.second is BlockItem) {
                    println("timing 2")
                    val block = it.second as BlockItem
                    val blockId = BlockRegistry.blocks[block.block] ?: throw IllegalArgumentException("Block not found in registry")

                    object : net.minecraft.item.BlockItem(blockId) {
                        init {
                            maxCount = settings.maxCount
                            maxDamage = settings.maxDamage
                            itemGroup = settings.itemGroup
                            setName(it.first.id)
                        }
                    }

                    items[it.second] = index + 256
                }
                else {
                    net.minecraft.item.Item.ITEMS[index] = object : net.minecraft.item.Item(index) {
                        init {
                            maxCount = settings.maxCount
                            maxDamage = settings.maxDamage
                            itemGroup = settings.itemGroup
                            setName(it.first.id)
                        }
                    }

                    items[it.second] = index
                }

                index++
            }
        }, EventPriority.LOW)
    }
}