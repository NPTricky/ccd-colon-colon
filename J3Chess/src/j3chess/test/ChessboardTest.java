package j3chess.test;

import static org.junit.Assert.*;
import j3chess.Chessboard;
import j3chess.Field;
import j3chess.FieldDirection;

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
		for (int circle=0; circle<cb.NUMBEROFCIRCLES; circle++) {
			for (int column=0; column<cb.NUMBEROFCOLUMNS; column++) {
				Field testSubject = cb.getField(column,  circle);
				
				// Test all edges for consistency
				
				if (testSubject.getNeighbor(FieldDirection.InClockwise) != null) {
					if(circle!=cb.NUMBEROFCIRCLES-1){
							assertEquals("Left inner edge is not consistent for field ["+column+", "+circle+"]" ,
									testSubject.getNeighbor(FieldDirection.InClockwise).getNeighbor(FieldDirection.OutCounterClockwise), testSubject);
					}else{
						// inner throw the middle
						assertEquals("Left inner edge is not consistent for field ["+column+", "+circle+"]" ,
								testSubject.getNeighbor(FieldDirection.InClockwise).getNeighbor(FieldDirection.InCounterClockwise), testSubject);
					}
						
					
				}
				if (testSubject.getNeighbor(FieldDirection.In) != null) {
					if(circle!=cb.NUMBEROFCIRCLES-1){
						assertEquals("Inner edge is not consistent for field ["+column+", "+circle+"]",
							testSubject.getNeighbor(FieldDirection.In).getNeighbor(FieldDirection.Out), testSubject);
					}else{
						// inner throw the middle 
						assertEquals("Inner edge is not consistent for field ["+column+", "+circle+"]",
								testSubject.getNeighbor(FieldDirection.In).getNeighbor(FieldDirection.In), testSubject);
					}				
				}
				if (testSubject.getNeighbor(FieldDirection.InCounterClockwise) != null) {
					if(circle!=cb.NUMBEROFCIRCLES-1){
						assertEquals("Right inner edge is not consistent for field ["+column+", "+circle+"]",
							testSubject.getNeighbor(FieldDirection.InCounterClockwise).getNeighbor(FieldDirection.OutClockwise), testSubject);
					}else{
						// inner throw the middle 
						assertEquals("Right inner edge is not consistent for field ["+column+", "+circle+"]",
								testSubject.getNeighbor(FieldDirection.InCounterClockwise).getNeighbor(FieldDirection.InClockwise), testSubject);
					}		
				}
				if (testSubject.getNeighbor(FieldDirection.Clockwise) != null) {
					assertEquals("Left edge is not consistent for field ["+column+", "+circle+"]",
							testSubject.getNeighbor(FieldDirection.Clockwise).getNeighbor(FieldDirection.CounterClockwise), testSubject);
				}
				if (testSubject.getNeighbor(FieldDirection.CounterClockwise) != null) {
					assertEquals("Right edge is not consistent for field ["+column+", "+circle+"]",
							testSubject.getNeighbor(FieldDirection.CounterClockwise).getNeighbor(FieldDirection.Clockwise), testSubject);
				}
				if (testSubject.getNeighbor(FieldDirection.OutClockwise) != null) {
					assertEquals("Left outer edge is not consistent for field ["+column+", "+circle+"]",
							testSubject.getNeighbor(FieldDirection.OutClockwise).getNeighbor(FieldDirection.InCounterClockwise), testSubject);
				}
				if (testSubject.getNeighbor(FieldDirection.Out) != null) {
					assertEquals("Outer edge is not consistent for field ["+column+", "+circle+"]",
							testSubject.getNeighbor(FieldDirection.Out).getNeighbor(FieldDirection.In), testSubject);
				}
				if (testSubject.getNeighbor(FieldDirection.OutCounterClockwise) != null) {
					assertEquals("Right outer edge is not consistent for field ["+column+", "+circle+"]",
							testSubject.getNeighbor(FieldDirection.OutCounterClockwise).getNeighbor(FieldDirection.InClockwise), testSubject);
				}
			}
		}
	}

}
