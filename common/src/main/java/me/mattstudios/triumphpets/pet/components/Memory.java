package me.mattstudios.triumphpets.pet.components;

import org.bukkit.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class Memory {

    private List<Item> forgetList;
    private List<Item> personalBlackList;

    public Memory() {
        forgetList = new ArrayList<>();
        personalBlackList = new ArrayList<>();
    }

    public List<Item> getForgetList() {
        return forgetList;
    }

    public List<Item> getPersonalBlackList() {
        return personalBlackList;
    }
}
