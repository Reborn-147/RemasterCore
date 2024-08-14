package net.remaster147.remastercore

import net.fabricmc.api.ModInitializer
import net.remaster147.remastercore.registry.BlockRegistry

object RemasterCore : ModInitializer {
    override fun onInitialize() {
        BlockRegistry.registerAll()
    }
}