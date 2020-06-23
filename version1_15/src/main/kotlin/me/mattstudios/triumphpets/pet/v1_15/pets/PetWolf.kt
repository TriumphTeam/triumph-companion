package me.mattstudios.triumphpets.pet.v1_15.pets

import me.mattstudios.mattcore.MattPlugin
import me.mattstudios.triumphpets.pet.Pet
import me.mattstudios.triumphpets.pet.components.PetInventory
import me.mattstudios.triumphpets.pet.components.PetMemory
import net.minecraft.server.v1_15_R1.EntityHuman
import net.minecraft.server.v1_15_R1.EntityTypes
import net.minecraft.server.v1_15_R1.EntityWolf
import net.minecraft.server.v1_15_R1.EnumHand
import net.minecraft.server.v1_15_R1.World
import org.bukkit.NamespacedKey
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.persistence.PersistentDataType

/**
 * @author Matt
 */
class PetWolf(
        private val plugin: MattPlugin,
        private val petMemory: PetMemory,
        private val petInventory: PetInventory,
        private val owner: Player,
        private val petName: String,
        baby: Boolean,
        world: World
) : EntityWolf(EntityTypes.WOLF, world), Pet {

    private val petConfig = petMemory.petConfig

    private val petCreature = PetCreature(plugin, this, petConfig, petName, owner)

    init {
        // Wolf specific properties
        isTamed = true

        if (baby) {
            age = -24000
            ageLocked = true
        }

        // Adds NBT tag to identify the entity as pet
        // TODO change this later, move to pet creature
        bukkitEntity.persistentDataContainer.set(NamespacedKey(plugin, "pet"), PersistentDataType.BYTE, 1)
    }

    /**
     * TODO level
     */
    override fun getLevel(): Short = 1

    override fun getName() = petName

    /**
     * Gets the bukkit entity of the pet
     */
    override fun getEntity(): Entity = bukkitEntity

    /**
     * Gets the inventory
     */
    override fun getInventory() = petInventory

    /**
     * Gets the pets memory
     */
    override fun getMemory() = petMemory

    /**
     * Gets the pet's owner
     */
    override fun getPetOwner() = owner

    /**
     * Checks if the given player is owner
     */
    override fun isOwner(player: Player) = owner.uniqueId == player.uniqueId

    /**
     * Removes the entity from the world
     */
    override fun remove() = petCreature.remove()

    /**
     * Keeps the name on top of their head
     */
    override fun tick() {
        super.tick()
        petCreature.tick()
    }

    /**
     * Detects the right click on the entity
     */
    override fun a(entity: EntityHuman, enumhand: EnumHand): Boolean {
        if (enumhand == EnumHand.MAIN_HAND && entity.bukkitEntity == owner) {
            if (owner.isSneaking) petCreature.pet() else petInventory.open()
        }

        return super.a(entity, enumhand)
    }

}