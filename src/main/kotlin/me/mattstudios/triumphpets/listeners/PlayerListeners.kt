package me.mattstudios.triumphpets.listeners

import io.netty.channel.ChannelDuplexHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelPromise
import me.mattstudios.mattcore.utils.MessageUtils.color
import me.mattstudios.mattcore.utils.MessageUtils.info
import me.mattstudios.triumphpets.TriumphPets
import me.mattstudios.triumphpets.pet.PetPlayer
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

/**
 * @author Matt
 */
class PlayerListeners(private val plugin: TriumphPets) : Listener {

    private val dataManager = plugin.petManager.dataManager

    /**
     * Creates the pe player once the player joins the server
     */
    @EventHandler
    fun PlayerJoinEvent.onPlayerJoin() {
        //injectPlayer(player)
        // If player already exists, returns
        if (dataManager.getPetPlayer(player) != null) return

        dataManager.addPlayer(PetPlayer(player.uniqueId))
    }

    @EventHandler
    fun PlayerQuitEvent.quit() {
        //removePlayer(player)
    }

    private fun removePlayer(player: Player) {
        val channel = (player as CraftPlayer).handle.playerConnection.networkManager.channel
        channel.eventLoop().submit {
            channel.pipeline().remove(player.name)
        }
    }

    private fun injectPlayer(player: Player) {
        println("injecting")
        val duplexHandler = object : ChannelDuplexHandler() {
            @Throws(Exception::class)
            override fun write(ctx: ChannelHandlerContext, packet: Any, promise: ChannelPromise) {
                info(color("&cWrite $packet"))

                super.write(ctx, packet, promise)
            }

            @Throws(Exception::class)
            override fun channelRead(ctx: ChannelHandlerContext, packet: Any) {
                info(color("&aRead $packet"))

                super.channelRead(ctx, packet)
            }
        }

        val pipeline = (player as CraftPlayer).handle.playerConnection.networkManager.channel.pipeline()

        pipeline.addBefore("packet_handler", player.name, duplexHandler)
    }

}