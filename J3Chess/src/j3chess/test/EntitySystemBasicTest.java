package j3chess.test;

import static org.junit.Assert.assertEquals;
import j3chess.EntitySystem;
import j3chess.components.Position;

import org.junit.BeforeClass;
import org.junit.Test;

import artemis.Component;
import artemis.Entity;
import artemis.World;
import artemis.utils.Bag;

public class EntitySystemBasicTest {

    static EntitySystem mEntitySystem = new EntitySystem();
    static World mWorld = mEntitySystem.getWorld();

    @BeforeClass
    public static void setup() {
    }

    @Test
    public void createEmptyEntity() {
        final Entity emptyEntity = mWorld.createEntity();
        final Bag<Component> componentBag = new Bag<Component>();
        emptyEntity.getComponents(componentBag);
        assertEquals("Entity not empty.", componentBag.size(), 0);
    }

    @Test
    public void createEntityWithComponent() {
        Entity entity = createExampleEntity();
        assertEquals("Entity has no Component.",
                Position.class,
                entity.getComponent(Position.class).getClass());
    }

    @Test
    public void deleteEntityWithComponent() {
        Entity entity = createExampleEntity();
        int entityID = entity.getId();
        entity.deleteFromWorld();
        assertEquals(
                "Entity is still there.",
                null,
                mWorld.getEntity(entityID));
    }

    @Test
    public void deleteComponentFromEntity() {
        Entity entity = createExampleEntity();
        entity.removeComponent(Position.class);
        assertEquals(
                "Component is still there.",
                null,
                entity.getComponent(Position.class));
    }

    private Entity createExampleEntity() {
        final Entity entity = mWorld.createEntity();
        final Position componentInserted = new Position();
        entity.addComponent(componentInserted);
        entity.addToWorld();
        return entity;
    }
}
