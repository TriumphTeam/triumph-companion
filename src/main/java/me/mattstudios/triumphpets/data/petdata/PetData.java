package me.mattstudios.triumphpets.data.petdata;

import me.mattstudios.triumphpets.pet.PetType;
import org.bukkit.inventory.Inventory;

public class PetData {

    private int petId;
    private String petName;
    private PetType petType;
    private boolean baby;
    private short tier;
    private Inventory inventory;

    public PetData(int petId, String petName, PetType petType, boolean baby, short tier, Inventory inventory) {
        this.petName = petName;
        this.petId = petId;
        this.petType = petType;
        this.baby = baby;
        this.tier = tier;
        this.inventory = inventory;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public boolean isBaby() {
        return baby;
    }

    public void setBaby(boolean baby) {
        this.baby = baby;
    }

    public short getTier() {
        return tier;
    }

    public void setTier(short tier) {
        this.tier = tier;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
