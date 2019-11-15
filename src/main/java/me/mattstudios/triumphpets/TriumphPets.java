package me.mattstudios.triumphpets;

import me.mattstudios.mattscore.MattPlugin;
import me.mattstudios.mattscore.locale.Locales;
import me.mattstudios.triumphpets.commands.CMDGive;
import me.mattstudios.triumphpets.commands.CMDGives;
import me.mattstudios.triumphpets.commands.CMDPet;
import me.mattstudios.triumphpets.commands.CommandTest;
import me.mattstudios.triumphpets.commands.ListCommand;
import me.mattstudios.triumphpets.commands.PetsCommand;
import me.mattstudios.triumphpets.configuration.MainConfig;
import me.mattstudios.triumphpets.configuration.MessageConfig;
import me.mattstudios.triumphpets.data.PetDataHandler;
import me.mattstudios.triumphpets.gui.GuiHandler;
import me.mattstudios.triumphpets.listeners.PetListener;
import me.mattstudios.triumphpets.pet.PetController;
import me.mattstudios.triumphpets.pet.nms.v1_14_r1.EntityController_1_14_R1;
import me.mattstudios.triumphpets.pet.nms.v1_14_r1.PetRegistry_1_14_R1;

import java.util.stream.Stream;

import static me.mattstudios.mattscore.utils.MessageUtils.info;
import static me.mattstudios.mattscore.utils.NmsUtils.getServerVersion;

public final class TriumphPets extends MattPlugin {

    private PetController petController;
    private PetDataHandler petDataHandler;
    private GuiHandler guiHandler;

    @Override
    public void onPluginLoad() {
        registerNmsPets();
    }

    @Override
    public void onPluginEnable() {

        getConfig().load(MainConfig.class);
        getLocaleHandler().setLocale(Locales.EN_EN);
        getLocale().load(MessageConfig.class, Locales.EN_EN);
        System.out.println(getLocale().get(MessageConfig.STORAGE_METHOD));

        registerCommands();

        registerListeners();

        setUpNms();

        petDataHandler = new PetDataHandler(this);
        guiHandler = new GuiHandler(this);


    }

    @Override
    public void onPluginDisable() {
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

    public PetDataHandler getPetDataHandler() {
        return petDataHandler;
    }

    public GuiHandler getGuiHandler() {
        return guiHandler;
    }

    /**
     * Registers all commands used in the plugin.
     */
    private void registerCommands() {
        Stream.of(
                new PetsCommand(this),
                new ListCommand(this),
                new CMDPet(this),
                new CMDGive(this),
                new CMDGives(this),
                new CommandTest()
        ).forEach(this::registerCommand);
    }

    /**
     * Registers all event listeners used by the plugin.
     */
    private void registerListeners() {
        Stream.of(
                new PetListener(this)
        ).forEach(this::registerListener);
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

}
