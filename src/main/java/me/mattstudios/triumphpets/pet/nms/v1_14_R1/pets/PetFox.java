package me.mattstudios.triumphpets.pet.nms.v1_14_R1.pets;

import me.mattstudios.triumphpets.pet.components.PetMemory;
import me.mattstudios.triumphpets.pet.goals.PathfinderGoalFollowPlayer;
import me.mattstudios.triumphpets.pet.goals.PathfinderGoalPickUpItems;
import me.mattstudios.triumphpets.pet.goals.PathfinderGoalRandomWalkAround;
import net.minecraft.server.v1_14_R1.BehaviorController;
import net.minecraft.server.v1_14_R1.ChatMessage;
import net.minecraft.server.v1_14_R1.Entity;
import net.minecraft.server.v1_14_R1.EntityFox;
import net.minecraft.server.v1_14_R1.EntityHuman;
import net.minecraft.server.v1_14_R1.EntityLiving;
import net.minecraft.server.v1_14_R1.EntityTypes;
import net.minecraft.server.v1_14_R1.EnumHand;
import net.minecraft.server.v1_14_R1.PathfinderGoal;
import net.minecraft.server.v1_14_R1.PathfinderGoalFloat;
import net.minecraft.server.v1_14_R1.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_14_R1.PathfinderGoalSelector;
import net.minecraft.server.v1_14_R1.World;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.lang.reflect.Field;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.TreeMap;

import static me.mattstudios.utils.MessageUtils.color;

public class PetFox extends EntityFox {

    private Player owner;

    private String name;
    private Inventory inventory;
    private PetMemory petMemory;

    @SuppressWarnings("unused")
    public PetFox(EntityTypes<Entity> entityEntityTypes, World world) {
        super(EntityTypes.FOX, world);
    }

    public PetFox(World world, Player owner, String name, boolean baby, Type type) {
        super(EntityTypes.FOX, world);
        this.owner = owner;
        this.name = name;
        inventory = Bukkit.getServer().createInventory(owner, 27);

        clearPathfinders();

        setCustomName(new ChatMessage(color(name)));
        setCustomNameVisible(true);
        setCanPickupLoot(false);
        setPersistent();

        if (baby) {
            setAge(-24000);
            ageLocked = true;
        }

        collides = false;

        petMemory = new PetMemory();

        setFoxType(type);

        goalSelector.a(0, new PathfinderGoalPickUpItems(this, inventory, petMemory, 1.5, owner));
        goalSelector.a(1, new PathfinderGoalFollowPlayer(this, this.owner, petMemory, 1.5));
        goalSelector.a(6, new PathfinderGoalRandomWalkAround(this, petMemory, 1.2));

        goalSelector.a(7, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 5f));
        goalSelector.a(10, new PathfinderGoalFloat(this));

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Method to get the pet's inventory.
     *
     * @return The pets inventory.
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Get's the pet's internal memory.
     *
     * @return The memory.
     */
    public PetMemory getPetMemory() {
        return petMemory;
    }

    /**
     * Detects the right click on the entity.
     *
     * @param entity   The pet.
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
     * Plays breed heart animation (just the particle).
     */
    private void pet() {
        owner.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 600, 1));
        world.broadcastEntityEffect(this, (byte) 18);
    }

    /**
     * Opens the pet inventory.
     */
    private void openInventory() {
        owner.openInventory(inventory);
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