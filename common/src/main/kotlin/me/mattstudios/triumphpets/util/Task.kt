package me.mattstudios.triumphpets.util

import me.mattstudios.mattcore.MattPlugin
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitTask

object Task {

    fun async(plugin: MattPlugin, task: () -> Unit): BukkitTask {
        return Bukkit.getScheduler().runTaskAsynchronously(plugin, task)
    }

}