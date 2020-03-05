package me.mattstudios.triumphpets.manager

import me.mattstudios.mattcore.MattPlugin
import me.mattstudios.triumphpets.data.PetData
import me.mattstudios.triumphpets.data.database.DBType
import me.mattstudios.triumphpets.data.database.Database
import me.mattstudios.triumphpets.data.database.type.SQLite
import me.mattstudios.triumphpets.locale.Message
import org.apache.commons.lang.StringUtils
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * @author Matt
 */
class DataManager(private val plugin: MattPlugin, private val dbType: DBType) {

    private lateinit var database: Database
    private val pets = mutableSetOf<PetData>()

    init {
        when (dbType) {
            DBType.SQLITE -> database = SQLite(plugin, this)
        }

    }

    /**
     * Loads the data from the database to the set
     */
    fun loadPet(petData: PetData) {
        pets.add(petData)
    }

    /**
     * Adds the new pet to the set and to the database
     */
    fun addPet(sender: CommandSender, player: Player, petData: PetData) {
        database.insertPet(petData)
        pets.add(petData)
        sender.sendMessage(StringUtils.replace(plugin.locale.getMessage(Message.COMMAND_GIVE_SUCCESS), "{player}", player.name))
    }

    /**
     * Gets all the pets the player has
     */
    fun getPets(player: Player): List<PetData> {
        return pets.filter { it.ownerUuid == player.uniqueId }
    }

}