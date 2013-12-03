package j3chess.test;

//import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import j3chess.utility.Vector2d;

import org.junit.Test;

public class Vector2dTest {

    @Test
    public final void constructorTest() {
        // Create zero vector
        Vector2d test = new Vector2d();
        assertEquals("Zero vector X is not assigned correctly", test.x, 0, 0);
        assertEquals("Zero vector Y is not assigned correctly", test.y, 0, 0);
        // Define test cases
        float[][] testCases = {{10,	15},
                {-3,	22},
                {22,	-10},
                {-2.2819f, 173.23f},
                {(float) Math.PI, (float) Math.E}, };

        for (int i = 0; i < testCases.length; ++i) {
            // Create vectors to be tested
            Vector2d v = new Vector2d(testCases[i][0], testCases[i][1]);
            // We can assume 0 tolerance since we are only using float values
            assertEquals("Vector X coordinate is not assigned correctly", testCases[i][0], v.x, 0);
            assertEquals("Vector Y coordinate is not assigned correctly", testCases[i][1], v.y, 0);
        }
    }

    @Test
    public final void polarCoordinateTest() {
        // Define test cases, format {r, rho}
        float[][] testCases = {{0, 1.2345f},
                {1, 0},
                {5.172f, 31.2893f},
                {(float) Math.PI, (float) Math.PI}, };
        // Define expected results, format {x, y}
        // Calculated using:
        //	http://www.random-science-tools.com/maths/coordinate-converter.htm
        float[][] expected = {{0, 0},
                {1, 0},
                {5.131f, -0.6532f},
                {-3.142f, -6.498e-13f}, };

        for (int i = 0; i < testCases.length; ++i) {
            // Create vectors to be tested from polar coordinates
            Vector2d v = Vector2d.fromPolarCoordinates(testCases[i][0], testCases[i][1]);

            // Test with little epsilon
            assertEquals("Vector X coordinate is not calculated correctly", expected[i][0], v.x, 0.001f);
            assertEquals("Vector Y coordinate is not calculated correctly", expected[i][1], v.y, 0.001f);
        }
    }

}
