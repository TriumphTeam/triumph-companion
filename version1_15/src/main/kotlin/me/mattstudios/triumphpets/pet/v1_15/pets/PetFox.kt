package me.mattstudios.triumphpets.pet.v1_15.pets

import me.mattstudios.mattcore.MattPlugin
import me.mattstudios.mattcore.utils.MessageUtils.color
import me.mattstudios.mattcore.utils.TimeUtils.getSecondsDifference
import me.mattstudios.triumphpets.pet.Pet
import me.mattstudios.triumphpets.pet.components.FilterType
import me.mattstudios.triumphpets.pet.components.PetInventory
import me.mattstudios.triumphpets.pet.components.PetMemory
import me.mattstudios.triumphpets.pet.v1_15.components.NameEntity
import me.mattstudios.triumphpets.pet.v1_15.goals.PathfinderGoalFollowPlayer
import me.mattstudios.triumphpets.pet.v1_15.goals.PathfinderGoalPickUpItems
import me.mattstudios.triumphpets.pet.v1_15.goals.PathfinderGoalRandomWalkAround
import net.minecraft.server.v1_15_R1.ChatMessage
import net.minecraft.server.v1_15_R1.EntityFox
import net.minecraft.server.v1_15_R1.EntityHuman
import net.minecraft.server.v1_15_R1.EntityTypes
import net.minecraft.server.v1_15_R1.EnumHand
import net.minecraft.server.v1_15_R1.PathfinderGoalFloat
import net.minecraft.server.v1_15_R1.PathfinderGoalLookAtPlayer
import net.minecraft.server.v1_15_R1.PathfinderGoalSelector
import net.minecraft.server.v1_15_R1.World
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType


/**
 * @author Matt
 */
class PetFox(private val plugin: MattPlugin, world: World, private val owner: Player, private val petName: String, baby: Boolean, type: Type) : EntityFox(EntityTypes.FOX, world), Pet {

    private val petMemory: PetMemory = PetMemory(plugin, FilterType.BLACK_LIST)
    private val petInventory = PetInventory(plugin, this)

    private val displayLevel = NameEntity(petName, world)

    private var petPetTime: Long = 0
    private val PET_COOLDOWN = 15

    init {
        // Clears the goal selector
        goalSelector = PathfinderGoalSelector(if (world.methodProfiler != null) world.methodProfiler else null)

        customName = ChatMessage(color("&7[&6&l★★★★★&7]"))
        customNameVisible = true
        foxType = type
        canPickUpLoot = false

        if (baby) {
            age = -24000
            ageLocked = true
        }

        world.addEntity(displayLevel)

        goalSelector.a(0, PathfinderGoalPickUpItems(this, this, 1.5))
        goalSelector.a(1, PathfinderGoalFollowPlayer(this, this, 1.5))
        goalSelector.a(5, PathfinderGoalRandomWalkAround(this, this, 1.5))

        goalSelector.a(7, PathfinderGoalLookAtPlayer(this, EntityHuman::class.java, 5f))
        goalSelector.a(10, PathfinderGoalFloat(this))
    }

    override fun getLevel(): Short {
        return 1
    }

    override fun getName(): String {
        return petName
    }

    /**
     * Gets the bukkit entity of the pet
     */
    override fun getEntity(): Entity {
        return bukkitEntity
    }

    /**
     * Gets the inventory
     */
    override fun getInventory(): PetInventory {
        return petInventory
    }

    /**
     * Gets the pets memory
     */
    override fun getMemory(): PetMemory {
        return petMemory
    }

    /**
     * Gets the pet's owner
     */
    override fun getOwner(): Player {
        return owner
    }

    /**
     * Keeps the name on top of their head
     */
    override fun tick() {
        super.tick()

        displayLevel.setLocation(locX(), locY() + .6, locZ(), 0.0F, 0.0F)

        displayLevel.customNameVisible = !owner.isSneaking
        this.customNameVisible = !owner.isSneaking

    }

    /**
     * Detects the right click on the entity
     */
    override fun a(entity: EntityHuman, enumhand: EnumHand): Boolean {
        if (enumhand == EnumHand.MAIN_HAND && entity.bukkitEntity == owner) {
            if (owner.isSneaking) pet() else petInventory.open()
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

}