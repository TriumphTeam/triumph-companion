package me.mattstudios.triumphpets.data;

import me.mattstudios.triumphpets.TriumphPets;
import me.mattstudios.triumphpets.pet.PetType;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static me.mattstudios.triumphpets.util.Queries.SQLITE_CREATE_INVENTORY_ITEMS;
import static me.mattstudios.triumphpets.util.Queries.SQLITE_CREATE_PETS;
import static me.mattstudios.triumphpets.util.Queries.SQLITE_CREATE_PET_INVENTORY;
import static me.mattstudios.triumphpets.util.Utils.TAG;
import static me.mattstudios.utils.MessageUtils.color;
import static me.mattstudios.utils.MessageUtils.info;

public class SQLiteManager {

    private TriumphPets plugin;

    private Connection connection;

    public SQLiteManager(TriumphPets plugin) {
        this.plugin = plugin;

        createDB();
        connect();
        createTables();
    }

    private void createDB() {
        try {
            File dbFile = new File(plugin.getDataFolder(), "tp-data.db");

            if (!dbFile.exists()) {
                if (!dbFile.createNewFile()) info(color(TAG + "&cCouldn't create file."));
                else info(color(TAG + "&aDatabase created successfully!"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + plugin.getDataFolder() + "/tp-data.db");
        } catch (SQLException e) {
            info(color(TAG + "&cAn error occurred trying to connect to the database!"));
            e.printStackTrace();
        }
    }

    private void createTables() {
        try {
            if (connection.isClosed()) connect();

            connection.prepareStatement(SQLITE_CREATE_PETS).execute();
            connection.prepareStatement(SQLITE_CREATE_PET_INVENTORY).execute();
            connection.prepareStatement(SQLITE_CREATE_INVENTORY_ITEMS).execute();
        } catch (SQLException e) {
            info(color(TAG + "&cAn error occurred creating database tables!"));
            e.printStackTrace();
        }
    }

    public void add(Player player) {
        try {
            if (connection.isClosed()) connect();

            String test = "INSERT INTO tp_pets(`owner_uuid`, `pet_type`, `pet_name`, `baby`, `tier`) " +
                    "VALUES ('" + player.getUniqueId().toString() + "', " +
                    "'" + PetType.PET_FOX.name() + "', " +
                    "'Foxy', " +
                    "true, " +
                    "1);";

            connection.prepareStatement(test).executeUpdate();

        } catch (SQLException e) {
            info(color(TAG + "&cAn error occurred creating database tables!"));
        }
    }

}
