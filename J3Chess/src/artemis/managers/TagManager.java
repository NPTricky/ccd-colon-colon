package artemis.managers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import artemis.Entity;
import artemis.Manager;

/**
 * If you need to tag any entity, use this. A typical usage would be to tag
 * entities such as "PLAYER", "BOSS" or something that is very unique.
 * 
 * @author Arni Arent
 * 
 */
public class TagManager extends Manager {
    private final Map<String, Entity> entitiesByTag;
    private final Map<Entity, String> tagsByEntity;

    public TagManager() {
        entitiesByTag = new HashMap<String, Entity>();
        tagsByEntity = new HashMap<Entity, String>();
    }

    public void register(final String tag, final Entity e) {
        entitiesByTag.put(tag, e);
        tagsByEntity.put(e, tag);
    }

    public void unregister(final String tag) {
        tagsByEntity.remove(entitiesByTag.remove(tag));
    }

    public boolean isRegistered(final String tag) {
        return entitiesByTag.containsKey(tag);
    }

    public Entity getEntity(final String tag) {
        return entitiesByTag.get(tag);
    }

    public Collection<String> getRegisteredTags() {
        return tagsByEntity.values();
    }

    @Override
    public void deleted(final Entity e) {
        final String removedTag = tagsByEntity.remove(e);
        if (removedTag != null) {
            entitiesByTag.remove(removedTag);
        }
    }

    @Override
    protected void initialize() {
    }

}
