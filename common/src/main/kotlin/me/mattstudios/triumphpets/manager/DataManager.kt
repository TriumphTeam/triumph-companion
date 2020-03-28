package me.mattstudios.triumphpets.manager

import me.mattstudios.mattcore.MattPlugin
import me.mattstudios.triumphpets.config.pet.PetConfig
import me.mattstudios.triumphpets.data.PetData
import me.mattstudios.triumphpets.data.database.DBType
import me.mattstudios.triumphpets.data.database.Database
import me.mattstudios.triumphpets.data.database.type.SQLite
import me.mattstudios.triumphpets.pet.PetPlayer
import org.bukkit.entity.Player

/**
 * @author Matt
 */
class DataManager(private val plugin: MattPlugin, dbType: DBType, val petConfig: PetConfig) {

    private lateinit var database: Database
    private val petPlayers = mutableSetOf<PetPlayer>()

    init {
        when (dbType) {
            DBType.SQLITE -> database = SQLite(plugin, this)
        }

    }

    /**
     * Loads the data from the database to the set
     */
    fun loadPlayer(player: PetPlayer) {
        petPlayers.add(player)
    }

    /**
     * Adds the player to the database and the list
     */
    fun addPlayer(petPlayer: PetPlayer) {
        database.insertPlayer(petPlayer)
        petPlayers.add(petPlayer)
    }

    /**
     * Gets the pet player
     */
    fun getPetPlayer(player: Player): PetPlayer? {
        return petPlayers.find { it.isPetPlayer(player) }
    }

    /**
     * Adds the new pet to the set and to the database
     */
    fun addPet(petPlayer: PetPlayer, petData: PetData) {
        database.insertPet(petPlayer, petData)
        petPlayer.addPet(petData)
    }

}