package me.mattstudios.triumphpets.crate.gui

import me.mattstudios.mattcore.MattPlugin
import me.mattstudios.mattcore.utils.MessageUtils.color
import me.mattstudios.mfgui.gui.components.GuiAction
import me.mattstudios.mfgui.gui.components.ItemBuilder
import me.mattstudios.mfgui.gui.components.XMaterial
import me.mattstudios.mfgui.gui.guis.Gui
import me.mattstudios.mfgui.gui.guis.GuiItem
import me.mattstudios.triumphpets.util.Items
import org.bukkit.entity.Player

/**
 * @author Matt
 */
class CrateOptionsGui(plugin: MattPlugin, private val player: Player) {

    private val gui = Gui(plugin, 5, color("&lPet crate options"))
    private val colorGui = Gui(plugin, 4, color("&lSelect color"))
    private val particleGui = Gui(plugin, 5, color("&lSelect particle"))

    private val fillItem = GuiItem(ItemBuilder(XMaterial.BLACK_STAINED_GLASS_PANE.parseItem()).setName("").build())

    init {
        setupGui()
        setupColorGui()
        setupParticleGui()

        gui.open(player)
    }

    private fun setupGui() {
        gui.setDefaultClickAction { it.isCancelled = true }

        gui.filler.fill(GuiItem(ItemBuilder(XMaterial.BLACK_STAINED_GLASS_PANE.parseItem()).build()))

        val crateColorItem = ItemBuilder(Items.CRATE_ITEM_BLUE.item)
                .setName("Crate Color")
                .setLore(listOf("Testing"))
                .build()

        val particle = ItemBuilder(XMaterial.BLAZE_POWDER.parseItem())
                .setName("Crate Particle")
                .setLore(listOf("Testing"))
                .build()

        gui.setItem(2, 3, GuiItem(crateColorItem, GuiAction {
            colorGui.open(player)
        }))

        gui.setItem(2, 7, GuiItem(particle, GuiAction {
            particleGui.open(player)
        }))

        gui.setItem(4, 5, GuiItem(ItemBuilder(XMaterial.EMERALD_BLOCK.parseItem()).build()))

    }

    private fun setupColorGui() {
        colorGui.setDefaultClickAction { it.isCancelled = true }
        colorGui.setOutsideClickAction { gui.open(player) }

        colorGui.filler.fill(fillItem)
    }

    private fun setupParticleGui() {
        particleGui.setDefaultClickAction { it.isCancelled = true }
        particleGui.setOutsideClickAction { gui.open(player) }

        particleGui.filler.fill(fillItem)
    }

}