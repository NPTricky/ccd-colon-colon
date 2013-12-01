package j3chess.test;

import static org.junit.Assert.*;
import j3chess.Chessboard;
import j3chess.Field;

import org.junit.Test;

public class ChessboardTest {

	@Test
	public final void constructorTest() {
		// This tests whether all edges created by createFields() are symmetric.
		// We don't want any fields Jim and Bob, where Bob is Jim's left neighbor, but
		// Jim is not Bob's right neighbor. That doesn't make sense.
		
		// We assume a 6x24 chessboard here. If the dimensions are changed this test will fail,
		// so adapt it accordingly!
		
		// Let's go, create a new chessboard for testing
		Chessboard cb = new Chessboard();
		
		// Loop through all fields
		for (int circle=0; circle<cb.NUMBEROFCIRCELS; circle++) {
			for (int column=0; column<cb.NUMBEROFCOLUMNS; column++) {
				Field testSubject = cb.getField(column,  circle);
				
				// Test all edges for consistency
				
				if (testSubject.getLeftInnerNeighbor() != null) {
					if(circle!=cb.NUMBEROFCIRCELS-1){
							assertEquals("Left inner edge is not consistent for field ["+column+", "+circle+"]" ,
									testSubject.getLeftInnerNeighbor().getRightOuterNeighbor(), testSubject);
					}else{
						// inner throw the middle
						assertEquals("Left inner edge is not consistent for field ["+column+", "+circle+"]" ,
								testSubject.getLeftInnerNeighbor().getRightInnerNeighbor(), testSubject);
					}
						
					
				}
				if (testSubject.getInnerNeighbor() != null) {
					if(circle!=cb.NUMBEROFCIRCELS-1){
						assertEquals("Inner edge is not consistent for field ["+column+", "+circle+"]",
							testSubject.getInnerNeighbor().getOuterNeighbor(), testSubject);
					}else{
						// inner throw the middle 
						assertEquals("Inner edge is not consistent for field ["+column+", "+circle+"]",
								testSubject.getInnerNeighbor().getInnerNeighbor(), testSubject);
					}				
				}
				if (testSubject.getRightInnerNeighbor() != null) {
					if(circle!=cb.NUMBEROFCIRCELS-1){
						assertEquals("Right inner edge is not consistent for field ["+column+", "+circle+"]",
							testSubject.getRightInnerNeighbor().getLeftOuterNeighbor(), testSubject);
					}else{
						// inner throw the middle 
						assertEquals("Right inner edge is not consistent for field ["+column+", "+circle+"]",
								testSubject.getRightInnerNeighbor().getLeftInnerNeighbor(), testSubject);
					}		
				}
				if (testSubject.getLeftNeighbor() != null) {
					assertEquals("Left edge is not consistent for field ["+column+", "+circle+"]",
							testSubject.getLeftNeighbor().getRightNeighbor(), testSubject);
				}
				if (testSubject.getRightNeighbor() != null) {
					assertEquals("Right edge is not consistent for field ["+column+", "+circle+"]",
							testSubject.getRightNeighbor().getLeftNeighbor(), testSubject);
				}
				if (testSubject.getLeftOuterNeighbor() != null) {
					assertEquals("Left outer edge is not consistent for field ["+column+", "+circle+"]",
							testSubject.getLeftOuterNeighbor().getRightInnerNeighbor(), testSubject);
				}
				if (testSubject.getOuterNeighbor() != null) {
					assertEquals("Outer edge is not consistent for field ["+column+", "+circle+"]",
							testSubject.getOuterNeighbor().getInnerNeighbor(), testSubject);
				}
				if (testSubject.getRightOuterNeighbor() != null) {
					assertEquals("Right outer edge is not consistent for field ["+column+", "+circle+"]",
							testSubject.getRightOuterNeighbor().getLeftInnerNeighbor(), testSubject);
				}
			}
		}
	}

}
