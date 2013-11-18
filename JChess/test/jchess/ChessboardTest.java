package jchess;

import static org.junit.Assert.*;

import org.junit.Test;

public class ChessboardTest {

	@Test
	public void DimensionTest() {
		Settings s = new Settings();
		Game g = new Game();
		Moves m = new Moves(g);
		Chessboard cb = new Chessboard(s, m);
		
		// I don't even know what to expect here
		// The values don't make any sense at all
		System.out.println(cb.get_height());
		System.out.println(cb.get_widht());
	}
}
