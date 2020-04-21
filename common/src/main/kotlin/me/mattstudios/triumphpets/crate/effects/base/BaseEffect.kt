package me.mattstudios.triumphpets.crate.effects.base

import me.mattstudios.mattcore.utils.Task.timer
import me.mattstudios.triumphpets.crate.componetents.Effect
import org.bukkit.Bukkit

/**
 * @author Matt
 */
abstract class BaseEffect(private val period: Long = 2) : Runnable, Effect {

    private var taskId = -1

    /**
     * Starts the task and gets the ID, also prevents new tasks to start without the current being finished
     */
    override fun start() {
        if (taskId != -1) return
        taskId = timer(period, this).taskId
    }

    /**
     * Stops the task and resets the ID
     */
    override fun stop() {
        if (taskId == -1) return
        Bukkit.getScheduler().cancelTask(taskId)
        taskId = -1
    }

}