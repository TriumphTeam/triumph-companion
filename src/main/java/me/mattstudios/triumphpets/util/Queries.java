package me.mattstudios.triumphpets.util;

public class Queries {

    /**
     * Create Queries.
     */
    public static final String SQLITE_CREATE_PETS =
            "CREATE TABLE IF NOT EXISTS `tp_pets`(\n" +
                    "  `pet_id` INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "  `owner_uuid` VARCHAR(36),\n" +
                    "  `pet_type` VARCHAR(36),\n" +
                    "  `pet_name` VARCHAR(128),\n" +
                    "  `baby` BOOLEAN,\n" +
                    "  `tier` TINYINT\n" +
                    ");";

    public static final String SQLITE_CREATE_PET_INVENTORY =
            "CREATE TABLE IF NOT EXISTS `tp_pet_inventory`(\n" +
                    "  `inv_id` INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "  `pet_id` INTEGER, \n" +
                    "  FOREIGN KEY (`pet_id`) REFERENCES `tp_pets` (`pet_id`)" +
                    ");";

    public static final String SQLITE_CREATE_INVENTORY_ITEMS =
            "CREATE TABLE IF NOT EXISTS `tp_inv_items`(\n" +
                    "  `items_id` INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "  `inv_id` INTEGER,\n" +
                    "  `itemstack` VARCHAR(255),\n" +
                    "  `slot` TINYINT, \n" +
                    "  FOREIGN KEY (`inv_id`) REFERENCES `tp_pet_inventory` (`inv_id`)" +
                    ");";
}
