package me.mattstudios.triumphpets.events;

import me.mattstudios.triumphpets.pet.PetType;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PetPickUpItemEvent extends Event implements Cancellable {

    private boolean cancelled;
    private PetType petType;
    private ItemStack itemStack;
    private Player owner;

    private static final HandlerList handlers  = new HandlerList();

    public PetPickUpItemEvent(PetType petType, ItemStack itemStack, Player owner) {
        this.petType = petType;
        this.itemStack = itemStack;
        this.owner = owner;

        cancelled = false;
    }

    @NotNull
    @EventHandler
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public PetType getPetType() {
        return petType;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public Player getOwner() {
        return owner;
    }
}
