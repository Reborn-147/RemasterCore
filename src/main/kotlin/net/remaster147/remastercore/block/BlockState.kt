package net.remaster147.remastercore.block

import net.minecraft.block.Block
import net.minecraft.util.math.Direction

class BlockState(val block: Block) {
    private var light: Int = 0
    private var rotation: Direction = Direction.NORTH

    fun getLight(): Int {
        return light
    }

    fun setLight(light: Int) {
        this.light = light
    }

    fun getRotation(): Direction {
        return rotation
    }

    fun setRotation(rotation: Direction) {
        this.rotation = rotation
    }
}