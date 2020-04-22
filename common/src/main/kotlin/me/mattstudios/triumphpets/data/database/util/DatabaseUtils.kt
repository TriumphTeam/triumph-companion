package me.mattstudios.triumphpets.data.database.util

import me.mattstudios.mattcore.utils.MessageUtils.color
import org.bukkit.Bukkit
import java.sql.Connection
import java.sql.SQLException

object DatabaseUtils {

    /**
     * Makes it easier to handle try catches in the SQL code
     */
    inline fun Connection.tryRun(message: String? = null, connection: (Connection) -> Unit) {
        try {
            connection.invoke(this)
        } catch (e: SQLException) {
            if (message != null) Bukkit.getLogger().severe(color(message))
            e.printStackTrace()
        } finally {
            if (!isClosed) close()
        }
    }

}