package net.remaster147.remastercore.registry

import net.minecraft.utils.Identifier
import net.remaster147.remastercore.block.Block
import net.remaster147.remastercore.event.EventListener
import net.remaster147.remastercore.event.EventManager
import net.remaster147.remastercore.event.EventPriority
import net.remaster147.remastercore.event.core.MinecraftPreEvent

object BlockRegistry : Registry<Block>() {
    val blocks = mutableMapOf<Block, Int>()

    fun getBlocksByNamespace(namespace: String): Map<Identifier, Block> {
        return registry.filter { it.key.namespace == namespace }
    }

    fun registerAll() {
        EventManager.addListener(MinecraftPreEvent::class, EventListener {
            var index = net.minecraft.block.Block.BLOCKS.indexOfLast { it != null } + 1

            //Modded Blocks
            val modBlocks = registry.entries.map { it.key to it.value }

            modBlocks.sortedBy { it.first.id }.forEach {
                val settings = it.second.settings

                object : net.minecraft.block.Block(index, settings.material) {
                    init {
                        blastResistance = settings.resistance * 3F
                        hardness = settings.hardness
                        translationKey = it.first.id
                        field_439 = settings.blockLight
                    }
                }

                blocks[it.second] = index

                println("timing 1")

                index++
            }

        }, EventPriority.HIGH)
    }
}