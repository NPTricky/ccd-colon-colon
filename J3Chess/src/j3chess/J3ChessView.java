package j3chess;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import org.jdesktop.application.Application;
import org.jdesktop.application.FrameView;

/**
 *
 */
public class J3ChessView extends FrameView {
    /**
     * Height of the Chessboard.
     */
    public static final int CHESSBOARDHEIGHT = 700;
    /**
     * Width of the Chessboard.
     */
    public static final int CHESSBOARDWIDTH = 700;
    /**
     * Path to the ChessboardImage.
     */
    private String mChessboardImagePath = "src/j3chess/resources/graphics/chessboard.png";

    /**
     * The Panel for drawing Pieces.
     */
    private DrawPanel mMainPanel;
    /**
     * StatusPanel of the GUI.
     */
    private JPanel mStatusPanel;
    /**
     * Menubar for the Usermenu.
     */
    private JMenuBar mMenuBar;

    /**
     * @param app Parent application.
     */
    public J3ChessView(final Application app) {
        super(app);

        initialize();
    }

    /**
     * @Brief initialize the View
     */
    private void initialize() {
        ImageIcon chessboardImage = new ImageIcon(mChessboardImagePath);

        //scale the chessboard
        chessboardImage.setImage(chessboardImage.getImage().
            getScaledInstance(CHESSBOARDWIDTH, CHESSBOARDHEIGHT,
            Image.SCALE_DEFAULT));

        mMainPanel = new DrawPanel(chessboardImage.getImage());

        this.getFrame().add(mMainPanel);

        mStatusPanel = new JPanel();
        mMenuBar = new JMenuBar();

        //setComponent(mMainPanel);
        setMenuBar(mMenuBar);
        setStatusBar(mStatusPanel);

        createMenuBar();

    }


    /**
     * @Brief creates the MenuBar
     */
    private void createMenuBar() {

        JMenuItem newGame;
        newGame = new JMenuItem("New game");
        newGame.setMnemonic(KeyEvent.VK_N);
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                new Game();
            }
        });

        // Exit Button
        JMenuItem  mExit;
        mExit = new JMenuItem("Exit");
        mExit.setMnemonic(KeyEvent.VK_C);
        mExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
        ActionEvent.CTRL_MASK)); // ctrl+q close the program
        mExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                 getFrame().dispose();
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
