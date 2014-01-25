package j3chess;

import artemis.ComponentManager;
import artemis.EntityManager;
import artemis.World;
import artemis.managers.GroupManager;
import artemis.managers.PlayerManager;

/**
 * wrapper for the component based entity system.
 */
public class EntitySystem {

    /** @brief the world or scene of all the entities */
    private World mWorld;

    /**
     * @brief empty default constructor of the entity system
     */
    public EntitySystem() {
        J3ChessApp.getLogger().trace(
                "Initializing " + EntitySystem.class.getName() + "...");

        reset();
        initialize();
    }

    /**
     * @brief Initialize our custom configuration of the artemis entity system.
     */
    public final void initialize() {
        mWorld.initialize();
    }

    /**
     * @brief Resets the world behind the entity system.
     */
    public final void reset() {
        this.mWorld = new World();

        // managers...
        this.mWorld.setManager(new GroupManager());
        this.mWorld.setManager(new PlayerManager());
    }

    /**
     * @brief getter for the entity systems world.
     * @return the world
     */
    public final World getWorld() {
        return mWorld;
    }

    /**
     * @brief getter for the entity systems entity manager.
     * @return the entity manager
     */
    public final EntityManager getEntityManager() {
        return mWorld.getEntityManager();
    }

    /**
     * @brief getter for the entity systems component manager.
     * @return the component manager
     */
    public final ComponentManager getComponentManager() {
        return mWorld.getComponentManager();
    }

    /**
     * @brief getter for the entity systems group manager.
     * @return the group manager
     */
    public final GroupManager getGroupManager() {
        return mWorld.getManager(GroupManager.class);
    }

    /**
     * @brief getter for the entity systems player manager.
     * @return the player manager
     */
    public final PlayerManager getPlayerManager() {
        return mWorld.getManager(PlayerManager.class);
    }

    /**
     * @brief add a system to the entity system (manager)
     * @param system
     *            the system to add
     * @param <T>
     *            type of the system to add
     */
    public final <T extends artemis.EntitySystem> void setSystem(final T system) {
        this.mWorld.setSystem(system);
    }

    /**
     * @brief one simulation through all of the processing systems
     * @param delta
     *            time delta
     */
    public final void process(final float delta) {
        this.mWorld.setDelta(delta);
        this.mWorld.process();
    }
}
