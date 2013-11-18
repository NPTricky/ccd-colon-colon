package jchess;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Test suite, responsible for running all other tests.
 * Got a new test? Add it to the @SuiteClasses list (comma separated)
 */
@RunWith(Suite.class)
@SuiteClasses( {
	ChessboardTest.class
})
public class JChessTests {
	
}
