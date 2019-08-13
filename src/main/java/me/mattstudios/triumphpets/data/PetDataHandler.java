package me.mattstudios.triumphpets.data;

import me.mattstudios.triumphpets.TriumphPets;
import me.mattstudios.triumphpets.data.petdata.PetData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PetDataHandler {

    private TriumphPets plugin;

    private SQLiteManager sqLiteManager;

    private Map<String, List<PetData>> petsData;

    public PetDataHandler(TriumphPets plugin) {
        this.plugin = plugin;

        sqLiteManager = new SQLiteManager(this.plugin);
        petsData = sqLiteManager.cacheData();
        if (petsData == null) petsData = new HashMap<>();
    }

    public List<PetData> getPets(String playerUuid, short tier) {
        List<PetData> petDataList = new ArrayList<>();

        if (petsData.isEmpty()) return petDataList;
        if (!petsData.containsKey(playerUuid)) return petDataList;

        for (PetData petData : petsData.get(playerUuid)) {
            if (petData.getTier() == tier) petDataList.add(petData);
        }
        return petDataList;
    }
}
