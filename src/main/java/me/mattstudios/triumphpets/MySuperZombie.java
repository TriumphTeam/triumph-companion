package me.mattstudios.triumphpets;

import net.minecraft.server.v1_14_R1.BehaviorController;
import net.minecraft.server.v1_14_R1.ChatMessage;
import net.minecraft.server.v1_14_R1.Entity;
import net.minecraft.server.v1_14_R1.EntityFox;
import net.minecraft.server.v1_14_R1.EntityHuman;
import net.minecraft.server.v1_14_R1.EntityLiving;
import net.minecraft.server.v1_14_R1.EntityPlayer;
import net.minecraft.server.v1_14_R1.EntityTypes;
import net.minecraft.server.v1_14_R1.EnumHand;
import net.minecraft.server.v1_14_R1.PathfinderGoal;
import net.minecraft.server.v1_14_R1.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_14_R1.PathfinderGoalSelector;
import net.minecraft.server.v1_14_R1.World;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.TreeMap;

public class MySuperZombie extends EntityFox {

    public MySuperZombie(World world) {
        super(EntityTypes.FOX, world);
        this.attachedToPlayer = true;

        clearPathfinders();

        setCustomName(new ChatMessage("Hey"));
        setCustomNameVisible(true);
        setAge(0);

        goalSelector.a(0, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 3f));

    }

    public MySuperZombie(EntityTypes<Entity> entityEntityTypes, World world) {
        super(EntityTypes.FOX, world);
    }

    private void clearPathfinders() {
        try {
            Field brField = EntityLiving.class.getDeclaredField("br");
            brField.setAccessible(true);
            BehaviorController<?> controller = (BehaviorController<?>) brField.get(this);

            /*
            Replace memoriesField with this in 1.14
            Field aField = BehaviorController.class.getDeclaredField("a");
            aField.setAccessible(true);
            aField.set(controller, new HashMap<>());
            */

            Field memoriesField = BehaviorController.class.getDeclaredField("memories");
            memoriesField.setAccessible(true);
            memoriesField.set(controller, new HashMap<>());

            /*
            Replace sensors field with this in 1.14.1
            Field bField = BehaviorController.class.getDeclaredField("b");
            bField.setAccessible(true);
            bField.set(controller, new LinkedHashMap<>());
            */

            Field sensorsField = BehaviorController.class.getDeclaredField("sensors");
            sensorsField.setAccessible(true);
            sensorsField.set(controller, new LinkedHashMap<>());

            Field cField = BehaviorController.class.getDeclaredField("c");
            cField.setAccessible(true);
            cField.set(controller, new TreeMap<>());
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }


        try {
            Field dField;
            dField = PathfinderGoalSelector.class.getDeclaredField("d");
            dField.setAccessible(true);
            dField.set(goalSelector, new LinkedHashSet<>());
            dField.set(targetSelector, new LinkedHashSet<>());

            Field cField;
            cField = PathfinderGoalSelector.class.getDeclaredField("c");
            cField.setAccessible(true);
            dField.set(goalSelector, new LinkedHashSet<>());
            cField.set(targetSelector, new EnumMap<>(PathfinderGoal.Type.class));

            Field fField;
            fField = PathfinderGoalSelector.class.getDeclaredField("f");
            fField.setAccessible(true);
            dField.set(goalSelector, new LinkedHashSet<>());
            fField.set(targetSelector, EnumSet.noneOf(PathfinderGoal.Type.class));
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean a(EntityHuman entity, EnumHand enumhand) {
        ((Player) CraftPlayer.getEntity(entity.getWorld().getServer(), (EntityPlayer) entity)).sendMessage("§2Braaaaaains!");
        return super.a(entity, enumhand);
    }
}
