package j3chess.test;

import j3chess.EntitySystem;
import j3chess.components.Selection;
import j3chess.gui.J3ChessView;
import j3chess.systems.SelectedSystem;

import org.junit.BeforeClass;
import org.junit.Test;

import artemis.Entity;

public class SelectedSystemBasicTest {

    static EntitySystem mEntitySystem = new EntitySystem();

    @BeforeClass
    public static void setup() {
        mEntitySystem.setSystem(new SelectedSystem(new J3ChessView()));
        mEntitySystem.initialize();
    }

    @Test
    public void process() {
        mEntitySystem.process(0);
    }

    @Test
    public void initialise() {
        final Entity entity_one = mEntitySystem.getWorld().createEntity();
        entity_one.addComponent(new Selection());
        entity_one.addToWorld();

        final Entity entity_two = mEntitySystem.getWorld().createEntity();
        entity_two.addComponent(new Selection());
        entity_two.addToWorld();

        mEntitySystem.reset();
    }
}
