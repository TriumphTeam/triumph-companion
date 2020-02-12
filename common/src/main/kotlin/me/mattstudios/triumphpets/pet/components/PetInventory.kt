package me.mattstudios.triumphpets.pet.components

import com.cryptomorin.xseries.XMaterial
import me.mattstudios.mattcore.MattPlugin
import me.mattstudios.mattcore.utils.MessageUtils.color
import me.mattstudios.mfgui.gui.GUI
import me.mattstudios.mfgui.gui.GuiItem
import me.mattstudios.mfgui.gui.components.GuiAction
import me.mattstudios.mfgui.gui.components.ItemBuilder
import me.mattstudios.triumphpets.locale.Message
import me.mattstudios.triumphpets.pet.Pet
import me.mattstudios.triumphpets.pet.PetType
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Item
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack


/**
 * @author Matt
 */
class PetInventory(private val plugin: MattPlugin, private val pet: Pet) {

    private val owner = pet.getOwner()
    private val petMemory = pet.getMemory()

    private val petGui = GUI(plugin, rows(), color(pet.getName() + plugin.locale.getMessage(Message.PET_GUI_TITLE)), true)
    private val filterGui = GUI(plugin, 3, color("&3Filter"))

    private val dummyInventory = Bukkit.createInventory(petGui.inventory.holder, petGui.inventory.size, "")

    /**
     * Starts the GUI related stuff
     */
    init {
        setUpPetGui()
        setUpFilterGUI()
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
        dummyInventory.contents = petGui.inventory.contents
        val leftOvers = dummyInventory.addItem(item.itemStack)
        dummyInventory.clear()
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
     * Sets up the Pet GUI
     */
    private fun setUpPetGui() {
        // Sets the fill items in the bottom
        petGui.setItem(listOf(rows() * 9 - 8, rows() * 9 - 6, rows() * 9 - 4, rows() * 9 - 2, rows() * 9 - 1), GuiItem(getFillItem(), GuiAction { it.isCancelled = true }))

        // Sets the filter item for the filter GUI
        petGui.setItem(rows() * 9 - 9, GuiItem(getPetItem(), GuiAction {
            it.isCancelled = true
            clickSound()
            filterGui.open(owner)
        }))

        // Sets the filter item for the filter GUI
        petGui.setItem(rows() * 9 - 7, GuiItem(getFilterItem(), GuiAction {
            it.isCancelled = true
            clickSound()
            filterGui.open(owner)
        }))

        // Sets the close item to close the GUI
        petGui.setItem(rows() * 9 - 5, GuiItem(getCloseItem(), GuiAction {
            it.isCancelled = true
            clickSound()
            owner.closeInventory()
        }))

        // Sets the options item to open the options GUI
        petGui.setItem(rows() * 9 - 3, GuiItem(getOptionsItem(), GuiAction { it.isCancelled = true }))
    }

    /**
     * Sets up the filter GUI
     */
    private fun setUpFilterGUI() {
        filterGui.setDragAction { if (it.inventory.type != InventoryType.PLAYER) it.isCancelled = true }
        filterGui.setDefaultTopClickAction { it.isCancelled = true }

        filterGui.setFillItem(GuiItem(getFillItem()))

        filterGui.setItem(10, GuiItem(XMaterial.AIR.parseItem()))
        filterGui.setItem(11, GuiItem(XMaterial.AIR.parseItem()))
        filterGui.setItem(12, GuiItem(XMaterial.AIR.parseItem()))
        filterGui.setItem(13, GuiItem(XMaterial.AIR.parseItem()))
        filterGui.setItem(14, GuiItem(XMaterial.AIR.parseItem()))
        filterGui.setItem(15, GuiItem(XMaterial.AIR.parseItem()))
        filterGui.setItem(16, GuiItem(XMaterial.AIR.parseItem()))

        filterGui.setItem(22, GuiItem(getCloseItem(), GuiAction {
            clickSound()
            owner.closeInventory()
        }))

        filterGui.setItem(20, GuiItem(ItemStack(Material.PAPER), GuiAction {
            clickSound()
            owner.closeInventory()
        }))

        filterGui.setItem(24, GuiItem(ItemStack(Material.BLACK_CONCRETE), GuiAction {
            clickSound()
            owner.closeInventory()
        }))

        addSlotFilterAction(10)
        addSlotFilterAction(11)
        addSlotFilterAction(12)
        addSlotFilterAction(13)
        addSlotFilterAction(14)
        addSlotFilterAction(15)
        addSlotFilterAction(16)
    }

    /**
     * Gets the rows needed
     */
    private fun rows(): Int {
        return pet.getLevel() + 1
    }

    /**
     * Gets the item to fill the bottom row
     */
    private fun getFillItem(): ItemStack {
        return ItemBuilder(XMaterial.BLACK_STAINED_GLASS_PANE.parseItem()).setName(" ")
                .addItemFlags(ItemFlag.HIDE_ATTRIBUTES).build()
    }

    /**
     * Gets the item for the filter button
     */
    private fun getFilterItem(): ItemStack {
        return ItemBuilder(XMaterial.HOPPER.parseItem()).setName(plugin.locale.getMessage(Message.PET_GUI_FILTER_NAME))
                .setLore(color("&cChange later")).build()
    }

    /**
     * Gets the item for the close button
     */
    private fun getCloseItem(): ItemStack {
        return ItemBuilder(XMaterial.BARRIER.parseItem()).setName(plugin.locale.getMessage(Message.PET_GUI_CLOSE_NAME))
                .setLore(color("&cChange later")).build()
    }

    /**
     * Gets the item for the options button
     */
    private fun getOptionsItem(): ItemStack {
        return ItemBuilder(XMaterial.COMMAND_BLOCK.parseItem()).setName(plugin.locale.getMessage(Message.PET_GUI_OPTIONS_NAME))
                .setLore(color("&cChange later")).build()
    }

    /**
     * Gets the item for the options button
     */
    private fun getPetItem(): ItemStack {
        return ItemBuilder(XMaterial.PLAYER_HEAD.parseItem())
                .setSkullTexture(PetType.PET_FOX_SNOW.texture)
                .setName("")
                .setLore(color("&cChange later")).build()
    }


    /**
     * Plays the click sound
     */
    private fun clickSound() {
        owner.playSound(owner.location, Sound.UI_BUTTON_CLICK, .3f, .5f)
    }

    /**
     * Plays deep xp sound
     */
    private fun xpDeepSound() {
        owner.playSound(owner.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, .3f, .2f)
    }

    /**
     * Plays high xp sound
     */
    private fun xpHighSound() {
        owner.playSound(owner.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, .3f, .8f)
    }

    /**
     * Adds a slot action to the filters
     */
    private fun addSlotFilterAction(slot: Int) {
        filterGui.addSlotAction(slot, GuiAction {
            val cursor = it.cursor ?: return@GuiAction

            // Removing filter
            if (cursor.type == XMaterial.AIR.parseMaterial() && it.currentItem != null) {
                it.currentItem?.type?.let { type -> petMemory.unFilter(type) }
                filterGui.updateItem(slot, XMaterial.AIR.parseItem())
                xpDeepSound()
                return@GuiAction
            }

            if (cursor.type == XMaterial.AIR.parseMaterial()) return@GuiAction

            if (petMemory.isFiltered(cursor.type)) return@GuiAction

            val filterItem = ItemBuilder(cursor.type).glow().build()
            petMemory.filter(cursor.type)
            filterGui.updateItem(slot, filterItem)
            xpHighSound()
        })
    }

}