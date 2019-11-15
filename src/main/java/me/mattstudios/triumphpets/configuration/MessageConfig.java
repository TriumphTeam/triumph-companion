package me.mattstudios.triumphpets.configuration;

import ch.jalu.configme.Comment;
import ch.jalu.configme.SettingsHolder;
import ch.jalu.configme.properties.Property;
import me.mattstudios.triumphpets.TriumphPets;
import me.mattstudios.triumphpets.locale.Messages;

import static ch.jalu.configme.properties.PropertyInitializer.newProperty;

public class MessageConfig implements SettingsHolder{

    @Comment("Comment Test!")
    public static final Property<String> STORAGE_METHOD =
            newProperty("using-language", TriumphPets.getLocaleHandler().getLocaleMessage(Messages.TEST));


}
