package j3chess.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

public class GameBasicTest {

    @BeforeClass
    public static void setup() {
        System.out.println("GameBasicTest Setup...");
    }

    @Test
    public void test1() {
        assertEquals(2 , 2);
    }
}