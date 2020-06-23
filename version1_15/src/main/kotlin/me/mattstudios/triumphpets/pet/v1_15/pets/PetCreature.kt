package me.mattstudios.triumphpets.pet.v1_15.pets

import me.mattstudios.mattcore.MattPlugin
import me.mattstudios.triumphpets.pet.v1_15.components.HologramEntity
import net.minecraft.server.v1_15_R1.World

/**
 * @author Matt
 */
class PetCreature(
        plugin: MattPlugin,
        world: World
) {

    private var displayName = HologramEntity(plugin, "Foxy", world)

    private var petPetTime = 0L
    private val PET_COOLDOWN = 15


}