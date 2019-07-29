package me.mattstudios.triumphpets.pet.goals;

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

import java.util.ArrayList;
import java.util.List;

import static me.mattstudios.triumphpets.util.Utils.distance;
import static me.mattstudios.utils.TimeUtils.getSecondsDifference;

public class PathfinderGoalPickUpItems extends PathfinderGoal {

    private double speed;
    private EntityInsentient petEntity;
    private Player player;
    private NavigationAbstract navigation;
    private Inventory inventory;

    private Item trackedItem;
    private boolean tracking;
    private long startTrackingTimer;
    private List<Item> forgotItems;

    private int controller = 0;

    public PathfinderGoalPickUpItems(EntityInsentient entity, Inventory inventory, double speed, Player player) {
        this.petEntity = entity;
        this.player = player;
        this.navigation = petEntity.getNavigation();
        this.speed = speed;
        this.inventory = inventory;

        this.tracking = false;
        this.startTrackingTimer = 0;
        this.forgotItems = new ArrayList<>();
    }

    @Override
    public void c() {

        player.sendMessage("Tracking: " + tracking);

        if (trackedItem == null || trackedItem.isDead() || forgotItems.contains(trackedItem)) {
            if (tracking) tracking = false;
            trackedItem = null;
            startTrackingTimer = 0;
            return;
        }

        if (startTrackingTimer != 0 && getSecondsDifference(startTrackingTimer) > 5) {
            forgotItems.add(trackedItem);
            startTrackingTimer = 0;
            petEntity.getBukkitEntity().getLocation().getWorld().spawnParticle(Particle.SMOKE_NORMAL, petEntity.getBukkitEntity().getLocation(), 10, .5, .5, .5, 0);
        }

        if (distance(trackedItem.getLocation().toVector(), new Vector(petEntity.locX, petEntity.locY, petEntity.locZ)) <= 1.5) {
            trackedItem.getWorld().playSound(trackedItem.getLocation(), Sound.ENTITY_ITEM_PICKUP, SoundCategory.MASTER, .5f, 10f);
            inventory.addItem(trackedItem.getItemStack());
            trackedItem.remove();
        } else {
            if (!tracking) {
                startTrackingTimer = System.currentTimeMillis();
                tracking = true;
            }

            System.out.println(navigation.j());

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

            if (forgotItems.contains(item)) continue;

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

}
