package me.mattstudios.triumphpets.crate.gui

import me.mattstudios.mattcore.MattPlugin
import me.mattstudios.mattcore.utils.MessageUtils.color
import me.mattstudios.mfgui.gui.components.GuiAction
import me.mattstudios.mfgui.gui.components.ItemBuilder
import me.mattstudios.mfgui.gui.components.XMaterial
import me.mattstudios.mfgui.gui.guis.Gui
import me.mattstudios.mfgui.gui.guis.GuiItem
import me.mattstudios.triumphpets.util.Items
import me.mattstudios.triumphpets.util.Utils.playClickSound
import org.bukkit.Material
import org.bukkit.entity.Player

/**
 * @author Matt
 */
class CrateOptionsGui(plugin: MattPlugin, private val player: Player) {

    private val gui = Gui(plugin, 5, color("&lPet crate options"))
    private val colorGui = Gui(plugin, 3, color("&lSelect color"))
    private val particleGui = Gui(plugin, 5, color("&lSelect particle"))

    private val fillItem = GuiItem(ItemBuilder(XMaterial.BLACK_STAINED_GLASS_PANE.parseItem()).setName("").build())

    init {
        setupGui()
        setupColorGui()
        setupParticleGui()

        gui.open(player)
    }

    /**
     * Adds the main components to the GUI
     */
    private fun setupGui() {
        gui.setDefaultClickAction { it.isCancelled = true }

        gui.filler.fill(GuiItem(ItemBuilder(XMaterial.BLACK_STAINED_GLASS_PANE.parseItem()).build()))

        val crateColorItem = ItemBuilder(Items.CRATE_ITEM_BLUE.getItem())
                .setName("Crate Color")
                .setLore(listOf("Testing"))
                .build()

        val particle = ItemBuilder(XMaterial.BLAZE_POWDER.parseItem())
                .setName("Crate Particle")
                .setLore(listOf("Testing"))
                .build()

        gui.setItem(2, 3, GuiItem(crateColorItem, GuiAction {
            playClickSound(player)
            colorGui.open(player)
        }))

        gui.setItem(2, 7, GuiItem(particle, GuiAction {
            playClickSound(player)
            particleGui.open(player)
        }))

        gui.setItem(4, 5, GuiItem(ItemBuilder(XMaterial.EMERALD_BLOCK.parseItem()).build()))

    }

    /**
     * Sets up the color choose GUI
     */
    private fun setupColorGui() {
        colorGui.setDefaultClickAction { it.isCancelled = true }
        colorGui.setOutsideClickAction {
            playClickSound(player)
            gui.open(player)
        }

        colorGui.filler.fill(fillItem)

        colorGui.setItem(2, 3, GuiItem(ItemBuilder(Items.CRATE_ITEM_BLUE.getItem()).build()))
        colorGui.setItem(2, 4, GuiItem(ItemBuilder(Items.CRATE_ITEM_PURPLE.getItem()).build()))
        colorGui.setItem(2, 5, GuiItem(ItemBuilder(Items.CRATE_ITEM_RED.getItem()).build()))
        colorGui.setItem(2, 6, GuiItem(ItemBuilder(Items.CRATE_ITEM_YELLOW.getItem()).build()))
        colorGui.setItem(2, 7, GuiItem(ItemBuilder(Items.CRATE_ITEM_GREEN.getItem()).build()))

        colorGui.setItem(3, 1, GuiItem(ItemBuilder(Material.PAPER).build()))
    }

    /**
     * Sets up the particles choose GUI
     */
    private fun setupParticleGui() {
        particleGui.setDefaultClickAction { it.isCancelled = true }
        particleGui.setOutsideClickAction {
            playClickSound(player)
            gui.open(player)
        }

        particleGui.filler.fill(fillItem)
    }

}