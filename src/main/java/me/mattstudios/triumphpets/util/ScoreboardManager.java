package me.mattstudios.triumphpets.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

public class ScoreboardManager {

    private Map<UUID, String> petTeams;
    private Scoreboard scoreboard;

    public ScoreboardManager() {
        this.petTeams = new HashMap<>();
        scoreboard = Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard();
    }

    /**
     * Removes all the teams in memory, to not flood the scoreboard.
     */
    public void removeAll() {
        for (UUID uuid : petTeams.keySet()) {
            if (scoreboard.getTeam(petTeams.get(uuid)) == null) continue;
            Objects.requireNonNull(scoreboard.getTeam(petTeams.get(uuid))).unregister();
        }
    }

    /**
     * Sets the player and the pet to the same team so they never bump on each other.
     *
     * @param pet    The pet entity.
     * @param player The player.
     */
    public void manageTeamCollision(Entity pet, Player player) {
        String teamName;
        if (petTeams.containsKey(player.getUniqueId())) teamName = petTeams.get(player.getUniqueId());
        else teamName = getTeamName();

        Team team = scoreboard.getTeam(teamName) == null ? scoreboard.registerNewTeam(teamName) : scoreboard.getTeam(teamName);

        assert team != null;
        team.addEntry(player.getUniqueId().toString());
        team.addEntry(pet.getUniqueId().toString());
        team.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.FOR_OTHER_TEAMS);

        petTeams.put(player.getUniqueId(), teamName);
    }

    /**
     * Gets a random team name that has not been used before.
     *
     * @return A custom string with the team name. TP-?????
     */
    private String getTeamName() {
        int number = gen();
        while (true) {
            if (petTeams.containsValue("TP-" + number)) number = gen();
            else return "TP-" + number;
        }
    }

    /**
     * Gets a random big number.
     *
     * @return The randomized number.
     */
    private static int gen() {
        Random r = new Random(System.currentTimeMillis());
        return 100000 + r.nextInt(900000);
    }
}
