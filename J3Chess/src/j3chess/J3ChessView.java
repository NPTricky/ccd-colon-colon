package j3chess;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import org.jdesktop.application.Application;
import org.jdesktop.application.FrameView;


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
		mMainPanel.add(new JTextField("Bla"));
		
	
		this.getFrame().add(mMainPanel);
		
		
		
		

		
		
		mStatusPanel = new JPanel();
		mMenuBar = new JMenuBar();
		mTabbedPane = new JTabbedPane();
		
        //setComponent(mMainPanel);
        setMenuBar(mMenuBar);
        setStatusBar(mStatusPanel);
        
        createMenuBar();
        
	}       
        
    
	
	
	
	
	private void createMenuBar(){

       		JMenuItem  newGame;
    		newGame = new JMenuItem("New game");
    		newGame.setMnemonic(KeyEvent.VK_N);
    		newGame.addActionListener(new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				new Game();
    				
    			}
    		});
    		
    			// Exit Button
    		JMenuItem  mExit;
    		mExit = new JMenuItem("Exit");
    		mExit.setMnemonic(KeyEvent.VK_C); 
    		mExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK)); //strg + q close the program
    		mExit.addActionListener(new ActionListener() {
    	
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				System.exit(0);
    			}
    	
    		});		
    	
    		// menuTwo menu
    		JMenu menuTwo;
    		menuTwo = new JMenu("Menu Two");
    		menuTwo.setMnemonic(KeyEvent.VK_M); 
    		
    		// game menu
    		JMenu game;
    		game = new JMenu("Game");
    		game.setMnemonic(KeyEvent.VK_G);
    	
    		
    		
    		
    		//build menu
    		menuTwo.add(mExit);
    		game.add(newGame);
    		mMenuBar.add(game);
    		mMenuBar.add(menuTwo);
    		
    	}   
	
}
