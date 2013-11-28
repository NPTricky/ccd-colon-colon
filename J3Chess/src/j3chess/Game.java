package j3chess;

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

    /**
     * @brief main class that keeps track of all objects needed
     *        throughout the game.
     */
    public Game() {
        this.mEntitySystem = new EntitySystem();
        this.mChessboard = new Chessboard();
        this.mPieceFactory = new PieceFactory(mEntitySystem);
        initialize();
    }

    /**
     * @brief initialization logic of the game
     */
    public final void initialize() {
        mEntitySystem.initialize();
    }

    /**
     * @return the chessboard
     */
    public final Chessboard getChessboard() {
        return mChessboard;
    }

}
