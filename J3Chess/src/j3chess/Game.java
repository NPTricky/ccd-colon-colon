package j3chess;

import j3chess.EntitySystem;
import j3chess.Chessboard;
import j3chess.PieceFactory;

public class Game {
	/** @brief Represents the entities within our game */
	private EntitySystem mEntitySystem;
	/** @brief Represents the 3-person chessboard */
	private Chessboard mChessboard;
	/** @brief Represents the entities within our game */
	private PieceFactory mPieceFactory;

	/**
	 * @brief Main class that keeps track of all objects needed throughout the game.
	 */
	public Game() {
		this.mEntitySystem = new EntitySystem();
		this.mChessboard = new Chessboard();
		this.mPieceFactory = new PieceFactory(mEntitySystem);
	}
	
	public void initialize() {
		mEntitySystem.initialize();
	}
	
	/**
	 * @return the chessboard
	 */
	public Chessboard getChessboard() {
		return mChessboard;
	}

	/**
	 * @param chessboard the chessboard to set
	 */
	public void setChessboard(Chessboard chessboard) {
		this.mChessboard = chessboard;
	}

}
