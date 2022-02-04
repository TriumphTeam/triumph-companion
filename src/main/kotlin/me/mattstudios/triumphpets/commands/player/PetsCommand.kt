package me.mattstudios.triumphpets.commands.player

import com.cryptomorin.xseries.XMaterial
import me.mattstudios.mattcore.utils.MessageUtils.color
import me.mattstudios.mf.annotations.Command
import me.mattstudios.mf.annotations.Default
import me.mattstudios.mf.base.CommandBase
import me.mattstudios.mfgui.gui.components.GuiAction
import me.mattstudios.mfgui.gui.components.ItemBuilder
import me.mattstudios.mfgui.gui.guis.GuiItem
import me.mattstudios.mfgui.gui.guis.PaginatedGui
import me.mattstudios.triumphpets.TriumphPets
import me.mattstudios.triumphpets.data.PetData
import me.mattstudios.triumphpets.locale.Message
import me.mattstudios.triumphpets.util.Items
import org.apache.commons.lang.StringUtils.replace
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

/**
 * @author Matt
 */
@Command("pets")
class PetsCommand(private val plugin: TriumphPets) : CommandBase() {

    private val locale = plugin.locale

    // Starts up the items for the GUI interface
    private val fillItem = ItemBuilder(XMaterial.BLACK_STAINED_GLASS_PANE.parseItem())
            .setName("").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).build()

    // The item builder for the next page button
    private val nextPageItem = ItemBuilder(XMaterial.PAPER.parseItem())
            .setName(locale.getMessage(Message.PET_LIST_GUI_NEXT))
            .addItemFlags(ItemFlag.HIDE_ATTRIBUTES).build()

    // The item builder for the previous page button
    private val prevPageItem = ItemBuilder(XMaterial.PAPER.parseItem())
            .setName(locale.getMessage(Message.PET_LIST_GUI_PREVIOUS))
            .addItemFlags(ItemFlag.HIDE_ATTRIBUTES).build()

    private val dataManager = plugin.petManager.dataManager


    /**
     * Runs the pets command
     */
    @Default
    fun pets(player: Player) {
        val petPlayer = dataManager.getPetPlayer(player) ?: return

        // List with all the pets the player has
        val pets = petPlayer.pets

        val rows = rows(pets.size)
        val gui = PaginatedGui(plugin, rows, 28, locale.getMessage(Message.PET_LIST_GUI_TITLE))

        // Fills in the GUI with it's items and buttons
        setupGui(gui, rows)

        val activePet = petPlayer.getActivePet()

        val despawnItem = ItemBuilder(Items.EMPTY_PET_ITEM.getItem())
                .setName(locale.getMessage(Message.PET_LIST_GUI_NO_PET_TITLE))
                .setLore(color(locale.getMessageRaw(Message.PET_LIST_GUI_NO_PET_LORE)))
                .build()

        val petItem = activePet?.getPetItem(locale, locale.getMessageRaw(Message.PET_DATA_DISPLAY_ACTION_DESPAWN))
                      ?: despawnItem

        // Sets the item for the despawning of the Pet
        gui.setItem(rows * 9 - 5, GuiItem(petItem, GuiAction {
            if (activePet == null) return@GuiAction

            // TODO Despawn message
            plugin.petManager.petController.despawnPet(player)
            petPlayer.activePetUUID = null

            gui.close(player)
        }))

        // Loads all the pets into the GUI
        pets.parallelStream().filter { !petPlayer.isActivePet(it) }.forEach { petData ->
            gui.addItem(GuiItem(petData.getPetItem(locale, locale.getMessageRaw(Message.PET_DATA_DISPLAY_ACTION_SPAWN)), GuiAction {
                if (petPlayer.isActivePet(petData)) return@GuiAction

                // TODO Spawn message
                player.sendMessage("Spawning")

                plugin.petManager.petController.spawnPet(petPlayer, petData)

                gui.close(player)
            }))
        }


        gui.open(player)
    }

    /**
     * Sets up the GUI
     */
    private fun setupGui(gui: PaginatedGui, rows: Int) {
        val lastRow = rows * 9

        // Cancels all the clicks on the GUI
        gui.setDefaultClickAction {
            it.isCancelled = true
        }

        gui.filler.fillBorder(GuiItem(fillItem))

        // The item to use in the prev button
        updateLore(prevPageItem, locale.getMessageRaw(Message.PET_LIST_GUI_PREVIOUS_LORE), gui.currentPageNum)
        updateLore(nextPageItem, locale.getMessageRaw(Message.PET_LIST_GUI_NEXT_LORE), gui.currentPageNum)

        // Sets the pagination item for previous page
        gui.setItem(lastRow - 7, GuiItem(prevPageItem, GuiAction {
            // Updates the item with the current page number
            updateLore(prevPageItem, locale.getMessageRaw(Message.PET_LIST_GUI_PREVIOUS_LORE), gui.currentPageNum)
            updateLore(nextPageItem, locale.getMessageRaw(Message.PET_LIST_GUI_NEXT_LORE), gui.currentPageNum)

            gui.updateItem(lastRow - 3, nextPageItem)
            gui.updateItem(lastRow - 7, prevPageItem)
            gui.prevPage()
        }))

        // Sets the pagination item for next page
        gui.setItem(lastRow - 3, GuiItem(nextPageItem, GuiAction {
            // Updates the item with the current page number
            updateLore(prevPageItem, locale.getMessageRaw(Message.PET_LIST_GUI_PREVIOUS_LORE), gui.currentPageNum)
            updateLore(nextPageItem, locale.getMessageRaw(Message.PET_LIST_GUI_NEXT_LORE), gui.currentPageNum)

            gui.updateItem(lastRow - 3, nextPageItem)
            gui.updateItem(lastRow - 7, prevPageItem)
            gui.nextPage()
        }))
    }

    /**
     * Fills up the information for the current pet
     */
    private fun getPetItem(petData: PetData): ItemStack {
        val builder = ItemBuilder(petData.type.item.clone()).setName(color(petData.name))
        return builder.build()
    }

    /**
     * Gets the correct rows based on how many the player has
     */
    private fun rows(size: Int): Int {
        return 6//3.coerceAtLeast(ceil(size / 9.0 + 2).toInt())
    }

    /**
     * Updates the lore
     */
    private fun updateLore(itemStack: ItemStack, lore: List<String>, pageNumber: Int) {
        val itemMeta = itemStack.itemMeta
        itemMeta?.lore = color(lore.map { replace(it, "{page}", pageNumber.toString()) })
        itemStack.itemMeta = itemMeta
    }

}