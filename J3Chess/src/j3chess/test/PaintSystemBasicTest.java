package j3chess.test;

import static org.junit.Assert.assertEquals;

import javax.swing.ImageIcon;

import j3chess.EntitySystem;
import j3chess.J3ChessView;
import j3chess.components.Paintable;
import j3chess.systems.PaintSystem;

import org.junit.BeforeClass;
import org.junit.Test;

import artemis.Entity;

public class PaintSystemBasicTest {

    static EntitySystem mEntitySystem = new EntitySystem();

    @BeforeClass
    public static void setup() {
        mEntitySystem.setSystem(new PaintSystem(new J3ChessView()));
        mEntitySystem.initialize();
    }

    @Test
    public void process() {
        mEntitySystem.process(0);
    }

    @Test
    public void initialise() {
        ImageIcon image = new ImageIcon("src/j3chess/resources/graphics/pieces_two/queen.png");

        Entity entity_one = mEntitySystem.getWorld().createEntity();
        entity_one.addComponent(new Paintable("src/j3chess/resources/graphics/pieces_one/king.png"));
        entity_one.addToWorld();

        Entity entity_two = mEntitySystem.getWorld().createEntity();
        entity_two.addComponent(new Paintable(image));
        entity_two.addToWorld();

        mEntitySystem.reset();
    }
}
