package me.mattstudios.triumphpets.pet.nms.v1_14_r1.goals;

import me.mattstudios.triumphpets.events.PetPickUpItemEvent;
import me.mattstudios.triumphpets.pet.PetEntity;
import me.mattstudios.triumphpets.pet.PetType;
import me.mattstudios.triumphpets.pet.components.PetMemory;
import net.minecraft.server.v1_14_R1.EntityInsentient;
import net.minecraft.server.v1_14_R1.NavigationAbstract;
import net.minecraft.server.v1_14_R1.PathfinderGoal;
import org.bukkit.Bukkit;
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
    private EntityInsentient entityInsentient;
    private Player owner;
    private NavigationAbstract navigation;
    private Inventory inventory;
    private PetMemory petMemory;

    private Item trackedItem;
    private long startTime;
    private final double PICK_DIST;
    private final int SEARCH_DISTANCE;

    private int controller = 0;

    public PathfinderGoalPickUpItems(EntityInsentient entityInsentient, PetEntity petEntity, Player owner, double speed) {
        this.entityInsentient = entityInsentient;
        this.owner = owner;
        this.speed = speed;

        inventory = petEntity.getPetInventory().getInventory();
        petMemory = petEntity.getPetMemory();
        navigation = entityInsentient.getNavigation();

        startTime = 0;
        PICK_DIST = 1.5;
        SEARCH_DISTANCE = 15;
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
        for (Entity foundEntity : entityInsentient.getBukkitEntity().getNearbyEntities(PICK_DIST, PICK_DIST, PICK_DIST)) {
            if (!(foundEntity instanceof Item)) continue;
            pickItem((Item) foundEntity);
        }
    }

    /**
     * Makes pet follow the closest item.
     */
    private void followItem() {
        if (trackedItem == null || trackedItem.isDead() || petMemory.getForgetList().contains(trackedItem)) {
            resetTracker();
            return;
        }

        if ((petMemory.isTracking() && startTime != 0) && getSecondsDifference(startTime) >= 5) {
            petMemory.getForgetList().add(trackedItem);
            entityInsentient.getBukkitEntity().getWorld().spawnParticle(Particle.SMOKE_NORMAL, entityInsentient.locX, entityInsentient.locY, entityInsentient.locZ, 50, .5, .5, .5, 0);
        }

        if (!petMemory.isTracking()) startTracking();
        navigation.a(((CraftEntity) trackedItem).getHandle(), speed);
    }

    /**
     * Gets the closes item for the pet to track.
     */
    private void getItemToTrack() {
        //makes it run only once every 1 second.
        if (!shouldRun()) return;

        for (Entity foundEntity : entityInsentient.getBukkitEntity().getNearbyEntities(SEARCH_DISTANCE, 5, SEARCH_DISTANCE)) {
            if (!(foundEntity instanceof Item)) continue;

            Item item = (Item) foundEntity;

            if (petMemory.getForgetList().contains(item)) continue;

            if (trackedItem == null || trackedItem.isDead()) {
                trackedItem = item;
                continue;
            }

            if (distance(item.getLocation().toVector(), new Vector(entityInsentient.locX, entityInsentient.locY, entityInsentient.locZ)) < distance(trackedItem.getLocation().toVector(), new Vector(entityInsentient.locX, entityInsentient.locY, entityInsentient.locZ))) {
                trackedItem = item;
            }

        }
    }

    /**
     * Starts tracking the item.
     */
    private void startTracking() {
        petMemory.setTracking(true);
        startTime = System.currentTimeMillis();
    }

    /**
     * Resets the item tracker to track a new one.
     */
    private void resetTracker() {
        trackedItem = null;
        petMemory.setTracking(false);
        startTime = 0;
    }

    /**
     * Picks the item given to it.
     *
     * @param item The item to pick up.
     */
    private void pickItem(Item item) {
        PetPickUpItemEvent event = new PetPickUpItemEvent(PetType.PET_FOX_SNOW, item.getItemStack(), owner);
        Bukkit.getPluginManager().callEvent(event);

        if (event.isCancelled()) return;

        item.getWorld().playSound(item.getLocation(), Sound.ENTITY_ITEM_PICKUP, SoundCategory.MASTER, .5f, 10f);
        inventory.addItem(item.getItemStack());
        item.remove();
    }

    private boolean shouldRun() {
        if (controller != 20) {
            controller++;
            return false;
        }
        controller = 0;
        return true;
    }

}
