package j3chess.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import j3chess.Chessboard;
import j3chess.EntitySystem;
import j3chess.Game;
import j3chess.J3ChessApp;
import j3chess.J3ChessView;
import j3chess.Move;
import j3chess.MoveType;

import org.junit.BeforeClass;
import org.junit.Test;

public class GameBasicTest {
    static EntitySystem mEntitySystem;
    static Chessboard mChessboard;

    /**
     * @brief Provides objects for tests
     */
    @BeforeClass
    public static void setup() {
        mEntitySystem = new EntitySystem();
        mChessboard = new Chessboard(mEntitySystem);
    }

    /**
     * @brief Modifies some game parameters and checks if they are
     *        reset on "New Game".
     */
    @Test
    public final void startGameTest() {
        // Get a game and do some stuff
        J3ChessView view = new J3ChessView();
        Game game = new Game(view);
        assertNotNull(game);
        Chessboard chessboard = game.getChessboard();
        game.selectPiece(chessboard.getField(0, 1).getPiece());
        game.doMove(new Move(MoveType.Common, chessboard.getField(0, 1),
                chessboard.getField(0, 3)));
        game.nextPlayer();

        // Start new game and check whether parameters were reset
        game = new Game(new J3ChessView());
        chessboard = game.getChessboard();
        assertNotNull(game);
        assertEquals(game.getCurrentPlayerID(), 0);
        assertNull(game.getSelectedPiece());
        assertNull(chessboard.getField(0, 3).getPiece());
        assertNotNull(chessboard.getField(0, 1).getPiece());
    }

    /**
     * This test checks whether a field that is clicked on is actually there.
     */
    @Test
    public final void twoWayClick() {
        assertEquals("Field is not what it's meant to be",
                mChessboard.getFieldByXY(0.93f, 0.052f),
                mChessboard.getField(0, 0));
        assertEquals("Field is not what it's meant to be",
                mChessboard.getFieldByXY(0.223f, -0.762f),
                mChessboard.getField(19, 1));
        assertEquals("Field is not what it's meant to be",
                mChessboard.getFieldByXY(0.93f, -0.75f), null);
    }
}