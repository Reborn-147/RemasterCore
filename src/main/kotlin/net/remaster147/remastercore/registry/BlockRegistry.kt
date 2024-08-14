package net.remaster147.remastercore.registry

import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.utils.Identifier
import net.remaster147.remastercore.event.EventListener
import net.remaster147.remastercore.event.EventManager
import net.remaster147.remastercore.event.EventPriority
import net.remaster147.remastercore.event.core.MinecraftPreEvent
import net.remaster147.remastercore.mixin.IBlock

object BlockRegistry : Registry<Block>() {
    val blocks = mutableMapOf<Block, Int>()

    fun getBlocksByNamespace(namespace: String): Map<Identifier, Block> {
        return registry.filter { it.key.namespace == namespace }
    }

    fun registerAll() {
        EventManager.addListener(MinecraftPreEvent::class, EventListener {
            //Vanilla Blocks
            var index = registry.filter { it.key.namespace == "minecraft" }.count() - 1

            //Modded Blocks
            registry.entries.sortedBy { it.key.namespace }.forEach {
                Block.BLOCKS[index] = it.value

                blocks[it.value] = index

                index++
            }

        }, EventPriority.HIGH)
    }
}