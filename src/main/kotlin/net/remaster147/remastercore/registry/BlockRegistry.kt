package net.remaster147.remastercore.registry

import net.minecraft.block.Block
import net.minecraft.utils.Identifier

object BlockRegistry : Registry<Block>() {
    fun getBlocksByNamespace(namespace: String): Map<Identifier, Block> {
        return registry.filter { it.key.namespace == namespace }
    }
}