package j3chess;

import j3chess.artemis.World;
import j3chess.artemis.EntityManager;
import j3chess.artemis.ComponentManager;
import j3chess.artemis.managers.GroupManager;
import j3chess.artemis.managers.PlayerManager;

/**
 * @brief Wrapper for the component based entity system.
 * 
 */
public class EntitySystem {
	
	private World mWorld;
	
	EntitySystem() {

	}
	
	/**
	 * @brief Initialize our custom configuration of the artemis entity system.
	 * @return void
	 */
	public void initialize() {
		J3ChessApp.getLogger().trace("Initializing " + EntitySystem.class.getName() + "...");
		this.mWorld = new World();
		
		// manager examples...
		this.mWorld.setManager(new GroupManager());
		this.mWorld.setManager(new PlayerManager());
	}
	
	/**
	 * @brief Convenience getter for the entity systems world.
	 * @return the world
	 */
	public World getWorld() {
		return mWorld;
	}
	
	/**
	 * @brief Convenience getter for the entity systems entity manager.
	 * @return the entity manager
	 */
	public EntityManager getEntityManager() {
		return mWorld.getEntityManager();
	}
	
	/**
	 * @brief Convenience getter for the entity systems component manager.
	 * @return the component manager
	 */
	public ComponentManager getComponentManager() {
		return mWorld.getComponentManager();
	}
	
	/**
	 * @brief Convenience getter for the entity systems tag manager.
	 * @return the tag manager
	 */
	public GroupManager getTagManager() {
		return mWorld.getManager(GroupManager.class);
	}
	
	/**
	 * @brief Convenience getter for the entity systems player manager.
	 * @return the player manager
	 */
	public PlayerManager getPlayerManager() {
		return mWorld.getManager(PlayerManager.class);
	}
	
	
}
