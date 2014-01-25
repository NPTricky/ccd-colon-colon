package j3chess.test;

import static org.junit.Assert.assertEquals;
import j3chess.Chessboard;
import j3chess.EntitySystem;
import j3chess.PieceFactory;

import org.junit.BeforeClass;
import org.junit.Test;

public class ValidMovementSystemBasicTest {

    static EntitySystem mEntitySystem = new EntitySystem();
    static Chessboard mChessboard = new Chessboard(mEntitySystem);
    static PieceFactory mPieceFactory = new PieceFactory(mEntitySystem);

    @BeforeClass
    public static void setup() {
        System.out.println("ValidMovementSystemBasicTest Setup...");
    }

    @Test
    public void test1() {
        assertEquals(2 , 2);
    }
}
