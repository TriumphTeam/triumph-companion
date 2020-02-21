package me.mattstudios.triumphpets.config.pet

import ch.jalu.configme.Comment
import ch.jalu.configme.SettingsHolder
import ch.jalu.configme.configurationdata.CommentsConfiguration
import ch.jalu.configme.properties.Property
import ch.jalu.configme.properties.PropertyInitializer.newProperty

object PetProperty : SettingsHolder {

    /**
     * Global settings for pets
     */

    @JvmField
    @Comment("Distance in blocks in which pets should search for items, (keep this low)", "Default: 15.0")
    val ITEM_SEARCH_DISTANCE: Property<Double> = newProperty("item-search-distance", 15.0)

    @JvmField
    @Comment("How many ticks should be waited to search for a new item, lower number - faster - worse performance", "Default: 20 (1 second)")
    val ITEM_TRACK_TICKS: Property<Int> = newProperty("item-track-ticks", 20)

    @JvmField
    @Comment("Distance in blocks in which pet's pick up items close by, making it smaller than 1.5 might make the pet not reach it", "Default: 1.5")
    val ITEM_PICK_DISTANCE: Property<Double> = newProperty("item-pick-distance", 1.5)

    @JvmField
    @Comment("After how many seconds without being able to reach an item, the pet should forget about it", "Default: 5")
    val ITEM_FORGET_TIME: Property<Int> = newProperty("item-forget-time", 5)

    @JvmField
    @Comment("How often in seconds pet's forget list should be cleared", "Default: 900")
    val FORGET_LIST_TIME: Property<Int> = newProperty("forget-list-time", 900)

    @JvmField
    @Comment("Distance in blocks in which the pet should follow player", "Default: 7.0")
    val FOLLOW_DISTANCE: Property<Double> = newProperty("follow-distance", 7.0)

    @JvmField
    @Comment("Distance in blocks which the pet will teleport to the player.", "Default: 20.0")
    val TELEPORT_DISTANCE: Property<Double> = newProperty("teleport-distance", 20.0)

    @JvmField
    @Comment("Chance for the pet to walk around randomly, higher number more walking (1-100)", "Default: 5 (5%)")
    val WALK_AROUND_CHANCE: Property<Int> = newProperty("walk-around-chance", 5)
    override fun registerComments(conf: CommentsConfiguration) {
        conf.setComment("item-search-distance",
                "█░█░█ ▄▀█ █▀█ █▄░█ █ █▄░█ █▀▀",
                "▀▄▀▄▀ █▀█ █▀▄ █░▀█ █ █░▀█ █▄█",
                "Be extremely careful with this values as they can completely mess up your pets!",
                "Only change them slightly according to your needs and ONLY if you need!",
                "The default values are commented so if you mess up please use those!", " ", " ")
    }
}