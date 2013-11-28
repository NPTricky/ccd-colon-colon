package j3chess;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * the main class of the j3chess project.
 */
public class J3ChessApp extends SingleFrameApplication {
    /** @brief common logger instance for the application */
    private static final Logger LOGGER = LogManager.getRootLogger();

    /**
     * @brief main entry point of the project
     * @param args command line arguments that won't be considered
     */
    public static void main(final String[] args) {
        // setup for asynchronous logging
        System.setProperty(
                "Log4jContextSelector",
                "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");

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
        return LOGGER;
    }

    @Override
    protected final void startup() {
        J3ChessApp.getLogger().trace(
                "Starting " + J3ChessApp.class.getName() + "...");
        final J3ChessView view = new J3ChessView(this);
        this.show(view);
    }

    @Override
    protected final void shutdown() {
        J3ChessApp.getLogger().trace(
                "Shutdown " + J3ChessApp.class.getName() + "...");
    }
}
