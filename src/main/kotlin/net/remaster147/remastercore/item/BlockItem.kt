package net.remaster147.remastercore.item

import net.minecraft.utils.Identifier
import net.remaster147.remastercore.block.Block

class BlockItem(val block: Block, override val settings: ItemSettingBuilder) : Item(settings) {
}