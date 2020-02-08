package me.mattstudios.triumphpets.pet.components

import org.bukkit.Bukkit
import org.bukkit.entity.Item
import org.bukkit.entity.Player

/**
 * @author Matt
 */
class PetInventory(private val owner: Player, private var level: Int) {

    private var petInventory = Bukkit.getServer().createInventory(owner, level * 9)

    fun addItem(item: Item) {
        petInventory.addItem(item.itemStack)
    }

}