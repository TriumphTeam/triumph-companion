package me.mattstudios.triumphpets.pet.components;

import org.bukkit.Bukkit;
import org.bukkit.entity.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PetMemory {

    private boolean tracking;

    private List<Item> forgetList;
    private List<Item> personalBlackList;

    public PetMemory() {
        tracking = false;

        forgetList = new ArrayList<>();
        personalBlackList = new ArrayList<>();
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

    /**
     * I don't like getting the plugin instance like that but.. welp.. will change later.
     */
    private void periodicallyClearForget() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(
                Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("TriumphPets")),
                () -> forgetList.clear(),
                18000L,
                18000L
        );
    }
}
