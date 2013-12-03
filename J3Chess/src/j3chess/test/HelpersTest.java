package j3chess.test;

import static org.junit.Assert.assertEquals;
import j3chess.utility.Helper;

import org.junit.Before;
import org.junit.Test;

/**
 * test the static nested helpers of the helpers class
 */
public class HelpersTest {

    @Before
    public final void initialization() {
        return;
    }

    @Test
    public final void testDirectionHelper() {
        new Helper.DirectionHelper();
    }

}
