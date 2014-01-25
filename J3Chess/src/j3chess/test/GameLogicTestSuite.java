package j3chess.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ PieceFactoryTestSuite.class, GameTestSuite.class,
        EntitySystemTestSuite.class, HelperTestSuite.class,
        ChessboardTestSuite.class })
public class GameLogicTestSuite {

    @BeforeClass
    public static void setup() {
    }

    @AfterClass
    public static void cleanup() {
    }

}
