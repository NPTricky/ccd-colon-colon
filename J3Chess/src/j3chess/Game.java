package j3chess;
public class Game {

	public static void main(String[] args) {
		Game g = new Game();
	}
	
	private Game() {
		mChessboard = new Chessboard();
	}
	
	private Chessboard mChessboard;
	
}
