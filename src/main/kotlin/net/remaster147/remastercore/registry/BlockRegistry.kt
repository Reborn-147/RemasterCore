package net.remaster147.remastercore.registry

import net.minecraft.block.Block
import net.minecraft.utils.Identifier
import net.remaster147.core.IBlock
import net.remaster147.remastercore.event.EventListener
import net.remaster147.remastercore.event.EventManager
import net.remaster147.remastercore.event.EventPriority
import net.remaster147.remastercore.event.core.MinecraftPostEvent

object BlockRegistry : Registry<IBlock>() {
    fun getBlocksByNamespace(namespace: String): Map<Identifier, IBlock> {
        return registry.filter { it.key.namespace == namespace }
    }

    fun registerAll() {
        EventManager.addListener(MinecraftPostEvent::class, EventListener {
            //Vanilla Blocks
            var index = 0
            registry.filter { it.key.namespace == "minecraft" }.forEach {
                Block.BLOCKS[index] = it.value as Block

                index++
            }

            //Modded Blocks
            registry.entries.sortedBy { it.key.namespace }.forEach {
                Block.BLOCKS[index] = it.value as Block

                index++
            }

        }, EventPriority.HIGH)
    }
}