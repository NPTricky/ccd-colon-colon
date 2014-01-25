package j3chess.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

public class EntitySystemGroupTest {

    @BeforeClass 
    public static void setup() {
        System.out.println("EntitySystemGroupTest Setup...");
    }

    @Test
    public void test1() {
        assertEquals(2 , 2);
    }
}
