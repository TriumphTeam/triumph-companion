package me.mattstudios.triumphpets.pet.v1_16.components

import me.mattstudios.mattcore.MattPlugin
import me.mattstudios.mattcore.utils.MessageUtils.color
import me.mattstudios.triumphpets.pet.components.NameEntity
import net.minecraft.server.v1_16_R1.ChatMessage
import net.minecraft.server.v1_16_R1.EntityArmorStand
import net.minecraft.server.v1_16_R1.EntityTypes
import net.minecraft.server.v1_16_R1.EnumItemSlot
import net.minecraft.server.v1_16_R1.World
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.craftbukkit.v1_16_R1.inventory.CraftItemStack
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

/**
 * @author Matt
 */
class HologramEntity(plugin: MattPlugin, displayName: String, world: World, nbtValue: String = "pet") : EntityArmorStand(EntityTypes.ARMOR_STAND, world), NameEntity {

    init {
        isMarker = true
        customName = ChatMessage(color(displayName))
        customNameVisible = true
        isNoGravity = true
        isInvisible = true // This is a test

        setEquipment(EnumItemSlot.HEAD, CraftItemStack.asNMSCopy(ItemStack(Material.LEATHER_HELMET)))

        bukkitEntity.persistentDataContainer.set(NamespacedKey(plugin, nbtValue), PersistentDataType.BYTE, 1)
    }

}