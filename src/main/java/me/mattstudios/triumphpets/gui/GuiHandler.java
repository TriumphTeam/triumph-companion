package me.mattstudios.triumphpets.gui;

import me.mattstudios.triumphpets.TriumphPets;

public class GuiHandler {

    private TiersGui tiersGui;

    public GuiHandler(TriumphPets plugin) {
        tiersGui = new TiersGui(plugin);
    }

    public TiersGui getTiersGui() {
        return tiersGui;
    }
}
