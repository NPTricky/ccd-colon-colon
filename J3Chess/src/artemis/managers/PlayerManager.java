package artemis.managers;

import java.util.HashMap;
import java.util.Map;

import artemis.Entity;
import artemis.Manager;
import artemis.utils.Bag;
import artemis.utils.ImmutableBag;

/**
 * You may sometimes want to specify to which player an entity belongs to.
 * 
 * An entity can only belong to a single player at a time.
 * 
 * @author Arni Arent
 * 
 */
public class PlayerManager extends Manager {
    private final Map<Entity, String> playerByEntity;
    private final Map<String, Bag<Entity>> entitiesByPlayer;

    public PlayerManager() {
        playerByEntity = new HashMap<Entity, String>();
        entitiesByPlayer = new HashMap<String, Bag<Entity>>();
    }

    public void setPlayer(final Entity e, final String player) {
        playerByEntity.put(e, player);
        Bag<Entity> entities = entitiesByPlayer.get(player);
        if (entities == null) {
            entities = new Bag<Entity>();
            entitiesByPlayer.put(player, entities);
        }
        entities.add(e);
    }

    public ImmutableBag<Entity> getEntitiesOfPlayer(final String player) {
        Bag<Entity> entities = entitiesByPlayer.get(player);
        if (entities == null) {
            entities = new Bag<Entity>();
        }
        return entities;
    }

    public void removeFromPlayer(final Entity e) {
        final String player = playerByEntity.get(e);
        if (player != null) {
            final Bag<Entity> entities = entitiesByPlayer.get(player);
            if (entities != null) {
                entities.remove(e);
            }
        }
    }

    public String getPlayer(final Entity e) {
        return playerByEntity.get(e);
    }

    @Override
    protected void initialize() {
    }

    @Override
    public void deleted(final Entity e) {
        removeFromPlayer(e);
    }

}
