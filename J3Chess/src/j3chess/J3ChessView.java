package j3chess;

import org.jdesktop.application.Application;
import org.jdesktop.application.FrameView;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;

/**
 *
 */
public class J3ChessView extends FrameView {
	JPanel mMainPanel;
	JPanel mStatusPanel;
	JTabbedPane mTabbedPane;
	JMenuBar mMenuBar;
	
	/**
	 * @param app Parent application.
	 */
	public J3ChessView(Application app) {
		super(app);
		initialize();
	}

	private void initialize() {
		mMainPanel = new JPanel();
		mStatusPanel = new JPanel();
		mMenuBar = new JMenuBar();
		mTabbedPane = new JTabbedPane();
		
        setComponent(mMainPanel);
        setMenuBar(mMenuBar);
        setStatusBar(mStatusPanel);
	}
}
