package j3chess.artemis.systems;

import j3chess.artemis.Aspect;
import j3chess.artemis.Entity;
import j3chess.artemis.EntitySystem;
import j3chess.artemis.utils.ImmutableBag;

/**
 * This system has an empty aspect so it processes no entities, but it still gets invoked.
 * You can use this system if you need to execute some game logic and not have to concern
 * yourself about aspects or entities.
 * 
 * @author Arni Arent
 *
 */
public abstract class VoidEntitySystem extends EntitySystem {

	public VoidEntitySystem() {
		super(Aspect.getEmpty());
	}

	@Override
	protected final void processEntities(ImmutableBag<Entity> entities) {
		processSystem();
	}
	
	protected abstract void processSystem();

	@Override
	protected boolean checkProcessing() {
		return true;
	}

}
