package me.mattstudios.triumphpets.configuration;

import ch.jalu.configme.Comment;
import ch.jalu.configme.SettingsHolder;
import ch.jalu.configme.properties.Property;

import static ch.jalu.configme.properties.PropertyInitializer.newProperty;

public class MainConfig implements SettingsHolder {



    @Comment("Comment Test!")
    public static final Property<String> STORAGE_METHOD =
            newProperty("storage-method", "shit");

    public static final Property<String> DATA_ADDRESS =
            newProperty("data.address", "localhost");

    @Comment("Comment Tesdsst!")
    public static final Property<String> DATA_DATABASE =
            newProperty("data.database", "minecraft");

    public static final Property<String> DATA_USERNAME =
            newProperty("data.username", "root");

    public static final Property<String> DATA_PASSWORD =
            newProperty("data.password", "");

    @Comment("Comment Tessadasdsa dsadsa dsst!")
    public static final Property<String> sadas =
            newProperty("dssata.passasssword", "");

    public MainConfig() {
        // prevent instantiation
    }

}
