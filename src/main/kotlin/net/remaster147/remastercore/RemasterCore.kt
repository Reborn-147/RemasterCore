package net.remaster147.remastercore

import net.fabricmc.api.ModInitializer
import net.minecraft.block.Block
import net.minecraft.utils.Identifier
import net.minecraft.world.chunk.ChunkNibbleArray
import net.remaster147.remastercore.block.SampleBlock
import net.remaster147.remastercore.event.EventListener
import net.remaster147.remastercore.event.EventManager
import net.remaster147.remastercore.event.EventPriority
import net.remaster147.remastercore.registry.BlockRegistry

object RemasterCore : ModInitializer {
    override fun onInitialize() {
        BlockRegistry.register(Identifier("samplemod", "sampleblock"), SampleBlock())

        BlockRegistry.registerAll()
    }
}