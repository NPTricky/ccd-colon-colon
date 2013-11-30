package j3chess.test;

import static org.junit.Assert.assertEquals;
import j3chess.Direction;
import j3chess.utility.Helpers;

import org.junit.Test;

/**
 * test the static nested helpers of the helpers class
 */
public class HelpersTest {

    @Test
    public final void testDirectionHelper() {
        final Helpers.DirectionHelper helper = new Helpers.DirectionHelper();
        assertEquals("Opposite of CounterClockwise must be Clockwise",
                Direction.Field.Clockwise,
                helper.getOpposite(Direction.Field.CounterClockwise));
        assertEquals("Opposite of BackwardRight must be ForwardLeft",
                Direction.Piece.BackwardRight,
                helper.getOpposite(Direction.Piece.ForwardLeft));
    }

}
