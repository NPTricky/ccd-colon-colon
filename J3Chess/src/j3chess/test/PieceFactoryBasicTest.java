package j3chess.test;

import static org.junit.Assert.assertEquals;
import j3chess.EntitySystem;
import j3chess.Field;
import j3chess.J3ChessApp;
import j3chess.PieceFactory;
import j3chess.PieceType;
import j3chess.Player;
import j3chess.components.Movement;
import j3chess.components.Paintable;
import j3chess.components.PieceContext;
import j3chess.components.Position;

import java.awt.Image;
import java.util.EnumSet;

import javax.swing.ImageIcon;

import org.junit.BeforeClass;
import org.junit.Test;

import artemis.ComponentType;
import artemis.Entity;

public class PieceFactoryBasicTest {

    static EntitySystem mEntitySystem =
            new EntitySystem();
    static PieceFactory mPieceFactory =
            new PieceFactory(mEntitySystem);

    static ComponentType mPieceContextType = ComponentType
            .getTypeFor(PieceContext.class);
    static ComponentType mMovementType = ComponentType
            .getTypeFor(Movement.class);
    static ComponentType mPositionType = ComponentType
            .getTypeFor(Position.class);
    static ComponentType mPaintableType = ComponentType
            .getTypeFor(Paintable.class);
    static Field mDefaultField = new Field(0, 0);

    @BeforeClass
    public static void setup() {
        System.out.println("PieceFactoryBasicTest Setup...");
    }

    @Test
    public void createTest() {
        for (final PieceType type : EnumSet.allOf(PieceType.class)) {
            for (final Player player : EnumSet.allOf(Player.class)) {
                final Entity entity = mPieceFactory.create(type, player,
                        mDefaultField);
                final PieceType entityType = ((PieceContext) entity
                        .getComponent(mPieceContextType)).getPieceType();
                assertEquals(
                        "Factory Method <-> Entity System: PieceType Mismatch",
                        type,
                        entityType);
                final Player entityPlayer = ((PieceContext) entity
                        .getComponent(mPieceContextType)).getPlayer();
                assertEquals(
                        "Factory Method <-> Entity System: Player Mismatch",
                        player,
                        entityPlayer);
                checkCommon(entity);
            }
        }
    }

    private void checkCommon(final Entity entity) {
        // check movement common
        assertEquals(
                "Crossed Center",
                ((Movement) entity.getComponent(mMovementType))
                    .getCrossedCenter(),
                false);

        // check position
        assertEquals(
                "Field Mismatch",
                ((Position) entity.getComponent(mPositionType)).getField(),
                mDefaultField);

        // check paintable
        final Image entityImage = ((Paintable) entity
                .getComponent(mPaintableType)).getImage();
        final PieceContext context = ((PieceContext) entity
                .getComponent(mPieceContextType));
        final String path = J3ChessApp.RESOURCEPATH + "pieces_"
                + context.getPlayer().name().toLowerCase() + "/"
                + context.getPieceType().name().toLowerCase() + ".png";
        final Image testImage = new ImageIcon(path).getImage();
        assertEquals(
                "Image Mismatch",
                entityImage,
                testImage);
    }
}
