package me.mattstudios.triumphpets.events

import me.mattstudios.triumphpets.pet.utils.PetType
import org.bukkit.entity.Player
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.EventHandler
import org.bukkit.event.HandlerList
import org.bukkit.inventory.ItemStack


/**
 * @author Matt
 */
class PetPickUpItemEvent(val petType: PetType, val itemStack: ItemStack, val owner: Player) : Event(), Cancellable {

    private var cancelled = false
    private val handlers = HandlerList()

    /**
     * Default even get handlers
     */
    @EventHandler
    override fun getHandlers(): HandlerList {
        return handlers
    }

    /**
     * Default event get handlers list
     */
    fun getHandlerList(): HandlerList {
        return handlers
    }

    /**
     * Checks if the event is cancelled or not
     */
    override fun isCancelled(): Boolean {
        return cancelled
    }

    /**
     * Cancels the event
     */
    override fun setCancelled(cancelled: Boolean) {
        this.cancelled = cancelled
    }

}