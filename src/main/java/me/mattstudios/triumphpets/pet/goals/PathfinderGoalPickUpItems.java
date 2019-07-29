package me.mattstudios.triumphpets.pet.goals;

import net.minecraft.server.v1_14_R1.EntityInsentient;
import net.minecraft.server.v1_14_R1.NavigationAbstract;
import net.minecraft.server.v1_14_R1.PathfinderGoal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.Vector;

import static me.mattstudios.triumphpets.util.Utils.distance;

public class PathfinderGoalPickUpItems extends PathfinderGoal {

    private double speed;
    private EntityInsentient petEntity;
    private Player player;
    private NavigationAbstract navigation;
    private Item trackedItem;
    private Inventory inventory;

    private int controller = 0;

    public PathfinderGoalPickUpItems(EntityInsentient entity, Inventory inventory, double speed, Player player) {
        this.petEntity = entity;
        this.player = player;
        this.navigation = petEntity.getNavigation();
        this.speed = speed;
        this.inventory = inventory;
    }

    @Override
    public void c() {

        if (trackedItem == null || trackedItem.isDead()) return;

        if (distance(trackedItem.getLocation().toVector(), new Vector(petEntity.locX, petEntity.locY, petEntity.locZ)) < 1.5) {
            inventory.addItem(trackedItem.getItemStack());
            trackedItem.remove();
        }

        navigation.a(trackedItem.getLocation().getX(), trackedItem.getLocation().getY(), trackedItem.getLocation().getZ(), speed);
        
    }

    @Override
    public boolean a() {

        c();

        if (controller == 0 || controller % 20 != 0) {
            controller++;
            return false;
        }

        for (Entity foundEntity : petEntity.getBukkitEntity().getNearbyEntities(10, 10, 10)) {
            if (!(foundEntity instanceof Item)) continue;

            if (trackedItem == null || trackedItem.isDead()) {
                trackedItem = (Item) foundEntity;
                continue;
            }

            if (distance(foundEntity.getLocation().toVector(), new Vector(petEntity.locX, petEntity.locY, petEntity.locZ)) < distance(trackedItem.getLocation().toVector(), new Vector(petEntity.locX, petEntity.locY, petEntity.locZ))) {
                trackedItem = (Item) foundEntity;
            }

        }

        if (trackedItem != null && trackedItem.isDead()) trackedItem = null;

        controller = 0;
        return false;
    }

}
