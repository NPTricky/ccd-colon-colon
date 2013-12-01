package j3chess;

import j3chess.artemis.ComponentManager;
import j3chess.artemis.EntityManager;
import j3chess.artemis.World;
import j3chess.artemis.managers.GroupManager;
import j3chess.artemis.managers.PlayerManager;

/**
 * wrapper for the component based entity system.
 */
public class EntitySystem {

    /** @brief the world or scene of all the entities */
    private World mWorld;

    /**
     * @brief empty default constructor of the entity system
     */
    EntitySystem() {

    }

    /**
     * @brief Initialize our custom configuration of the artemis entity system.
     */
    public final void initialize() {
        J3ChessApp.getLogger().trace(
                "Initializing " + EntitySystem.class.getName() + "...");
        this.mWorld = new World();
        // manager examples...
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
     * @brief getter for the entity systems tag manager.
     * @return the tag manager
     */
    public final GroupManager getTagManager() {
        return mWorld.getManager(GroupManager.class);
    }

    /**
     * @brief getter for the entity systems player manager.
     * @return the player manager
     */
    public final PlayerManager getPlayerManager() {
        return mWorld.getManager(PlayerManager.class);
    }

}