package me.mattstudios.triumphpets.pet.components

import com.cryptomorin.xseries.XMaterial
import me.mattstudios.mattcore.configuration.Config
import me.mattstudios.triumphpets.config.Settings
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import java.util.SplittableRandom

object Experience {

    // 
    private val ores = mutableSetOf<Material>()
    private val ingots = mutableSetOf<Material>()
    private val unstackable = mutableSetOf<Material>()
    private val rare = mutableSetOf<Material>()
    private val special = mutableSetOf<Material>()

    private val random = SplittableRandom()

    private lateinit var config: Config

    fun load(config: Config) {
        this.config = config

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

    fun getExp(material: Material): Short {
        return when (material) {
            in special -> random.nextInt(config[Settings.EXPERIENCE_SPECIAL_MIN], config[Settings.EXPERIENCE_SPECIAL_MAX]).toShort()
            in rare -> random.nextInt(config[Settings.EXPERIENCE_RARE_MIN], config[Settings.EXPERIENCE_RARE_MAX]).toShort()
            in unstackable -> random.nextInt(config[Settings.EXPERIENCE_UNSTACKABLE_MIN], config[Settings.EXPERIENCE_UNSTACKABLE_MAX]).toShort()
            in ingots -> random.nextInt(config[Settings.EXPERIENCE_INGOTS_MIN], config[Settings.EXPERIENCE_INGOTS_MAX]).toShort()
            in ores -> random.nextInt(config[Settings.EXPERIENCE_ORES_MIN], config[Settings.EXPERIENCE_ORES_MAX]).toShort()

            else -> random.nextInt(config[Settings.EXPERIENCE_BASIC_MIN], config[Settings.EXPERIENCE_BASIC_MAX]).toShort()
        }
    }

}