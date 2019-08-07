package me.mattstudios.triumphpets;

import co.aikar.commands.PaperCommandManager;
import me.mattstudios.triumphpets.commands.CMDPets;
import me.mattstudios.triumphpets.commands.TestCMD;
import me.mattstudios.triumphpets.data.SQLiteManager;
import me.mattstudios.triumphpets.listeners.PetListener;
import me.mattstudios.triumphpets.pet.PetController;
import me.mattstudios.triumphpets.pet.nms.v1_14_r1.EntityController_1_14_R1;
import me.mattstudios.triumphpets.pet.nms.v1_14_r1.PetRegistry_1_14_R1;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.stream.Stream;

import static me.mattstudios.utils.MessageUtils.info;
import static me.mattstudios.utils.NmsUtils.getServerVersion;

public final class TriumphPets extends JavaPlugin {

    private PaperCommandManager commandManager;

    private PetController petController;
    private SQLiteManager sqLiteManager;

    @Override
    public void onLoad() {
        registerNmsPets();
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();

        commandManager = new PaperCommandManager(this);
        registerCommands();

        registerListeners();

        setUpNms();

        sqLiteManager = new SQLiteManager(this);
    }

    @Override
    public void onDisable() {
        petController.removeAll();
    }

    /**
     * Gets the interface to control all the pets.
     *
     * @return The pet controller.
     */
    public PetController getPetController() {
        return petController;
    }

    public SQLiteManager getSqLiteManager() {
        return sqLiteManager;
    }

    /**
     * Registers all commands used in the plugin.
     */
    private void registerCommands() {
        Stream.of(
                new TestCMD(this),
                new CMDPets()
        ).forEach(commandManager::registerCommand);
    }

    /**
     * Registers all event listeners used by the plugin.
     */
    private void registerListeners() {
        Stream.of(
                new PetListener(this)
        ).forEach(this::register);
    }

    /**
     * Registers pet's based on the NMS version
     */
    private void registerNmsPets() {
        String version = getServerVersion();
        switch (version) {
            case "v1_12_R2":
                System.out.println("soon 1.12");
                break;

            case "v1_13_R1":
                System.out.println("soon 1.13");
                break;

            default:
                if (!version.equalsIgnoreCase("v1_14_R1")) info("Might not support.");
                PetRegistry_1_14_R1.registerEntities();
                break;
        }
    }

    /**
     * Sets up the NMS classes, registering the pets and getting pet controller.
     */
    private void setUpNms() {
        String version = getServerVersion();
        switch (version) {
            case "v1_12_R2":
                System.out.println("soon 1.12");
                break;

            case "v1_13_R1":
                System.out.println("soon 1.13");
                break;

            default:
                if (!version.equalsIgnoreCase("v1_14_R1")) info("Might not support.");
                petController = new EntityController_1_14_R1(this);
                break;
        }
    }

    /**
     * Used for better bulk registering listeners.
     *
     * @param listener The listener to register.
     */
    private void register(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }


}
