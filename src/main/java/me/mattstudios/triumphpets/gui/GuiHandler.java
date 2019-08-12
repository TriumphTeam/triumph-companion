package me.mattstudios.triumphpets.gui;

import me.mattstudios.triumphpets.TriumphPets;

public class GuiHandler {

    private GuiTiers guiTiers;

    public GuiHandler(TriumphPets plugin) {
        guiTiers = new GuiTiers(plugin);
    }

    public GuiTiers getGuiTiers() {
        return guiTiers;
    }
}
