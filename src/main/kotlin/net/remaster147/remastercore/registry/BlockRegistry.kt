package net.remaster147.remastercore.registry

import net.minecraft.entity.Entity
import net.minecraft.utils.Identifier
import net.remaster147.remastercore.block.Block
import net.remaster147.remastercore.event.EventListener
import net.remaster147.remastercore.event.EventManager
import net.remaster147.remastercore.event.EventPriority
import net.remaster147.remastercore.event.core.MinecraftPostEvent
import net.remaster147.remastercore.event.core.MinecraftPreEvent

object BlockRegistry : Registry<Block>() {
    val blocks = mutableMapOf<Block, Int>()

    fun getBlocksByNamespace(namespace: String): Map<Identifier, Block> {
        return registry.filter { it.key.namespace == namespace }
    }

    fun registerAll() {
        EventManager.addListener(MinecraftPreEvent::class, EventListener {
            var index = net.minecraft.block.Block.BLOCKS.indexOfLast { it != null } + 1
            var itemIndex = net.minecraft.item.Item.ITEMS.indexOfLast { it != null } + 1

            //Modded Blocks
            registry.entries.sortedBy { it.key.namespace }.forEach {
                val settings = it.value.settings

                net.minecraft.block.Block.BLOCKS[index] = object : net.minecraft.block.Block(index, settings.material) {
                    init {
                        blastResistance = settings.resistance * 3F
                        hardness = settings.hardness
                        translationKey = it.key.id
                        field_439 = settings.blockLight
                        itemGroup = settings.itemGroup
                    }
                }

                net.minecraft.item.Item.ITEMS[itemIndex] = net.minecraft.item.BlockItem(index - 256)

                println("fuck off")

                blocks[it.value] = index

                index++
                itemIndex++
            }

        }, EventPriority.HIGH)
    }
}