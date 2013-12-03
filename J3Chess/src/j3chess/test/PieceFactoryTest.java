package j3chess.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/** import tests for the piece test suite, */
import j3chess.test.*;
import artemis.*;

public class PieceFactoryTest {

    private j3chess.EntitySystem mEntitySystem;
    private static j3chess.PieceFactory mPieceFactory;

    @Before
    public final void initialization() {
        mEntitySystem = new j3chess.EntitySystem();
        mPieceFactory = new j3chess.PieceFactory(mEntitySystem);
        return;
    }

    @Test
    public void test() {
    }

}
