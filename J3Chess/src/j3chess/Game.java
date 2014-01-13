package j3chess;

import java.util.EnumSet;

/**
 * the game class represents a single party of chess.
 */
public class Game {
    /** @brief delta time */
    public static final float DELTA_TIME = 20.0f;
    /** @brief width of a piece formation */
    public static final int PIECE_FORMATION_WIDTH = 8;
    /** @brief height of a piece formation */
    public static final int PIECE_FORMATION_HEIGHT = 2;

    /** @brief container for the entities of our game */
    private final EntitySystem mEntitySystem;
    /** @brief represents the 3-person chessboard */
    private final Chessboard mChessboard;
    /** @brief Represents the entities within our game */
    private final PieceFactory mPieceFactory;

    /** Counts the moves of all players.     */
    private int mMoveCounter = 0;
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
    public final void createPieces() {
        // Create array for each player's starting piece types
        PieceType[][] pieceFormation =
                new PieceType[PIECE_FORMATION_HEIGHT][PIECE_FORMATION_WIDTH];

        // set types for the outermost circle of the formation
        pieceFormation[0][0] = PieceType.Rook;
        pieceFormation[0][1] = PieceType.Knight;
        pieceFormation[0][2] = PieceType.Bishop;
        pieceFormation[0][3] = PieceType.King;
        pieceFormation[0][4] = PieceType.Queen;
        pieceFormation[0][5] = PieceType.Bishop;
        pieceFormation[0][6] = PieceType.Knight;
        pieceFormation[0][7] = PieceType.Rook;

        // set types for the innermost circle of the formation
        for (int i = 0; i < PIECE_FORMATION_WIDTH; ++i) {
            pieceFormation[1][i] = PieceType.Pawn;
        }

        // for every player...
        for (Player player : EnumSet.allOf(Player.class)) {
            // ...create every piece
            for (int y = 0; y < PIECE_FORMATION_HEIGHT; ++y) {
                for (int x = 0; x < PIECE_FORMATION_WIDTH; ++x) {
                    // Get field where the piece should be placed
                    Field field = mChessboard.getField(
                            (player.ordinal() * PIECE_FORMATION_WIDTH + x)
                                % Chessboard.NUMBEROFCOLUMNS,
                            y);

                    // create an entity on the field
                    mPieceFactory.create(
                            pieceFormation[y][x],
                            player,
                            field);
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

    /**
     * @brief update the game
     */
    public final void update() {
        mEntitySystem.getWorld().setDelta(DELTA_TIME);
        mEntitySystem.getWorld().process();
    }

    /** @brief increase the move counter */
    public final void increaseMovesCounter() {
        mMoveCounter++;
    }

    /** @return the number of moves sum of all players */
    public final int getMoveCounter() {
        return mMoveCounter;
    }
}
