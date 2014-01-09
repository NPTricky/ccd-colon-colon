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
