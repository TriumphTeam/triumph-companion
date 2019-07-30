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
    private boolean isTracking;
    private long startTime;

    private int controller = 0;

    public PathfinderGoalPickUpItems(EntityInsentient entity, Inventory inventory, Memory memory, double speed, Player player) {
        this.petEntity = entity;
        this.player = player;
        this.speed = speed;
        this.inventory = inventory;
        this.memory = memory;

        navigation = petEntity.getNavigation();
        isTracking = false;
        startTime = 0;
    }

    @Override
    public void c() {

        player.sendMessage("Tracking: " + isTracking + (trackedItem == null || trackedItem.isDead() ? "" : " - " + trackedItem.getName()));
        player.sendMessage("Time: " + getSecondsDifference(startTime));


        if (trackedItem == null || trackedItem.isDead() || memory.getForgetList().contains(trackedItem)) {
            resetTracker();
            return;
        }

        if (isTracking && startTime != 0) {
            if (getSecondsDifference(startTime) >= 5) {
                memory.getForgetList().add(trackedItem);
                petEntity.getBukkitEntity().getWorld().spawnParticle(Particle.SMOKE_NORMAL, petEntity.locX, petEntity.locY, petEntity.locZ, 50, .5, .5, .5, 0);
            }
        }

        double dist = distance(trackedItem.getLocation().toVector(), new Vector(petEntity.locX, petEntity.locY, petEntity.locZ));

        if (dist <= 1.5) {
            trackedItem.getWorld().playSound(trackedItem.getLocation(), Sound.ENTITY_ITEM_PICKUP, SoundCategory.MASTER, .5f, 10f);
            inventory.addItem(trackedItem.getItemStack());
            trackedItem.remove();

            resetTracker();
        } else {
            if (!isTracking) startTracking();
            navigation.a(((CraftEntity) trackedItem).getHandle(), speed);
        }

    }

    @Override
    public boolean a() {

        c();

        if (controller == 0 || controller % 20 != 0) {
            controller++;
            return false;
        }

        for (Entity foundEntity : petEntity.getBukkitEntity().getNearbyEntities(10, 5, 10)) {
            if (!(foundEntity instanceof Item)) continue;

            Item item = (Item) foundEntity;

            if (memory.getForgetList().contains(item)) continue;

            if (trackedItem == null || trackedItem.isDead()) {
                trackedItem = item;
                continue;
            }

            if (distance(foundEntity.getLocation().toVector(), new Vector(petEntity.locX, petEntity.locY, petEntity.locZ)) < distance(trackedItem.getLocation().toVector(), new Vector(petEntity.locX, petEntity.locY, petEntity.locZ))) {
                trackedItem = item;
            }

        }

        if (trackedItem != null && trackedItem.isDead()) trackedItem = null;

        controller = 0;
        return false;
    }

    private void startTracking() {
        isTracking = true;
        startTime = System.currentTimeMillis();
    }

    private void resetTracker() {
        trackedItem = null;
        isTracking = false;
        startTime = 0;
    }

}
