package me.mattstudios.triumphpets.pet.utils

import com.cryptomorin.xseries.XMaterial
import me.mattstudios.mattcore.configuration.Config
import me.mattstudios.triumphpets.config.Settings
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import java.util.SplittableRandom

object Experience {

    // Set with all the ores
    private val ores = mutableSetOf<Material>()
    // Set with all the ingots/nuggets
    private val ingots = mutableSetOf<Material>()
    // Set with all the unstackable items
    private val unstackable = mutableSetOf<Material>()
    // Set with all the rare items
    private val rare = mutableSetOf<Material>()
    // Set with all special items
    private val special = mutableSetOf<Material>()

    private val random = SplittableRandom()

    private lateinit var config: Config

    /**
     * Loads the data into the sets and gets the config data
     */
    fun load(config: Config) {
        Experience.config = config

        XMaterial.LAPIS_LAZULI.parseMaterial()?.let { ingots.add(it) }
        XMaterial.REDSTONE.parseMaterial()?.let { ingots.add(it) }

        XMaterial.EXPERIENCE_BOTTLE.parseMaterial()?.let { rare.add(it) }
        XMaterial.DRAGON_BREATH.parseMaterial()?.let { rare.add(it) }
        XMaterial.ELYTRA.parseMaterial()?.let { rare.add(it) }
        XMaterial.ENCHANTED_BOOK.parseMaterial()?.let { rare.add(it) }
        XMaterial.CREEPER_HEAD.parseMaterial()?.let { rare.add(it) }
        XMaterial.PLAYER_HEAD.parseMaterial()?.let { rare.add(it) }
        XMaterial.ZOMBIE_HEAD.parseMaterial()?.let { rare.add(it) }
        XMaterial.GOLDEN_APPLE.parseMaterial()?.let { rare.add(it) }
        XMaterial.BEACON.parseMaterial()?.let { rare.add(it) }
        XMaterial.DIAMOND.parseMaterial()?.let { rare.add(it) }
        XMaterial.EMERALD.parseMaterial()?.let { rare.add(it) }

        XMaterial.HEART_OF_THE_SEA.parseMaterial()?.let { special.add(it) }
        XMaterial.NETHER_STAR.parseMaterial()?.let { special.add(it) }
        XMaterial.TOTEM_OF_UNDYING.parseMaterial()?.let { special.add(it) }
        XMaterial.CONDUIT.parseMaterial()?.let { special.add(it) }
        XMaterial.END_CRYSTAL.parseMaterial()?.let { special.add(it) }
        XMaterial.DRAGON_EGG.parseMaterial()?.let { special.add(it) }
        XMaterial.ENCHANTED_GOLDEN_APPLE.parseMaterial()?.let { special.add(it) }

        for (xMaterial in XMaterial.VALUES) {
            val material = xMaterial.parseMaterial() ?: continue

            if (material in rare || material in special) continue

            if (material.name.contains("ORE")) {
                ores.add(material)
                continue
            }

            if (material.name.contains("INGOT") || material.name.contains("NUGGET")) {
                ingots.add(material)
                continue
            }

            if (material.name.contains("MUSIC_DISC")) {
                rare.add(material)
                continue
            }

            if (ItemStack(material).maxStackSize == 1) {
                unstackable.add(material)
            }
        }
    }

    /**
     * Gets the correct amount of XP based on the given Material
     */
    fun getExp(material: Material, amount: Int): Int {
        var xp = 0

        when (material) {
            in special -> {
                for (i in 1..amount) {
                    xp += random.nextInt(config[Settings.EXPERIENCE_SPECIAL_MIN], config[Settings.EXPERIENCE_SPECIAL_MAX] + 1)
                }
            }
            in rare -> {
                for (i in 1..amount) {
                    xp += random.nextInt(config[Settings.EXPERIENCE_RARE_MIN], config[Settings.EXPERIENCE_RARE_MAX] + 1)
                }
            }
            in unstackable -> {
                for (i in 1..amount) {
                    xp += random.nextInt(config[Settings.EXPERIENCE_UNSTACKABLE_MIN], config[Settings.EXPERIENCE_UNSTACKABLE_MAX] + 1)
                }
            }
            in ingots -> {
                for (i in 1..amount) {
                    xp += random.nextInt(config[Settings.EXPERIENCE_INGOTS_MIN], config[Settings.EXPERIENCE_INGOTS_MAX] + 1)
                }
            }
            in ores -> {
                for (i in 1..amount) {
                    xp += random.nextInt(config[Settings.EXPERIENCE_ORES_MIN], config[Settings.EXPERIENCE_ORES_MAX] + 1)
                }
            }

            else -> {
                for (i in 1..amount) {
                    xp += random.nextInt(config[Settings.EXPERIENCE_BASIC_MIN], config[Settings.EXPERIENCE_BASIC_MAX] + 1)
                }
            }
        }

        return xp
    }

}