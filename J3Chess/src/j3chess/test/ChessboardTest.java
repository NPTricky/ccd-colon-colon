package j3chess.test;

import static org.junit.Assert.*;
import j3chess.Chessboard;
import j3chess.Field;

import org.junit.Test;

public class ChessboardTest {

	@Test
	public void constructorTest() {
		// This tests whether all edges created by createFields() are symmetric.
		// We don't want any fields Jim and Bob, where Bob is Jim's left neighbor, but
		// Jim is not Bob's right neighbor. That doesn't make sense.
		
		// We assume a 6x24 chessboard here. If the dimensions are changed this test will fail,
		// so adapt it accordingly!
		
		// Let's go, create a new chessboard for testing
		Chessboard cb = new Chessboard();
		
		// Loop through all fields
		for (int m=0; m<6; m++) {
			for (int n=0; n<24; n++) {
				Field testSubject = cb.getField(m,  n);
				
				// Test all edges for consistency
				
				if (testSubject.getLeftInnerNeighbor() != null) {
					assertEquals("Left inner edge is not consistent for field ["+m+", "+n+"]",
							testSubject.getLeftInnerNeighbor().getRightOuterNeighbor(), testSubject);
				}
				if (testSubject.getInnerNeighbor() != null) {
					assertEquals("Inner edge is not consistent for field ["+m+", "+n+"]",
							testSubject.getInnerNeighbor().getOuterNeighbor(), testSubject);
				}
				if (testSubject.getRightInnerNeighbor() != null) {
					assertEquals("Right inner edge is not consistent for field ["+m+", "+n+"]",
							testSubject.getRightInnerNeighbor().getLeftOuterNeighbor(), testSubject);
				}
				if (testSubject.getLeftNeighbor() != null) {
					assertEquals("Left edge is not consistent for field ["+m+", "+n+"]",
							testSubject.getLeftNeighbor().getRightNeighbor(), testSubject);
				}
				if (testSubject.getRightNeighbor() != null) {
					assertEquals("Right edge is not consistent for field ["+m+", "+n+"]",
							testSubject.getRightNeighbor().getLeftNeighbor(), testSubject);
				}
				if (testSubject.getLeftOuterNeighbor() != null) {
					assertEquals("Left outer edge is not consistent for field ["+m+", "+n+"]",
							testSubject.getLeftOuterNeighbor().getRightInnerNeighbor(), testSubject);
				}
				if (testSubject.getOuterNeighbor() != null) {
					assertEquals("Outer edge is not consistent for field ["+m+", "+n+"]",
							testSubject.getOuterNeighbor().getInnerNeighbor(), testSubject);
				}
				if (testSubject.getRightOuterNeighbor() != null) {
					assertEquals("Right outer edge is not consistent for field ["+m+", "+n+"]",
							testSubject.getRightOuterNeighbor().getLeftInnerNeighbor(), testSubject);
				}
			}
		}
	}

}
