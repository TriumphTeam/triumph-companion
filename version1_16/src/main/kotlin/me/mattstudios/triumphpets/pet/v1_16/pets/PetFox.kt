package me.mattstudios.triumphpets.pet.v1_16.pets

import me.mattstudios.mattcore.MattPlugin
import me.mattstudios.triumphpets.pet.Pet
import me.mattstudios.triumphpets.pet.components.PetInventory
import me.mattstudios.triumphpets.pet.components.PetMemory
import net.minecraft.server.v1_16_R1.EntityFox
import net.minecraft.server.v1_16_R1.EntityHuman
import net.minecraft.server.v1_16_R1.EntityTypes
import net.minecraft.server.v1_16_R1.EnumHand
import net.minecraft.server.v1_16_R1.EnumInteractionResult
import net.minecraft.server.v1_16_R1.World
import org.bukkit.NamespacedKey
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.persistence.PersistentDataType


/**
 * @author Matt
 */
class PetFox(
        private val plugin: MattPlugin,
        override val petMemory: PetMemory,
        override val petInventory: PetInventory,
        override val petOwner: Player,
        override val petName: String,
        baby: Boolean,
        type: Type,
        world: World
) : EntityFox(EntityTypes.FOX, world), Pet {

    override val entity: Entity = bukkitEntity
    override val level: Short = 1

    private val petCreature = PetCreature(plugin, this)

    init {
        // Fox specific properties
        foxType = type
        canPickUpLoot = false

        if (baby) {
            age = -24000
            ageLocked = true
        }

        // Adds NBT tag to identify the entity as pet
        // TODO change this later, move to pet creature
        bukkitEntity.persistentDataContainer.set(NamespacedKey(plugin, "pet"), PersistentDataType.BYTE, 1)
    }

    /**
     * Checks if the given player is owner
     */
    override fun isOwner(player: Player) = petOwner.uniqueId == player.uniqueId

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
    override fun b(entity: EntityHuman, enumhand: EnumHand): EnumInteractionResult? {
        if (enumhand == EnumHand.MAIN_HAND && entity.bukkitEntity == petOwner) {
            if (petOwner.isSneaking) petCreature.pet() else petInventory.open()
        }

        return super.b(entity, enumhand)
    }

}