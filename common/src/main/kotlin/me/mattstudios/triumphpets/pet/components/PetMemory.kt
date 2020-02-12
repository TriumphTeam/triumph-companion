package me.mattstudios.triumphpets.pet.components

import me.mattstudios.mattcore.MattPlugin
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Item


/**
 * @author Matt
 */
class PetMemory(private val plugin: MattPlugin, val filterType: FilterType) {

    var tracking = false

    private val forgetList = mutableListOf<Item>()
    private val filteredItems = mutableSetOf<Material>()

    /**
     * Makes it so it can clear the forget list periodically
     */
    init {
        periodicallyClearForget()
    }

    /**
     * Forgets an item
     */
    fun forgetItem(item: Item) {
        forgetList.add(item)
    }

    /**
     * Checks if the item in question is forgotten or not
     */
    fun isForgotten(item: Item?): Boolean {
        return if (item == null) true else forgetList.contains(item)
    }

    fun filter(material: Material) {
        filteredItems.add(material)
    }

    fun unFilter(material: Material) {
        filteredItems.remove(material)
    }

    fun isFiltered(material: Material): Boolean {
        return filteredItems.contains(material)
    }

    /**
     * Every 15 minutes clears the forgotten list
     */
    private fun periodicallyClearForget() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, Runnable { forgetList.clear() }, 18000L, 18000L)
    }

}