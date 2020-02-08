package me.mattstudios.triumphpets.pet.components

import me.mattstudios.mattcore.MattPlugin
import org.bukkit.Bukkit
import org.bukkit.entity.Item
import org.bukkit.inventory.ItemStack


/**
 * @author Matt
 */
class PetMemory(private val plugin: MattPlugin) {

    var tracking = false

    private val forgetList = mutableListOf<Item>()
    private val personalBlackList = mutableListOf<ItemStack>()

    init {
        periodicallyClearForget()
    }

    fun forgetItem(item: Item) {
        forgetList.add(item)
    }

    fun isForgotten(item: Item): Boolean {
        return forgetList.contains(item)
    }

    private fun periodicallyClearForget() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, Runnable { forgetList.clear() }, 18000L, 18000L)
    }

}