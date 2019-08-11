package me.mattstudios.triumphpets.data;

import com.zaxxer.hikari.HikariDataSource;
import me.mattstudios.triumphpets.TriumphPets;
import me.mattstudios.triumphpets.data.petdata.PetData;
import me.mattstudios.triumphpets.pet.PetType;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static me.mattstudios.triumphpets.util.Queries.SQLITE_CREATE_PETS;
import static me.mattstudios.triumphpets.util.Queries.SQLITE_CREATE_PET_INVENTORY;
import static me.mattstudios.triumphpets.util.Utils.TAG;
import static me.mattstudios.utils.MessageUtils.color;
import static me.mattstudios.utils.MessageUtils.info;

public class SQLiteManager {

    private TriumphPets plugin;

    private HikariDataSource hikari;
    private Map<String, List<PetData>> petsData;

    public SQLiteManager(TriumphPets plugin) {
        this.plugin = plugin;

        petsData = new HashMap<>();

        createDB();
        createTables();

        cacheData();
    }

    public List<PetData> getPets(Player player) {
        return petsData.getOrDefault(player.getUniqueId().toString(), null);
    }

    /*public void add(Player player) {
        try {
            if (connection.isClosed()) connect();

            String test = "INSERT INTO tp_pets(`owner_uuid`, `pet_type`, `pet_name`, `baby`, `tier`) " +
                    "VALUES ('" + player.getUniqueId().toString() + "', " +
                    "'" + PetType.PET_FOX_SNOW.name() + "', " +
                    "'Foxy', " +
                    "true, " +
                    "1);";

            connection.prepareStatement(test).executeUpdate();

        } catch (SQLException e) {
            info(color(TAG + "&cAn error occurred creating database tables!"));
        }
    }*/

    private void createDB() {
        try {
            File dbFile = new File(plugin.getDataFolder(), "tp-data.db");

            if (!dbFile.exists()) {
                if (!dbFile.createNewFile()) info(color(TAG + "&cCouldn't create file."));
                else info(color(TAG + "&aDatabase created successfully!"));
            }

            connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void connect() {
        hikari = new HikariDataSource();
        hikari.setJdbcUrl("jdbc:sqlite:" + plugin.getDataFolder() + "/tp-data.db");
        hikari.setPoolName("TriumphPets");
    }

    private void createTables() {
        Connection connection = null;
        try {
            connection = hikari.getConnection();

            connection.prepareStatement(SQLITE_CREATE_PETS).execute();
            connection.prepareStatement(SQLITE_CREATE_PET_INVENTORY).execute();
        } catch (SQLException e) {
            info(color(TAG + "&cAn error occurred creating database tables!"));
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void cacheData() {
        Connection connection = null;
        try {
            connection = hikari.getConnection();

            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM `tp_pets`;");

            while (resultSet.next()) {
                int petId = resultSet.getInt("pet_id");
                String ownerUuid = resultSet.getString("owner_uuid");
                String petName = resultSet.getString("pet_name");
                PetType petType = PetType.valueOf(resultSet.getString("pet_type"));
                boolean baby = resultSet.getBoolean("baby");
                short tier = resultSet.getShort("tier");

                if (!petsData.containsKey(ownerUuid)) {
                    List<PetData> petDataList = new ArrayList<>();
                    petDataList.add(new PetData(petId, petName, petType, baby, tier, null));
                    petsData.put(ownerUuid, petDataList);
                } else {
                    petsData.get(ownerUuid).add(new PetData(petId, petName, petType, baby, tier, null));
                }
            }

            resultSet.close();

        } catch (SQLException e) {
            info(color(TAG + "&cAn error occurred caching the pets data!"));
        } finally {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    }

}
