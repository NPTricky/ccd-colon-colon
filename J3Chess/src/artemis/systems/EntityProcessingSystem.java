package artemis.systems;

import artemis.Aspect;
import artemis.Entity;
import artemis.EntitySystem;
import artemis.utils.ImmutableBag;

/**
 * A typical entity system. Use this when you need to process entities
 * possessing the provided component types.
 * 
 * @author Arni Arent
 * 
 */
public abstract class EntityProcessingSystem extends EntitySystem {

    public EntityProcessingSystem(final Aspect aspect) {
        super(aspect);
    }

    /**
     * Process a entity this system is interested in.
     * 
     * @param e
     *            the entity to process.
     */
    protected abstract void process(Entity e);

    @Override
    protected final void processEntities(final ImmutableBag<Entity> entities) {
        for (int i = 0, s = entities.size(); s > i; i++) {
            process(entities.get(i));
        }
    }

    @Override
    protected boolean checkProcessing() {
        return true;
    }

}
