package net.remaster147.remastercore

import net.fabricmc.api.ModInitializer
import net.remaster147.remastercore.registry.BlockRegistry
import net.remaster147.remastercore.registry.ItemRegistry

object RemasterCore : ModInitializer {
    override fun onInitialize() {
        BlockRegistry.registerAll()
        ItemRegistry.registerAll()
    }
}