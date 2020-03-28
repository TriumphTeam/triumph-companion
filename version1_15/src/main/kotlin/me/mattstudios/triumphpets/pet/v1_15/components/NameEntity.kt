package me.mattstudios.triumphpets.pet.v1_15.components

import me.mattstudios.mattcore.MattPlugin
import me.mattstudios.mattcore.utils.MessageUtils.color
import me.mattstudios.triumphpets.pet.components.PetNameEntity
import net.minecraft.server.v1_15_R1.ChatMessage
import net.minecraft.server.v1_15_R1.EntityArmorStand
import net.minecraft.server.v1_15_R1.EntityTypes
import net.minecraft.server.v1_15_R1.World
import org.bukkit.NamespacedKey
import org.bukkit.persistence.PersistentDataType

/**
 * @author Matt
 */
class NameEntity(plugin: MattPlugin, displayName: String, nbtValue: String, world: World) : EntityArmorStand(EntityTypes.ARMOR_STAND, world), PetNameEntity {

    constructor(plugin: MattPlugin, displayName: String, world: World) : this (plugin, displayName, "pet", world)

    init {
        isMarker = true
        customName = ChatMessage(color(displayName))
        customNameVisible = true
        isNoGravity = true
        isInvisible = true

        bukkitEntity.persistentDataContainer.set(NamespacedKey(plugin, nbtValue), PersistentDataType.BYTE, 1)
    }

}