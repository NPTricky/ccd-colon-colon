package j3chess.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/** import tests for the piece test suite, */
import j3chess.test.*;
import artemis.*;

/** run with the junit runner suite. */
@RunWith(Suite.class)
@SuiteClasses({
        PieceFactoryTest.class
        })

/**
 * the test suite for any kind of piece
 */
public class PieceTestSuite {

    private j3chess.EntitySystem mEntitySystem;
    private static j3chess.PieceFactory mPieceFactory;

    @Before
    public final void initialization() {
        mEntitySystem = new j3chess.EntitySystem();
        mPieceFactory = new j3chess.PieceFactory(mEntitySystem);
        return;
    }

    @Test
    public final void test() {
        return;
    }

}
