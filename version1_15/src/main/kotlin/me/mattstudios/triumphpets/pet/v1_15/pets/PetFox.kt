package me.mattstudios.triumphpets.pet.v1_15.pets

import me.mattstudios.mattcore.MattPlugin
import me.mattstudios.mattcore.utils.MessageUtils.color
import me.mattstudios.mattcore.utils.TimeUtils.getSecondsDifference
import me.mattstudios.triumphpets.config.pet.PetConfig
import me.mattstudios.triumphpets.pet.Pet
import me.mattstudios.triumphpets.pet.components.FilterType
import me.mattstudios.triumphpets.pet.components.PetInventory
import me.mattstudios.triumphpets.pet.components.PetMemory
import me.mattstudios.triumphpets.pet.v1_15.components.NameEntity
import me.mattstudios.triumphpets.pet.v1_15.goals.FollowPlayerGoal
import me.mattstudios.triumphpets.pet.v1_15.goals.PickUpItemsGoal
import me.mattstudios.triumphpets.pet.v1_15.goals.RandomWalkAroundGoal
import net.minecraft.server.v1_15_R1.ChatMessage
import net.minecraft.server.v1_15_R1.EntityFox
import net.minecraft.server.v1_15_R1.EntityHuman
import net.minecraft.server.v1_15_R1.EntityTypes
import net.minecraft.server.v1_15_R1.EnumHand
import net.minecraft.server.v1_15_R1.PathfinderGoalFloat
import net.minecraft.server.v1_15_R1.PathfinderGoalLookAtPlayer
import net.minecraft.server.v1_15_R1.PathfinderGoalSelector
import net.minecraft.server.v1_15_R1.World
import org.bukkit.NamespacedKey
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.persistence.PersistentDataType
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType


/**
 * @author Matt
 */
class PetFox(private val plugin: MattPlugin, petConfig: PetConfig, private val owner: Player, private val petName: String, baby: Boolean, type: Type, world: World) : EntityFox(EntityTypes.FOX, world), Pet {

    private var petMemory = PetMemory(plugin, petConfig, FilterType.BLACK_LIST)
    private var petInventory = PetInventory(plugin, this)

    private var displayName = NameEntity(plugin, petName, world)

    private var petPetTime = 0L
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

        persist = true

        world.addEntity(displayName)

        goalSelector.a(0, PickUpItemsGoal(this, this, petConfig, 1.5))
        goalSelector.a(1, FollowPlayerGoal(this, this, petConfig, 1.5))
        goalSelector.a(5, RandomWalkAroundGoal(this, this, petConfig, 1.5))
        //goalSelector.a(6, FarmGoal(this, this, 1.5))

        goalSelector.a(7, PathfinderGoalLookAtPlayer(this, EntityHuman::class.java, 5f))
        goalSelector.a(10, PathfinderGoalFloat(this))

        // Adds NBT tag to identify the entity as pet
        bukkitEntity.persistentDataContainer.set(NamespacedKey(plugin, "pet"), PersistentDataType.BYTE, 1)
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
     * Checks if the given player is owner
     */
    override fun isOwner(player: Player): Boolean {
        return owner.uniqueId == player.uniqueId
    }

    override fun remove() {
        this.bukkitEntity.remove()
        displayName.bukkitEntity.remove()
    }

    /**
     * Keeps the name on top of their head
     */
    override fun tick() {
        super.tick()

        // Checks if the display name died and respawns it
        if (!displayName.isAlive) {
            displayName = NameEntity(plugin, petName, world)
            world.addEntity(displayName)
        }

        // Makes it so the display name is always on top of the Pet
        displayName.setLocation(locX(), locY() + .6, locZ(), 0.0F, 0.0F)

        // Hides the display name and level when sneaking
        displayName.customNameVisible = !owner.isSneaking
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