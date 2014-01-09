package j3chess;

import java.awt.Graphics2D;
import java.awt.Image;

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

    /** @brief instance of the J3Chess view that takes displays everything */
    private J3ChessView mView;

    /** @brief Number of Players */
    public static final int NUMBEROFPLAYERS = 3;

    /** @brief Current game, may be null. */
    private Game mGame;

    /**
     * @brief main entry point of the project
     * @param args command line arguments that won't be considered
     */
    public static void main(final String[] args) {
        // setup for asynchronous logging
        System.setProperty(
            "Log4jContextSelector",
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
        mGame = new Game();
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
}
