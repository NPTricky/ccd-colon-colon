package artemis;

import java.util.BitSet;
import java.util.HashMap;

import artemis.utils.Bag;
import artemis.utils.ImmutableBag;

/**
 * The most raw entity system. It should not typically be used, but you can
 * create your own entity system handling by extending this. It is recommended
 * that you use the other provided entity system implementations.
 * 
 * @author Arni Arent
 * 
 */
public abstract class EntitySystem implements EntityObserver {
    private final int systemIndex;

    protected World world;

    private final Bag<Entity> actives;

    private final BitSet allSet;
    private final BitSet exclusionSet;
    private final BitSet oneSet;

    private boolean passive;

    private final boolean dummy;

    /**
     * Creates an entity system that uses the specified aspect as a matcher
     * against entities.
     * 
     * @param aspect
     *            to match against entities
     */
    public EntitySystem(final Aspect aspect) {
        actives = new Bag<Entity>();
        allSet = aspect.getAllSet();
        exclusionSet = aspect.getExclusionSet();
        oneSet = aspect.getOneSet();
        systemIndex = SystemIndexManager.getIndexFor(this.getClass());
        dummy = allSet.isEmpty() && oneSet.isEmpty(); // This system can't
                                                      // possibly be interested
                                                      // in any entity, so it
                                                      // must be "dummy"
    }

    /**
     * Called before processing of entities begins.
     */
    protected void begin() {
    }

    public final void process() {
        if (checkProcessing()) {
            begin();
            processEntities(actives);
            end();
        }
    }

    /**
     * Called after the processing of entities ends.
     */
    protected void end() {
    }

    /**
     * Any implementing entity system must implement this method and the logic
     * to process the given entities of the system.
     * 
     * @param entities
     *            the entities this system contains.
     */
    protected abstract void processEntities(ImmutableBag<Entity> entities);

    /**
     * 
     * @return true if the system should be processed, false if not.
     */
    protected abstract boolean checkProcessing();

    /**
     * Override to implement code that gets executed when systems are
     * initialized.
     */
    protected void initialize() {
    };

    /**
     * Called if the system has received a entity it is interested in, e.g.
     * created or a component was added to it.
     * 
     * @param e
     *            the entity that was added to this system.
     */
    protected void inserted(final Entity e) {
    };

    /**
     * Called if a entity was removed from this system, e.g. deleted or had one
     * of it's components removed.
     * 
     * @param e
     *            the entity that was removed from this system.
     */
    protected void removed(final Entity e) {
    };

    /**
     * Will check if the entity is of interest to this system.
     * 
     * @param e
     *            entity to check
     */
    protected final void check(final Entity e) {
        if (dummy) {
            return;
        }

        final boolean contains = e.getSystemBits().get(systemIndex);
        boolean interested = true; // possibly interested, let's try to prove it
                                   // wrong.

        final BitSet componentBits = e.getComponentBits();

        // Check if the entity possesses ALL of the components defined in the
        // aspect.
        if (!allSet.isEmpty()) {
            for (int i = allSet.nextSetBit(0); i >= 0; i = allSet
                    .nextSetBit(i + 1)) {
                if (!componentBits.get(i)) {
                    interested = false;
                    break;
                }
            }
        }

        // Check if the entity possesses ANY of the exclusion components, if it
        // does then the system is not interested.
        if (!exclusionSet.isEmpty() && interested) {
            interested = !exclusionSet.intersects(componentBits);
        }

        // Check if the entity possesses ANY of the components in the oneSet. If
        // so, the system is interested.
        if (!oneSet.isEmpty()) {
            interested = oneSet.intersects(componentBits);
        }

        if (interested && !contains) {
            insertToSystem(e);
        } else if (!interested && contains) {
            removeFromSystem(e);
        }
    }

    private void removeFromSystem(final Entity e) {
        actives.remove(e);
        e.getSystemBits().clear(systemIndex);
        removed(e);
    }

    private void insertToSystem(final Entity e) {
        actives.add(e);
        e.getSystemBits().set(systemIndex);
        inserted(e);
    }

    @Override
    public final void added(final Entity e) {
        check(e);
    }

    @Override
    public final void changed(final Entity e) {
        check(e);
    }

    @Override
    public final void deleted(final Entity e) {
        if (e.getSystemBits().get(systemIndex)) {
            removeFromSystem(e);
        }
    }

    @Override
    public final void disabled(final Entity e) {
        if (e.getSystemBits().get(systemIndex)) {
            removeFromSystem(e);
        }
    }

    @Override
    public final void enabled(final Entity e) {
        check(e);
    }

    protected final void setWorld(final World world) {
        this.world = world;
    }

    protected boolean isPassive() {
        return passive;
    }

    protected void setPassive(final boolean passive) {
        this.passive = passive;
    }

    public ImmutableBag<Entity> getActives() {
        return actives;
    }

    /**
     * Used to generate a unique bit for each system. Only used internally in
     * EntitySystem.
     */
    private static class SystemIndexManager {
        private static int INDEX = 0;
        private static HashMap<Class<? extends EntitySystem>, Integer> indices = new HashMap<Class<? extends EntitySystem>, Integer>();

        private static int getIndexFor(final Class<? extends EntitySystem> es) {
            Integer index = indices.get(es);
            if (index == null) {
                index = INDEX++;
                indices.put(es, index);
            }
            return index;
        }
    }

}
