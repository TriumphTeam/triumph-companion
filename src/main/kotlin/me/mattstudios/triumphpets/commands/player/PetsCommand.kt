package me.mattstudios.triumphpets.commands.player

import com.cryptomorin.xseries.XMaterial
import com.cryptomorin.xseries.XSound
import me.mattstudios.mattcore.utils.MessageUtils.color
import me.mattstudios.mattcore.utils.Task.later
import me.mattstudios.mf.annotations.Command
import me.mattstudios.mf.annotations.Default
import me.mattstudios.mf.base.CommandBase
import me.mattstudios.mfgui.gui.GUI
import me.mattstudios.mfgui.gui.GuiItem
import me.mattstudios.mfgui.gui.components.GuiAction
import me.mattstudios.mfgui.gui.components.ItemBuilder
import me.mattstudios.triumphpets.TriumphPets
import me.mattstudios.triumphpets.data.PetData
import me.mattstudios.triumphpets.locale.Message
import me.mattstudios.triumphpets.util.Utils.playClickSound
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import kotlin.math.ceil

/**
 * @author Matt
 */
@Command("pets")
class PetsCommand(private val plugin: TriumphPets) : CommandBase() {

    // Starts up the items for the GUI interface
    private val fillItem = ItemBuilder(XMaterial.BLACK_STAINED_GLASS_PANE.parseItem())
            .setName(" ").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).build()

    private val closeItem = ItemBuilder(XMaterial.BARRIER.parseItem())
            .setName(plugin.locale.getMessage(Message.GUI_CLOSE_NAME))
            .addItemFlags(ItemFlag.HIDE_ATTRIBUTES).build()

    private val nextItem = ItemBuilder(XMaterial.PAPER.parseItem())
            .setName("Next").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).build()

    private val prevItems = ItemBuilder(XMaterial.PAPER.parseItem())
            .setName("Previous").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).build()

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
        val gui = GUI(plugin, rows, plugin.locale.getMessage(Message.PET_LIST_GUI_TITLE))

        setupGui(gui, rows, player)

        // TODO Pagination
        // Loads all the pets into the GUI
        for (slot in pets.indices) {
            val petData = pets[slot]
            gui.setItem(slot, GuiItem(getPetItem(petData), GuiAction {
                if (petPlayer.isActivePet(petData)) return@GuiAction
                player.sendMessage("should spawn here")

                gui.updateItem(slot, getPetItem(petData))
                plugin.petManager.petController.spawnPet(petPlayer, petData)
            }))
        }

        gui.open(player)
    }

    /**
     * Sets up the GUI
     */
    private fun setupGui(gui: GUI, rows: Int, player: Player) {
        val lastRow = rows * 9

        // Cancels all the clicks on the GUI
        gui.setDefaultClickAction {
            it.isCancelled = true
        }

        gui.setItem(listOf(lastRow - 9, lastRow - 8, lastRow - 6, lastRow - 4, lastRow - 2, lastRow - 1), GuiItem(fillItem))

        // TODO Pagination
        gui.setItem(lastRow - 7, GuiItem(prevItems))

        gui.setItem(lastRow - 5, GuiItem(closeItem, GuiAction {
            later(2) {
                playClickSound(player)
                player.closeInventory()
            }
        }))

        // TODO Pagination
        gui.setItem(lastRow - 3, GuiItem(nextItem))
    }

    /**
     * Fills up the information for the current pet
     */
    private fun getPetItem(petData: PetData): ItemStack {
        val builder = ItemBuilder(petData.type.item.clone()).setName(color(petData.name))

        // TODO figure how to differentiate if pet is spawned or not, fuck

        return builder.build()
    }

    /**
     * Gets the correct rows based on how many the player has
     */
    private fun rows(size: Int): Int {
        return 2.coerceAtLeast(ceil(size / 9.0 + 1).toInt())
    }

}