package j3chess;

import java.awt.Image;

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
}
