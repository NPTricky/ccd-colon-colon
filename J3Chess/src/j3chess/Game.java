package j3chess;

public class Game {
	/** @brief Represents the 3-person chessboard */
	private Chessboard mChessboard;

	/**
	 * @brief Main class that keeps track of all objects needed throughout the game.
	 */
	public Game() {
		setChessboard(new Chessboard());
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
