package me.mattstudios.triumphpets.pet.components;

import me.mattstudios.triumphpets.TriumphPets;
import me.mattstudios.triumphpets.files.Configs;
import org.bukkit.Bukkit;
import org.bukkit.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class PetMemory {

    private TriumphPets plugin;

    private boolean tracking;

    private List<Item> forgetList;
    private List<Item> personalBlackList;

    public PetMemory(TriumphPets plugin) {
        this.plugin = plugin;
        tracking = false;

        forgetList = new ArrayList<>();
        personalBlackList = new ArrayList<>();

        periodicallyClearForget();
    }

    public boolean isTracking() {
        return tracking;
    }

    public void setTracking(boolean tracking) {
        this.tracking = tracking;
    }

    public List<Item> getForgetList() {
        return forgetList;
    }

    public List<Item> getPersonalBlackList() {
        return personalBlackList;
    }

    private void periodicallyClearForget() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> forgetList.clear(), plugin.getConfig().getInt(Configs.FORGET_LIST_TIME) * 20L, plugin.getConfig().getInt(Configs.FORGET_LIST_TIME) * 20L);
    }
}
