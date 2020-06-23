package me.mattstudios.triumphpets.pet.v1_15.pets

import me.mattstudios.mattcore.MattPlugin
import me.mattstudios.mattcore.utils.MessageUtils
import me.mattstudios.mattcore.utils.TimeUtils
import me.mattstudios.triumphpets.config.pet.PetConfig
import me.mattstudios.triumphpets.pet.Pet
import me.mattstudios.triumphpets.pet.v1_15.components.HologramEntity
import me.mattstudios.triumphpets.pet.v1_15.goals.FollowPlayerGoal
import me.mattstudios.triumphpets.pet.v1_15.goals.PickUpItemsGoal
import me.mattstudios.triumphpets.pet.v1_15.goals.RandomWalkAroundGoal
import net.minecraft.server.v1_15_R1.ChatMessage
import net.minecraft.server.v1_15_R1.EntityHuman
import net.minecraft.server.v1_15_R1.EntityInsentient
import net.minecraft.server.v1_15_R1.PathfinderGoalFloat
import net.minecraft.server.v1_15_R1.PathfinderGoalLookAtPlayer
import net.minecraft.server.v1_15_R1.PathfinderGoalSelector
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

/**
 * @author Matt
 */
internal class PetCreature(
        private val plugin: MattPlugin,
        private val petInsentient: EntityInsentient,
        private val petConfig: PetConfig,
        private val petName: String,
        private val owner: Player
) {

    private val world = petInsentient.world
    private var displayName = HologramEntity(plugin, "Foxy", world)

    private var petPetTime = 0L
    private val PET_COOLDOWN = 15

    init {
        // Clears the pathfinder goals
        petInsentient.goalSelector = PathfinderGoalSelector(if (world.methodProfiler != null) world.methodProfiler else null)

        petInsentient.customName = ChatMessage(MessageUtils.color("&7[&6&l★&7]"))
        petInsentient.customNameVisible = true

        petInsentient.persist = true

        displayName.setLocation(petInsentient.locX(), petInsentient.locY() + .6, petInsentient.locZ(), 0.0F, 0.0F)
        world.addEntity(displayName)

        val goalSelector = petInsentient.goalSelector

        goalSelector.a(0, PickUpItemsGoal(petInsentient as Pet, petInsentient, petConfig, 1.5))
        goalSelector.a(1, FollowPlayerGoal(petInsentient as Pet, petInsentient, petConfig, 1.5))
        goalSelector.a(5, RandomWalkAroundGoal(petInsentient as Pet, petInsentient, petConfig, 1.5))
        //goalSelector.a(6, FarmGoal(this, this, 1.5))

        goalSelector.a(7, PathfinderGoalLookAtPlayer(petInsentient, EntityHuman::class.java, 5f))
        goalSelector.a(10, PathfinderGoalFloat(petInsentient))
    }

    internal fun tick() {
        // Checks if the display name died and respawns it
        if (!displayName.isAlive) {
            displayName = HologramEntity(plugin, petName, world)
            world.addEntity(displayName)
        }

        // Makes it so the display name is always on top of the Pet
        displayName.setLocation(petInsentient.locX(), petInsentient.locY() + .6, petInsentient.locZ(), 0.0F, 0.0F)

        // Hides the display name and level when sneaking
        displayName.customNameVisible = !owner.isSneaking
        petInsentient.customNameVisible = !owner.isSneaking
    }

    internal fun remove() {
        petInsentient.bukkitEntity.remove()
        displayName.bukkitEntity.remove()
    }

    /**
     * Plays breed heart animation (just the particle)
     */
    internal fun pet() {
        if (petPetTime != 0L && TimeUtils.getSecondsDifference(petPetTime) < PET_COOLDOWN) return

        owner.addPotionEffect(PotionEffect(PotionEffectType.REGENERATION, 600, 1))
        world.broadcastEntityEffect(petInsentient, 18.toByte())

        petPetTime = System.currentTimeMillis()
    }

}