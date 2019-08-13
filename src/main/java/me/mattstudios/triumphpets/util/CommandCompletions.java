package me.mattstudios.triumphpets.util;

import me.mattstudios.triumphpets.TriumphPets;
import me.mattstudios.triumphpets.pet.PetType;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class CommandCompletions {

    public static void registerCommandCompletions(TriumphPets plugin) {
        plugin.getCommandManager().getCommandCompletions().registerCompletion("pet_types", c -> Arrays.stream(PetType.values()).map(Objects::toString).collect(Collectors.toList()));
    }

}
