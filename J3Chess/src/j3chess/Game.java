package j3chess;
public class Game {

	/**
	 * @brief Main entry point of the program.
	 * @param args
	 */
	public static void main(String[] args) {
		Game g = new Game();
	}
	
	/**
	 * @brief Main class that keeps track of all objects needed throughout the game.
	 */
	private Game() {
		// Create the chessboard instance
		mChessboard = new Chessboard();
	}
	
	/** @brief Represents the 3-person chessboard */
	private Chessboard mChessboard;
	
}
