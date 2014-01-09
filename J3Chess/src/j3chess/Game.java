package j3chess;

import j3chess.components.PieceStatus;
import j3chess.components.Position;
import artemis.ComponentType;
import artemis.Entity;

/**
 * the game class represents a single party of chess.
 */
public class Game {
    /** @brief container for the entities of our game */
    private final EntitySystem mEntitySystem;
    /** @brief represents the 3-person chessboard */
    private final Chessboard mChessboard;
    /** @brief Represents the entities within our game */
    private final PieceFactory mPieceFactory;

    /** Counts the moves of all players.     */
    private int moveCounter = 0;
    /**
     * @brief main class that keeps track of all objects needed
     *        throughout the game.
     */

    public Game() {
        mEntitySystem = new EntitySystem();
        mEntitySystem.initialize();
        mChessboard = new Chessboard(mEntitySystem);
        mPieceFactory = new PieceFactory(mEntitySystem);

        // Create pieces for all three players
        createPieces();
    }

    /**
     * @brief Creates all the pieces for 3 players.
     * Player 1 occupies fields A0 through H1
     * Player 2 occupies fields I0 through P1
     * Player 3 occupies fields Q0 through X1
     */
    public void createPieces() {
        // Create array for each player's starting piece types
        PieceType[][] pieceTypes = new PieceType[2][8];

        // Set row 0 (outermost circle)
        pieceTypes[0][0] = PieceType.ROOK;
        pieceTypes[0][1] = PieceType.KNIGHT;
        pieceTypes[0][2] = PieceType.BISHOP;
        pieceTypes[0][3] = PieceType.KING;
        pieceTypes[0][4] = PieceType.QUEEN;
        pieceTypes[0][5] = PieceType.BISHOP;
        pieceTypes[0][6] = PieceType.KNIGHT;
        pieceTypes[0][7] = PieceType.ROOK;

        // Set row 1 to all pawns
        for (int i = 0; i < pieceTypes[1].length; ++i) {
            pieceTypes[1][i] = PieceType.PAWN;
        }

        // Create pieces for all players
        for (int player = 0; player < 3; ++player) {
            // Create all pieces
            for (int y = 0; y < 2; ++y) {
                for (int x = 0; x < 8; ++x) {
                    // Get field where the piece should be placed
                    Field f = mChessboard.getField((player * 8 + x)
                            % Chessboard.NUMBEROFCOLUMNS, y);

                    // Create an entity according to the piece type on field f
                    // This synchronizes Field and the Piece's PieceStatus component positions
                    Entity p = mPieceFactory.create(pieceTypes[y][x], f);
                }
            }
        }
    }

    /**
     * @return the chessboard
     */
    public final Chessboard getChessboard() {
        return mChessboard;
    }

    public void update() {
        mEntitySystem.getWorld().setDelta(20.0f);
        mEntitySystem.getWorld().process();
    }

    /** @brief increase the move Counter*/
    public void increaseMovesCounter() {
        moveCounter++;
    }

    /** @return the number of moves sum of all Players*/
    public final int getMoveCounter() {
        return moveCounter;
    }
}
