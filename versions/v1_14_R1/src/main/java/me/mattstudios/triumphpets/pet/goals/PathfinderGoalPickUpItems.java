package me.mattstudios.triumphpets.pet.goals;

import me.mattstudios.triumphpets.pet.components.Memory;
import net.minecraft.server.v1_14_R1.EntityInsentient;
import net.minecraft.server.v1_14_R1.NavigationAbstract;
import net.minecraft.server.v1_14_R1.PathfinderGoal;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.Vector;

import static me.mattstudios.triumphpets.util.Utils.distance;
import static me.mattstudios.utils.TimeUtils.getSecondsDifference;

public class PathfinderGoalPickUpItems extends PathfinderGoal {

    private double speed;
    private EntityInsentient petEntity;
    private Player player;
    private NavigationAbstract navigation;
    private Inventory inventory;
    private Memory memory;

    private Item trackedItem;
    private long startTime;
    private final double PICK_DIST;
    private final int SEARCH_DISTANCE;

    private int controller = 0;

    public PathfinderGoalPickUpItems(EntityInsentient entity, Inventory inventory, Memory memory, double speed, Player player) {
        this.petEntity = entity;
        this.player = player;
        this.speed = speed;
        this.inventory = inventory;
        this.memory = memory;

        PICK_DIST = 1.5;
        SEARCH_DISTANCE = 15;
        navigation = petEntity.getNavigation();
        startTime = 0;
    }

    /**
     * Main ticking class for the PathfinderGoal.
     *
     * @return idk tbh..
     */
    @Override
    public boolean a() {

        getItemToTrack();
        pickCloseItem();

        followItem();

        if (trackedItem != null && trackedItem.isDead()) trackedItem = null;

        return true;
    }

    /**
     * Picks up the items that are close.
     */
    private void pickCloseItem() {
        for (Entity foundEntity : petEntity.getBukkitEntity().getNearbyEntities(PICK_DIST, PICK_DIST, PICK_DIST)) {
            if (!(foundEntity instanceof Item)) continue;
            pickItem((Item) foundEntity);
        }
    }

    /**
     * Makes pet follow the closest item.
     */
    private void followItem() {
        if (trackedItem == null || trackedItem.isDead() || memory.getForgetList().contains(trackedItem)) {
            resetTracker();
            return;
        }

        if ((memory.isTracking() && startTime != 0) && getSecondsDifference(startTime) >= 5) {
            memory.getForgetList().add(trackedItem);
            petEntity.getBukkitEntity().getWorld().spawnParticle(Particle.SMOKE_NORMAL, petEntity.locX, petEntity.locY, petEntity.locZ, 50, .5, .5, .5, 0);
        }

        if (!memory.isTracking()) startTracking();
        navigation.a(((CraftEntity) trackedItem).getHandle(), speed);
    }

    /**
     * Gets the closes item for the pet to track.
     */
    private void getItemToTrack() {
        //makes it run only once every 1 second.
        if (controller == 0 || controller % 20 != 0) {
            controller++;
            return;
        }
        controller = 0;

        for (Entity foundEntity : petEntity.getBukkitEntity().getNearbyEntities(SEARCH_DISTANCE, 5, SEARCH_DISTANCE)) {
            if (!(foundEntity instanceof Item)) continue;

            Item item = (Item) foundEntity;

            if (memory.getForgetList().contains(item)) continue;

            if (trackedItem == null || trackedItem.isDead()) {
                trackedItem = item;
                continue;
            }

            if (distance(item.getLocation().toVector(), new Vector(petEntity.locX, petEntity.locY, petEntity.locZ)) < distance(trackedItem.getLocation().toVector(), new Vector(petEntity.locX, petEntity.locY, petEntity.locZ))) {
                trackedItem = item;
            }

        }
    }

    /**
     * Starts tracking the item.
     */
    private void startTracking() {
        memory.setTracking(true);
        startTime = System.currentTimeMillis();
    }

    /**
     * Resets the item tracker to track a new one.
     */
    private void resetTracker() {
        trackedItem = null;
        memory.setTracking(false);
        startTime = 0;
    }

    /**
     * Picks the item given to it.
     *
     * @param item The item to pick up.
     */
    private void pickItem(Item item) {
        item.getWorld().playSound(item.getLocation(), Sound.ENTITY_ITEM_PICKUP, SoundCategory.MASTER, .5f, 10f);
        inventory.addItem(item.getItemStack());
        item.remove();
    }

}
