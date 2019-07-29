package me.mattstudios.triumphpets.pet.goals;

import me.mattstudios.triumphpets.pet.PetEntity;
import net.minecraft.server.v1_14_R1.EntityInsentient;
import net.minecraft.server.v1_14_R1.NavigationAbstract;
import net.minecraft.server.v1_14_R1.PathfinderGoal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static me.mattstudios.triumphpets.util.Utils.distance;

public class PathfinderGoalPickUpItems extends PathfinderGoal {

    private double speed;
    private PetEntity petEntity;
    private EntityInsentient entity;
    private Player player;
    private NavigationAbstract navigation;
    private Item closestItem;

    private int controller = 0;

    private List<Entity> items;

    public PathfinderGoalPickUpItems(PetEntity entity, Player player, double speed) {
        this.entity = ((EntityInsentient) entity);
        this.petEntity = entity;
        this.player = player;
        this.navigation = ((EntityInsentient) entity).getNavigation();
        this.speed = speed;
        items = new ArrayList<>();
    }

    public void c() {

        if (closestItem == null) return;

        if (distance(closestItem.getLocation().toVector(), new Vector(entity.locX, entity.locY, entity.locZ)) < 1.5) {
            petEntity.getInventory().addItem(closestItem.getItemStack());
            closestItem.remove();
            closestItem = null;
        }

        if (closestItem == null) return;

        navigation.a(closestItem.getLocation().getX(), closestItem.getLocation().getY(), closestItem.getLocation().getZ(), speed);
    }

    @Override
    public boolean a() {

        c();

        if (controller == 0 || controller % 20 != 0) {
            controller++;
            return false;
        }

        Collection<Entity> entities = entity.getBukkitEntity().getNearbyEntities(10, 10, 10);

        for (Entity entityGot : entities) {
            if (!(entityGot instanceof Item)) continue;

            if (closestItem == null) closestItem = (Item) entityGot;

            if (distance(entityGot.getLocation().toVector(), new Vector(entity.locX, entity.locY, entity.locZ)) < distance(closestItem.getLocation().toVector(), new Vector(entity.locX, entity.locY, entity.locZ))) {
                closestItem = (Item) entityGot;
            }

        }

        if (closestItem != null && closestItem.isDead()) closestItem = null;

        if (closestItem != null) player.sendMessage(closestItem.getName());

        controller = 0;

        return false;
    }

}
