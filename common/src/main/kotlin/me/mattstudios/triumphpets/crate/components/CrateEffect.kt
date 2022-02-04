package me.mattstudios.triumphpets.crate.components

import org.bukkit.Material

/**
 * @author Matt
 */
enum class CrateEffect(val material: Material, val effectName: String) {

    NONE(Material.BARRIER, "None"),
    SPARKLE(Material.GHAST_TEAR, "Sparkle"),
    SHIMMER(Material.SNOWBALL, "Shimmer"),
    SPIRAL(Material.NETHER_STAR, "Spiral"),
    ORBIT(Material.ENDER_PEARL, "Orbit");

}