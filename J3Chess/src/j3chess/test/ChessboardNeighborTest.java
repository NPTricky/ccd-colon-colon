package j3chess.test;

import static org.junit.Assert.assertEquals;
import j3chess.Chessboard;
import j3chess.Field;
import j3chess.FieldDirection;

import org.junit.BeforeClass;
import org.junit.Test;


/**
 * This tests whether all edges created by createFields() are
 *  symmetric. We don't want any fields Jim and Bob, where Bob
 *  is Jim's left neighbor, but Jim isn't Bob's right neighbor.
 *  That doesn't make sense.
 */
public class ChessboardNeighborTest {

    /** Chessboard used for testing. */
    private static Chessboard mChessboard;

    /** Set up the test environment. */
    @BeforeClass
    public static void setup() {
       // Let's go, create a new chessboard for testing
       mChessboard = new Chessboard(null);
    }

    /**
     * This tests whether all edges created by createFields() are
     *  symmetric. We don't want any fields Jim and Bob, where Bob
     *  is Jim's left neighbor, but Jim isn't Bob's right neighbor.
     *  That doesn't make sense.
     */
    @Test
    public final void properConstructor() {
        // Loop through all fields
        for (int circle = 0; circle < Chessboard.NUMBEROFCIRCLES; circle++) {
            for (int column = 0; column < Chessboard.NUMBEROFCOLUMNS; column++) {
                Field testSubject = mChessboard.getField(column, circle);

                // Test all edges for consistency

                if (testSubject.getNeighbor(FieldDirection.InClockwise) != null) {
                    if (circle != Chessboard.NUMBEROFCIRCLES - 1) {
                        assertEquals("Left inner edge is not consistent for field [" + column + ", " + circle + "]" ,
                                testSubject.getNeighbor(FieldDirection.InClockwise).getNeighbor(FieldDirection.OutCounterClockwise),
                                testSubject);
                    } else {
                        // inner throw the middle
                        assertEquals("Left inner edge is not consistent for field [" + column + ", " + circle + "]" ,
                                testSubject.getNeighbor(FieldDirection.InClockwise).getNeighbor(FieldDirection.InCounterClockwise),
                                testSubject);
                    }


                }
                if (testSubject.getNeighbor(FieldDirection.In) != null) {
                    if (circle != Chessboard.NUMBEROFCIRCLES - 1) {
                        assertEquals("Inner edge is not consistent for field [" + column + ", " + circle + "]",
                                testSubject.getNeighbor(FieldDirection.In).getNeighbor(FieldDirection.Out), testSubject);
                    } else {
                        // inner throw the middle
                        assertEquals("Inner edge is not consistent for field [" + column + ", " + circle + "]",
                                testSubject.getNeighbor(FieldDirection.In).getNeighbor(FieldDirection.In), testSubject);
                    }
                }
                if (testSubject.getNeighbor(FieldDirection.InCounterClockwise) != null) {
                    if (circle != Chessboard.NUMBEROFCIRCLES - 1) {
                        assertEquals("Right inner edge is not consistent for field [" + column + ", " + circle + "]",
                                testSubject.getNeighbor(FieldDirection.InCounterClockwise).getNeighbor(FieldDirection.OutClockwise),
                                testSubject);
                    } else {
                        // inner throw the middle
                        assertEquals("Right inner edge is not consistent for field [" + column + ", " + circle + "]",
                                testSubject.getNeighbor(FieldDirection.InCounterClockwise).getNeighbor(FieldDirection.InClockwise),
                                testSubject);
                    }
                }
                if (testSubject.getNeighbor(FieldDirection.Clockwise) != null) {
                    assertEquals("Left edge is not consistent for field [" + column + ", " + circle + "]",
                            testSubject.getNeighbor(FieldDirection.Clockwise).getNeighbor(FieldDirection.CounterClockwise), testSubject);
                }
                if (testSubject.getNeighbor(FieldDirection.CounterClockwise) != null) {
                    assertEquals("Right edge is not consistent for field [" + column + ", " + circle + "]",
                            testSubject.getNeighbor(FieldDirection.CounterClockwise).getNeighbor(FieldDirection.Clockwise), testSubject);
                }
                if (testSubject.getNeighbor(FieldDirection.OutClockwise) != null) {
                    assertEquals("Left outer edge is not consistent for field [" + column + ", " + circle + "]",
                            testSubject.getNeighbor(FieldDirection.OutClockwise).getNeighbor(FieldDirection.InCounterClockwise),
                            testSubject);
                }
                if (testSubject.getNeighbor(FieldDirection.Out) != null) {
                    assertEquals("Outer edge is not consistent for field [" + column + ", " + circle + "]",
                            testSubject.getNeighbor(FieldDirection.Out).getNeighbor(FieldDirection.In), testSubject);
                }
                if (testSubject.getNeighbor(FieldDirection.OutCounterClockwise) != null) {
                    assertEquals("Right outer edge is not consistent for field [" + column + ", " + circle + "]" ,
                            testSubject.getNeighbor(FieldDirection.OutCounterClockwise).getNeighbor(FieldDirection.InClockwise),
                            testSubject);
                }
            }
        }
    }
}
