package me.mattstudios.triumphpets.pet.pets;

import me.mattstudios.triumphpets.pet.PetEntity;
import me.mattstudios.triumphpets.pet.goals.PathfinderGoalFollowPlayer;
import me.mattstudios.triumphpets.pet.goals.PathfinderGoalPickUpItems;
import net.minecraft.server.v1_14_R1.BehaviorController;
import net.minecraft.server.v1_14_R1.ChatMessage;
import net.minecraft.server.v1_14_R1.Entity;
import net.minecraft.server.v1_14_R1.EntityFox;
import net.minecraft.server.v1_14_R1.EntityHuman;
import net.minecraft.server.v1_14_R1.EntityLiving;
import net.minecraft.server.v1_14_R1.EntityTypes;
import net.minecraft.server.v1_14_R1.EnumHand;
import net.minecraft.server.v1_14_R1.PathfinderGoal;
import net.minecraft.server.v1_14_R1.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_14_R1.PathfinderGoalRandomStrollLand;
import net.minecraft.server.v1_14_R1.PathfinderGoalSelector;
import net.minecraft.server.v1_14_R1.World;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.lang.reflect.Field;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.TreeMap;

public class PetFox extends EntityFox implements PetEntity {

    private Inventory inventory;
    private Player owner;

    public PetFox(EntityTypes<Entity> entityEntityTypes, World world) {
        super(EntityTypes.FOX, world);
    }

    public PetFox(World world, Player owner) {
        super(EntityTypes.FOX, world);
        this.attachedToPlayer = true;
        this.owner = owner;
        inventory = Bukkit.getServer().createInventory(owner, InventoryType.CHEST);

        clearPathfinders();

        setCustomName(new ChatMessage("Foxy"));
        setCustomNameVisible(true);
        setCanPickupLoot(false);
        setAge(-24000);

        goalSelector.a(0, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 5f));
        goalSelector.a(1, new PathfinderGoalRandomStrollLand(this, 1.3));
        goalSelector.a(2, new PathfinderGoalFollowPlayer(this, this.owner, 1.5));
        goalSelector.a(3, new PathfinderGoalPickUpItems(this, owner, 1.5));

    }

    /**
     * Opens the pet inventory.
     */
    private void openInventory() {
        owner.openInventory(inventory);
    }

    /**
     * Method to get the pet's inventory.
     * @return The pets inventory.
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Plays breed heart animation (just the particle).
     */
    public void pet() {
        world.broadcastEntityEffect(this, (byte) 18);
    }

    /**
     * Detects the right click on the entity.
     * @param entity The pet.
     * @param enumhand The player clicking it.
     * @return That..
     */
    @Override
    public boolean a(EntityHuman entity, EnumHand enumhand) {
        if (enumhand.equals(EnumHand.MAIN_HAND) && entity.getBukkitEntity().equals(owner)) {
            if (owner.isSneaking()) pet();
            else openInventory();
        }
        return super.a(entity, enumhand);
    }

    /**
     * Clears the default behaviours of the Entity.
     */
    private void clearPathfinders() {
        try {
            Field brField = EntityLiving.class.getDeclaredField("br");
            brField.setAccessible(true);
            BehaviorController<?> controller = (BehaviorController<?>) brField.get(this);

            Field memoriesField = BehaviorController.class.getDeclaredField("memories");
            memoriesField.setAccessible(true);
            memoriesField.set(controller, new HashMap<>());

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

}
