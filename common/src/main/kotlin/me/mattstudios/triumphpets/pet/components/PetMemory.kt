package me.mattstudios.triumphpets.pet.components

import me.mattstudios.mattcore.MattPlugin
import me.mattstudios.triumphpets.config.pet.PetConfig
import me.mattstudios.triumphpets.config.pet.PetProperty
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Item


/**
 * @author Matt
 */
class PetMemory(
        private val plugin: MattPlugin, val petConfig: PetConfig,
        var filterType: FilterType, val petExperience: PetExperience) {

    constructor(plugin: MattPlugin, petConfig: PetConfig, filterType: FilterType) :
            this(plugin, petConfig, filterType, PetExperience(0))

    var isTracking = false

    private val forgetTime = petConfig[PetProperty.FORGET_LIST_TIME]

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

    /**
     * Filters the material
     */
    fun filter(material: Material) {
        filteredItems.add(material)
    }

    /**
     * Unfilters the material
     */
    fun unFilter(material: Material) {
        filteredItems.remove(material)
    }

    /**
     * Checks if the material is filtered
     */
    fun isFiltered(material: Material): Boolean {
        if (filteredItems.isEmpty()) return false
        return if (filterType == FilterType.BLACK_LIST) filteredItems.contains(material) else !filteredItems.contains(material)
    }

    /**
     * Change the filtering type
     */
    fun toggleFilterType() {
        filterType = if (filterType == FilterType.BLACK_LIST) FilterType.WHITE_LIST else FilterType.BLACK_LIST
    }

    /**
     * Every 15 minutes clears the forgotten list
     */
    private fun periodicallyClearForget() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, Runnable { forgetList.clear() }, forgetTime * 20L, forgetTime * 20L)
    }

}