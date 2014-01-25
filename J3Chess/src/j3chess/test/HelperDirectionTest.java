package j3chess.test;

import static org.junit.Assert.assertEquals;

import java.util.EnumSet;

import j3chess.DirectionGroup;
import j3chess.FieldDirection;
import j3chess.PieceDirection;
import j3chess.utility.Helper;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This tests is for the direction helper.
 * It Tests if all direction in the right groups and if the
 * Directions will be right converted from pieceDirection to fieldDirection.
 */
public class HelperDirectionTest {

    /**
     * @Brief checks if the direction groups contains the right PieceDirections
     */
    @Test
    public void directionFromGroupTest() {
        EnumSet<PieceDirection> testdirection;

        //checking every direction
        testdirection = Helper.Direction.fromGroup(DirectionGroup.Every);
        for (PieceDirection direction: EnumSet.allOf(PieceDirection.class)) {
            assertEquals(testdirection.contains(direction), true);
        }

        //checking diagonal direction
        testdirection = Helper.Direction.fromGroup(DirectionGroup.Diagonal);
        assertEquals(testdirection.contains(PieceDirection.Backward), false);
        assertEquals(testdirection.contains(PieceDirection.BackwardLeft), true);
        assertEquals(testdirection.contains(PieceDirection.BackwardRight), true);
        assertEquals(testdirection.contains(PieceDirection.Forward), false);
        assertEquals(testdirection.contains(PieceDirection.ForwardLeft), true);
        assertEquals(testdirection.contains(PieceDirection.ForwardRight), true);
        assertEquals(testdirection.contains(PieceDirection.Right), false);
        assertEquals(testdirection.contains(PieceDirection.Left), false);

        //checking horizontal direction
        testdirection = Helper.Direction.fromGroup(DirectionGroup.Horizontal);
        assertEquals(testdirection.contains(PieceDirection.Backward), false);
        assertEquals(testdirection.contains(PieceDirection.BackwardLeft), false);
        assertEquals(testdirection.contains(PieceDirection.BackwardRight), false);
        assertEquals(testdirection.contains(PieceDirection.Forward), false);
        assertEquals(testdirection.contains(PieceDirection.ForwardLeft), false);
        assertEquals(testdirection.contains(PieceDirection.ForwardRight), false);
        assertEquals(testdirection.contains(PieceDirection.Right), true);
        assertEquals(testdirection.contains(PieceDirection.Left), true);

        //checking vertical direction
        testdirection = Helper.Direction.fromGroup(DirectionGroup.Vertical);
        assertEquals(testdirection.contains(PieceDirection.Backward), true);
        assertEquals(testdirection.contains(PieceDirection.BackwardLeft), false);
        assertEquals(testdirection.contains(PieceDirection.BackwardRight), false);
        assertEquals(testdirection.contains(PieceDirection.Forward), true);
        assertEquals(testdirection.contains(PieceDirection.ForwardLeft), false);
        assertEquals(testdirection.contains(PieceDirection.ForwardRight), false);
        assertEquals(testdirection.contains(PieceDirection.Right), false);
        assertEquals(testdirection.contains(PieceDirection.Left), false);
    }

    /**
     * @Brief checks if the PieceDirections are right converted to the FieldDirections
     */
    @Test
    public void toFieldDirectionTest() {
        Boolean crossedCenter = false;

        assertEquals(Helper.Direction.toFieldDirection(
                PieceDirection.Backward, crossedCenter),
                FieldDirection.Out);
        assertEquals(Helper.Direction.toFieldDirection(
                PieceDirection.BackwardLeft, crossedCenter),
                FieldDirection.OutClockwise);
        assertEquals(Helper.Direction.toFieldDirection(
                PieceDirection.BackwardRight, crossedCenter),
                FieldDirection.OutCounterClockwise);
        assertEquals(Helper.Direction.toFieldDirection(
                PieceDirection.Forward, crossedCenter),
                FieldDirection.In);
        assertEquals(Helper.Direction.toFieldDirection(
                PieceDirection.ForwardLeft, crossedCenter),
                FieldDirection.InClockwise);
        assertEquals(Helper.Direction.toFieldDirection(
                PieceDirection.ForwardRight, crossedCenter),
                FieldDirection.InCounterClockwise);
        assertEquals(Helper.Direction.toFieldDirection(
                PieceDirection.Left, crossedCenter),
                FieldDirection.Clockwise);
        assertEquals(Helper.Direction.toFieldDirection(
                PieceDirection.Right, crossedCenter),
                FieldDirection.CounterClockwise);
    }
}
