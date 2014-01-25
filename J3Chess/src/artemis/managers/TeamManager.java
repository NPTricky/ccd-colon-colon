package artemis.managers;

import java.util.HashMap;
import java.util.Map;

import artemis.Manager;
import artemis.utils.Bag;
import artemis.utils.ImmutableBag;

/**
 * Use this class together with PlayerManager.
 * 
 * You may sometimes want to create teams in your game, so that some players are
 * team mates.
 * 
 * A player can only belong to a single team.
 * 
 * @author Arni Arent
 * 
 */
public class TeamManager extends Manager {
    private final Map<String, Bag<String>> playersByTeam;
    private final Map<String, String> teamByPlayer;

    public TeamManager() {
        playersByTeam = new HashMap<String, Bag<String>>();
        teamByPlayer = new HashMap<String, String>();
    }

    @Override
    protected void initialize() {
    }

    public String getTeam(final String player) {
        return teamByPlayer.get(player);
    }

    public void setTeam(final String player, final String team) {
        removeFromTeam(player);

        teamByPlayer.put(player, team);

        Bag<String> players = playersByTeam.get(team);
        if (players == null) {
            players = new Bag<String>();
            playersByTeam.put(team, players);
        }
        players.add(player);
    }

    public ImmutableBag<String> getPlayers(final String team) {
        return playersByTeam.get(team);
    }

    public void removeFromTeam(final String player) {
        final String team = teamByPlayer.remove(player);
        if (team != null) {
            final Bag<String> players = playersByTeam.get(team);
            if (players != null) {
                players.remove(player);
            }
        }
    }

}
