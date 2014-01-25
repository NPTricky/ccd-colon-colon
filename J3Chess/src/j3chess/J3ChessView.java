package j3chess;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import org.jdesktop.application.FrameView;


/**
 * The main GUI class of the game, which creates the View.
 */
public class J3ChessView extends FrameView {
    /**
     * Height of the Chessboard.
     */
    public static final int CHESSBOARDHEIGHT = 664;
    /**
     * Width of the Chessboard.
     */
    public static final int CHESSBOARDWIDTH = 664;

    /**
     * The width of the NotationPanel.
     */
    public static final int NOTATIONPANELWIDTH = 300;

    /**
     * The height of the NotationPanel.
     */
    public static final int NOTATIONPANELHIGHT = 728;

    /**
     * Height of the DrawPanel.
     */
    private static int mDrawPanelHeight;
    /**
     * Width of the DrawPanel.
     */
    private static int mDrawPanelWidth;
    /**
     * Path to the ChessboardImage.
     */
    private String mChessboardImagePath = "src/j3chess/resources/graphics/chessboard.png";

    /**
     * The NotationPanel for drawing Pieces.
     */
    private NotationPanel mNotationPanel;

    /**
     * DrawPanel of the GUI.
     */
    private DrawPanel mDrawPanel;
    /**
     * StatusPanel of the GUI.
     */
    private JPanel mStatusPanel;
    /**
     * Menubar for the Usermenu.
     */
    private JMenuBar mMenuBar;

    /**
     * @brief Construtor of the View
     */
    public J3ChessView() {
        super(J3ChessApp.getInstance());
        initialize();
    }

    /**
     * @Brief initialize the View
     */
    private void initialize() {
        ImageIcon chessboardImage = new ImageIcon(mChessboardImagePath);
        chessboardImage.setImage(chessboardImage.getImage());

        final int border = 12;
        mDrawPanelWidth = chessboardImage.getIconWidth() + border;
        mDrawPanelHeight = chessboardImage.getIconHeight() + border;


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        //  TODO Read values from image / style file
        final int drawPanelBorder = 8;
        mainPanel.setPreferredSize(new Dimension(mDrawPanelWidth + drawPanelBorder, mDrawPanelHeight + drawPanelBorder));
        mDrawPanel = new DrawPanel(mDrawPanelWidth, mDrawPanelHeight);
        mNotationPanel = new NotationPanel(NOTATIONPANELWIDTH, NOTATIONPANELHIGHT);
        mainPanel.add(mDrawPanel, new GridBagConstraints());

        this.getFrame().getContentPane().setLayout(new FlowLayout());
        this.getFrame().add(mainPanel);
        this.getFrame().add(mNotationPanel);

        mStatusPanel = new JPanel();
        mMenuBar = new JMenuBar();

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
                ((J3ChessApp) getApplication()).startNewGame();
            }
        });

        JMenuItem newGameDialog;
        newGameDialog = new JMenuItem("New gamedialog");
        newGameDialog.setMnemonic(KeyEvent.VK_G);
        newGameDialog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                new NewGameDialog();
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
        game.add(newGameDialog);
        mMenuBar.add(game);
        mMenuBar.add(menuTwo);
    }

    /**
     * @brief adds a move at the end of the NotationPanel.
     * @param move the move to add
     */
    public final void addMove(final String move) {
        mNotationPanel.addMove(move);
    }

    /**
     * @return the persistent Graphics of the Draw Panel as Graphics2D object
     */
    public final Graphics2D getDrawGraphics() {
        return mDrawPanel.getPersistentGraphics();
    }

    /**
     * @brief reset the view for a new game
     */
    public final void reset() {
        mNotationPanel.reset();
        mDrawPanel.repaint();
    }

    /**
     * @brief Sets the current player to be displayed.
     */
    public final void refreshCurrentPlayer() {
        mNotationPanel.refreshCurrentPlayer();
    }

    /**
     * @return the DrawPanelHight
     */
    public final int getDrawPanelHight() {
        return mDrawPanelHeight;
    }
    /**
     * @return the DrawPanelWidth
     */
    public final int getDrawPanelWidth() {
        return mDrawPanelWidth;
    }
}
