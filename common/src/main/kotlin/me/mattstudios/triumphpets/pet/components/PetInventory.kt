package me.mattstudios.triumphpets.pet.components

import me.mattstudios.mattcore.MattPlugin
import me.mattstudios.mattcore.utils.MessageUtils.color
import me.mattstudios.mfgui.gui.GUI
import me.mattstudios.mfgui.gui.GuiItem
import me.mattstudios.mfgui.gui.components.GuiAction
import me.mattstudios.mfgui.gui.components.ItemBuilder
import me.mattstudios.triumphpets.locale.Message
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Item
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack


/**
 * @author Matt
 */
class PetInventory(private val plugin: MattPlugin, name: String, private val owner: Player, private var level: Int) {

    private val petGui = GUI(plugin, rows(), color(name + plugin.locale.getMessage(Message.PET_GUI_TITLE)), true)
    private val checkInventory = Bukkit.createInventory(petGui.inventory.holder, petGui.inventory.size, "")

    /**
     * Starts the GUI related stuff
     */
    init {
        petGui.setItem(listOf(rows() * 9 - 9, rows() * 9 - 8, rows() * 9 - 6, rows() * 9 - 4, rows() * 9 - 2, rows() * 9 - 1), GuiItem(getFillItem(), GuiAction { it.isCancelled = true }))

        petGui.setItem(rows() * 9 - 7, GuiItem(getFilterItem(), GuiAction { it.isCancelled = true }))
        petGui.setItem(rows() * 9 - 5, GuiItem(getCloseItem(), GuiAction { it.isCancelled = true }))
        petGui.setItem(rows() * 9 - 3, GuiItem(getOptionsItem(), GuiAction { it.isCancelled = true }))
    }

    /**
     * Adds the item to the inventory
     */
    fun addItem(item: Item) {
        petGui.addItem(item.itemStack)
    }

    /**
     * Checks if the inventory is full
     */
    fun isFull(item: Item): Boolean {
        val start = System.currentTimeMillis()
        checkInventory.contents = petGui.inventory.contents
        val leftOvers = checkInventory.addItem(item.itemStack)
        checkInventory.clear()
        println("" + (System.currentTimeMillis() - start) + "ms")
        return leftOvers.isNotEmpty()
    }

    /**
     * Checks if the player is currently looking at the GUI
     */
    fun isOpened(): Boolean {
        return petGui.inventory.viewers.contains(owner)
    }

    /**
     * Opens the inventory for the player
     */
    fun open() {
        petGui.open(owner)
    }

    /**
     * Gets the rows needed
     */
    private fun rows(): Int {
        return level + 1
    }

    /**
     * Gets the item to fill the bottom row
     */
    private fun getFillItem(): ItemStack {
        return ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" ")
                .addItemFlags(ItemFlag.HIDE_ATTRIBUTES).build()
    }

    /**
     * Gets the item for the filter button
     */
    private fun getFilterItem(): ItemStack {
        return ItemBuilder(Material.HOPPER).setName(plugin.locale.getMessage(Message.PET_GUI_FILTER_NAME))
                .setLore(color("&cChange later")).build()
    }

    /**
     * Gets the item for the close button
     */
    private fun getCloseItem(): ItemStack {
        return ItemBuilder(Material.REDSTONE_BLOCK).setName(plugin.locale.getMessage(Message.PET_GUI_CLOSE_NAME))
                .setLore(color("&cChange later")).build()
    }

    /**
     * Gets the item for the options button
     */
    private fun getOptionsItem(): ItemStack {
        return ItemBuilder(Material.BOOK).setName(plugin.locale.getMessage(Message.PET_GUI_OPTIONS_NAME))
                .setLore(color("&cChange later")).build()
    }

}