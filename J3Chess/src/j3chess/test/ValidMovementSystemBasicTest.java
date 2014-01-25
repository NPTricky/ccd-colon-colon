package j3chess.test;

import static org.junit.Assert.assertEquals;
import j3chess.Chessboard;
import j3chess.EntitySystem;
import j3chess.Field;
import j3chess.components.ValidMovement;
import j3chess.controller.Player;
import j3chess.pieces.PieceFactory;
import j3chess.pieces.PieceType;
import j3chess.systems.ValidMovementSystem;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class ValidMovementSystemBasicTest {

    static EntitySystem mEntitySystem = new EntitySystem();
    static Chessboard mChessboard = new Chessboard(mEntitySystem);
    static PieceFactory mPieceFactory = new PieceFactory(mEntitySystem);

    @BeforeClass
    public static void setup() {
        mEntitySystem.setSystem(new ValidMovementSystem());
        mEntitySystem.initialize();
    }

    @Test
    public void emptyProcessing() {
        mEntitySystem.process(20);
    }

    @Test
    public void caseOneProcessing() {
        create(PieceType.Rook, Player.ONE, new Field(0, 0));
        mEntitySystem.process(20);
        final ValidMovement valid = mEntitySystem.getWorld().getEntity(0)
                .getComponent(ValidMovement.class);
        assertEquals("Single Piece should not capture anything.", 0, valid
                .getValidCaptureMoves().size());
        mEntitySystem.reset();
    }

    @Test
    public void caseAnnihilationProcessing() {
        final List<PieceType> types = new ArrayList<PieceType>(
                EnumSet.allOf(PieceType.class));
        final List<Player> players = new ArrayList<Player>(
                EnumSet.allOf(Player.class));

        for (int j = 0; j < Chessboard.NUMBEROFCIRCLES; ++j) {
            for (int i = 0; i < Chessboard.NUMBEROFCOLUMNS; ++i) {
                final PieceType type = types.get((int) Math.round(Math.random()
                        * (types.size() - 1)));
                final Player player = players.get((int) Math.round(Math
                        .random() * (players.size() - 1)));
                create(type, player, mChessboard.getField(i, j));
            }
        }

        mEntitySystem.process(20);
        mEntitySystem.reset();
    }

    private void create(final PieceType type, final Player player,
            final Field field) {
        mPieceFactory.create(type, player, field);
    }
}
