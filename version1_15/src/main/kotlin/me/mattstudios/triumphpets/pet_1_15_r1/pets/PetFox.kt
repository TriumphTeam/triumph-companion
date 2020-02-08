package me.mattstudios.triumphpets.pet_1_15_r1.pets

import me.mattstudios.mattcore.MattPlugin
import me.mattstudios.mattcore.utils.MessageUtils.color
import me.mattstudios.mattcore.utils.TimeUtils.getSecondsDifference
import me.mattstudios.triumphpets.pet.Pet
import me.mattstudios.triumphpets.pet.components.PetInventory
import me.mattstudios.triumphpets.pet.components.PetMemory
import me.mattstudios.triumphpets.pet_1_15_r1.goal.ClearGoal.clearPathfinders
import me.mattstudios.triumphpets.pet_1_15_r1.goal.PathfinderGoalFollowPlayer
import me.mattstudios.triumphpets.pet_1_15_r1.goal.PathfinderGoalPickUpItems
import net.minecraft.server.v1_15_R1.ChatMessage
import net.minecraft.server.v1_15_R1.EntityFox
import net.minecraft.server.v1_15_R1.EntityHuman
import net.minecraft.server.v1_15_R1.EntityTypes
import net.minecraft.server.v1_15_R1.EnumHand
import net.minecraft.server.v1_15_R1.PathfinderGoalFloat
import net.minecraft.server.v1_15_R1.PathfinderGoalLookAtPlayer
import net.minecraft.server.v1_15_R1.World
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType


/**
 * @author Matt
 */
class PetFox(private val plugin: MattPlugin, world: World, private val owner: Player, name: String, baby: Boolean, type: Type) : EntityFox(EntityTypes.FOX, world), Pet {

    private val petMemory: PetMemory = PetMemory(plugin)
    private val petInventory = PetInventory(owner, 1)

    private var petPetTime: Long = 0
    private val PET_COOLDOWN = 15

    init {
        clearPathfinders(this)

        customName = ChatMessage(color(name))
        customNameVisible = true
        foxType = type
        canPickUpLoot = false

        if (baby) {
            age = -24000
            ageLocked = true
        }

        collides = false

        goalSelector.a(0, PathfinderGoalPickUpItems(this, this, 1.5))
        goalSelector.a(1, PathfinderGoalFollowPlayer(this, this, 1.5))

        goalSelector.a(7, PathfinderGoalLookAtPlayer(this, EntityHuman::class.java, 5f))
        goalSelector.a(10, PathfinderGoalFloat(this))
    }

    /**
     * Gets the bukkit entity of the pet
     */
    override fun getEntity(): Entity {
        return bukkitEntity
    }

    /**
     * Detects the right click on the entity
     */
    override fun a(entity: EntityHuman, enumhand: EnumHand): Boolean {
        if (enumhand == EnumHand.MAIN_HAND && entity.bukkitEntity == owner) {
            if (owner.isSneaking) pet() else openInventory()
        }
        return super.a(entity, enumhand)
    }

    /**
     * Plays breed heart animation (just the particle)
     */
    private fun pet() {
        if (petPetTime != 0L && getSecondsDifference(petPetTime) < PET_COOLDOWN) return

        owner.addPotionEffect(PotionEffect(PotionEffectType.REGENERATION, 600, 1))
        world.broadcastEntityEffect(this, 18.toByte())

        petPetTime = System.currentTimeMillis()
    }

    /**
     * Opens the pet inventory.
     */
    private fun openInventory() {
        //owner.openInventory(petInventory.getInventory())
    }

    override fun getInventory(): PetInventory {
        return petInventory
    }


    override fun getMemory(): PetMemory {
        return petMemory
    }

    override fun getOwner(): Player {
        return owner
    }
}