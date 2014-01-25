package j3chess.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import j3chess.Chessboard;
import j3chess.Game;
import j3chess.J3ChessView;
import j3chess.motion.Move;
import j3chess.motion.MoveType;

import org.junit.Test;

public class GameViewTest {
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
}
