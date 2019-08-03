package me.mattstudios.triumphpets.pet.components;

import org.bukkit.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class Memory {

    private boolean tracking;

    private List<Item> forgetList;
    private List<Item> personalBlackList;

    public Memory() {
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
}
