package net.remaster147.remastercore.item

import net.minecraft.item.itemgroup.ItemGroup

open class Item(open val settings: ItemSettingBuilder) {
    class ItemSettingBuilder {
        var maxCount: Int = 64
        var maxDamage: Int = 0
        var itemGroup: ItemGroup = ItemGroup.MISC

        fun setMaxCount(maxCount: Int): ItemSettingBuilder {
            this.maxCount = maxCount
            return this
        }

        fun setMaxDamage(maxDamage: Int): ItemSettingBuilder {
            this.maxDamage = maxDamage
            return this
        }

        fun setItemGroup(itemGroup: ItemGroup): ItemSettingBuilder {
            this.itemGroup = itemGroup
            return this
        }
    }
}