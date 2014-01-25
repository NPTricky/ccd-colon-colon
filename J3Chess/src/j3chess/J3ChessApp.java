package j3chess;

/**
 * \mainpage J3Chess - Chess for 3 players on a circular chessboard
 *
 * \section intro_sec Introduction
 *
 * J3Chess is a chess application, designed for 3 players on a
 * circular chessboard. It is the spiritual successor of <a href=
 * "http://chess.geniusprophecy.com/jchess.html">JChess</a>.
 *
 * \section rules_sec Rules
 *
 * The rules are very similar to the commonly known chess rules. Most
 * the pieces move as in standard chess.
 *
 * The pawn can always move one or two fields at will. It also captures
 * other pieces straight which is different from basic chess.
 *
 * Your figures can move across the center field. The center is not a field
 * itself and can't be occupied by a piece. When moving diagonally across
 * the center, try to follow the dashed lines. The game also assists
 * you by drawing all the legal moves.
 *
 * \section developers_sec For Developers
 *
 * If you are a developer and want to contribute to J3Chess, that's
 * great! There are some things you should know:
 *
 * * J3Chess makes use of the <a href="http://gamadu.com/artemis/">Artemis</a>
 *   framework. You should be familiar with the concepts of component-
 *   driven design.
 * * We try to keep our code base clean. For coding style, please refer
 *   to the <a href="http://www.oracle.com/technetwork/java/codeconv-138413.html">
 *   Java Code Conventions</a>. We used <a href="http://checkstyle.sourceforge.net/">
 *   Checkstyle</a> and <a href="http://findbugs.sourceforge.net/">FindBugs</a>
 *   to help us writing clean code, and we recommend you do too.
 *
 * Have fun coding and thanks for your support!
 */

import j3chess.gui.J3ChessView;

import java.awt.Graphics2D;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * the main class of the j3chess project.
 */
public class J3ChessApp extends SingleFrameApplication {
    /** @brief common logger instance for the application */
    private static final Logger LOG = LogManager.getRootLogger();

    /** @brief instance of the J3Chess view that displays everything */
    private J3ChessView mView;

    /** @brief Number of Players */
    public static final int NUMBEROFPLAYERS = 3;

    /** @brief Home path for the resource files */
    public static final String RESOURCEPATH = "src/j3chess/resources/graphics/";

    /** @brief Current game, may be null. */
    private Game mGame;

    /**
     * @brief main entry point of the project
     * @param args
     *            command line arguments that won't be considered
     */
    public static void main(final String[] args) {
        // setup for asynchronous logging
        System.setProperty("Log4jContextSelector",
                "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");

        // launch the application
        launch(J3ChessApp.class, args);
    }

    /**
     * @brief convenience getter to retrieve the application
     * @return the application as a J3ChessApp
     */
    public static J3ChessApp getInstance() {
        return Application.getInstance(J3ChessApp.class);
    }

    /**
     * @brief getter for the LOGGER member
     * @return the logger
     */
    public static Logger getLogger() {
        return LOG;
    }

    @Override
    protected final void startup() {
        J3ChessApp.getLogger().trace(
                "Starting " + J3ChessApp.class.getName() + "...");
        mView = new J3ChessView();
        this.show(mView);
        startNewGame();
    }

    @Override
    protected final void shutdown() {
        J3ChessApp.getLogger().trace(
                "Shutdown " + J3ChessApp.class.getName() + "...");
    }

    public void startNewGame() {
        mGame = new Game(mView);
        update();
    }

    public void update() {
        mGame.update();
    }

    public Game getGame() {
        return mGame;
    }

    public Graphics2D getDrawGraphics() {
        return mView.getDrawGraphics();
    }

    public J3ChessView getView() {
        return mView;
    }
}
