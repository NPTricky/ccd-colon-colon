package j3chess;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import j3chess.J3ChessView;

public class J3ChessApp extends SingleFrameApplication {
	public static final Logger mLogger = LogManager.getRootLogger();
	
	public static void main(String[] args) {
		// setup for asynchronous logging
		System.setProperty("Log4jContextSelector","org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
		
		launch(J3ChessApp.class, args);
	}

    public static J3ChessApp getInstance() {
        return Application.getInstance(J3ChessApp.class);
    }
    
    public static Logger getLogger() {
        return mLogger;
    }

	@Override
	protected void startup() {
		mLogger.trace("Starting " + J3ChessApp.class.getName() + "...");
		
	
		
		
		
		
		J3ChessView view = new J3ChessView(this);
		this.show(view);
	}
	
	@Override
	protected void shutdown() {
		mLogger.trace("Shutdown " + J3ChessApp.class.getName() + "...");
	}
}
