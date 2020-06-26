package me.mattstudios.triumphpets.pet.v1_16.pets

import me.mattstudios.mattcore.MattPlugin
import me.mattstudios.mattcore.utils.MessageUtils
import me.mattstudios.mattcore.utils.TimeUtils
import me.mattstudios.triumphpets.pet.Pet
import me.mattstudios.triumphpets.pet.v1_16.components.HologramEntity
import me.mattstudios.triumphpets.pet.v1_16.func.addGoal
import me.mattstudios.triumphpets.pet.v1_16.goals.FollowPlayerGoal
import me.mattstudios.triumphpets.pet.v1_16.goals.PickUpItemsGoal
import me.mattstudios.triumphpets.pet.v1_16.goals.RandomWalkAroundGoal
import net.minecraft.server.v1_16_R1.ChatMessage
import net.minecraft.server.v1_16_R1.EntityHuman
import net.minecraft.server.v1_16_R1.EntityInsentient
import net.minecraft.server.v1_16_R1.PathfinderGoalFloat
import net.minecraft.server.v1_16_R1.PathfinderGoalLookAtPlayer
import net.minecraft.server.v1_16_R1.PathfinderGoalSelector
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

/**
 * @author Matt
 */
internal class PetCreature(
        private val plugin: MattPlugin,
        private val petInsentient: EntityInsentient
) {

    private val pet = petInsentient as Pet
    private val petName = pet.petName
    private val petOwner = pet.petOwner

    private val petConfig = pet.petMemory.petConfig

    private val world = petInsentient.world
    private var displayName = HologramEntity(plugin, petName, world)

    private var petPetTime = 0L
    private val petCooldown = 15

    init {
        // Clears the pathfinder goals
        petInsentient.goalSelector = PathfinderGoalSelector(if (world.methodProfiler != null) world.methodProfilerSupplier else null)

        petInsentient.customName = ChatMessage(MessageUtils.color("&7[&6&l★&7]"))
        petInsentient.customNameVisible = true

        petInsentient.persist = true

        displayName.setLocation(petInsentient.locX(), petInsentient.locY() + .6, petInsentient.locZ(), 0.0F, 0.0F)
        world.addEntity(displayName)

        val goalSelector = petInsentient.goalSelector

        // Unsure about all the casting here, doesn't look right
        goalSelector.addGoal(0, PickUpItemsGoal(petInsentient, petConfig, 1.5))
        goalSelector.addGoal(1, FollowPlayerGoal(petInsentient, petConfig, 1.5))
        goalSelector.addGoal(5, RandomWalkAroundGoal(petInsentient, petConfig, 1.5))
        //goalSelector.a(6, FarmGoal(petInsentient, 1.5))

        goalSelector.addGoal(7, PathfinderGoalLookAtPlayer(petInsentient, EntityHuman::class.java, 5f))
        goalSelector.addGoal(10, PathfinderGoalFloat(petInsentient))
    }

    /**
     * Handles the ticking of the entity
     */
    internal fun tick() {
        // Checks if the display name died and respawns it
        if (!displayName.isAlive) {
            displayName = HologramEntity(plugin, petName, world)
            world.addEntity(displayName)
        }

        // Makes it so the display name is always on top of the Pet
        displayName.setLocation(petInsentient.locX(), petInsentient.locY() + .6, petInsentient.locZ(), 0.0F, 0.0F)

        // Hides the display name and level when sneaking
        displayName.customNameVisible = !petOwner.isSneaking
        petInsentient.customNameVisible = !petOwner.isSneaking
    }

    /**
     * Removes the entities from the world
     */
    internal fun remove() {
        petInsentient.bukkitEntity.remove()
        displayName.bukkitEntity.remove()
    }

    /**
     * Plays breed heart animation (just the particle) and gives player a to do effect
     */
    internal fun pet() {
        if (petPetTime != 0L && TimeUtils.getSecondsDifference(petPetTime) < petCooldown) return

        petOwner.addPotionEffect(PotionEffect(PotionEffectType.REGENERATION, 600, 1))
        world.broadcastEntityEffect(petInsentient, 18.toByte())

        petPetTime = System.currentTimeMillis()
    }

}