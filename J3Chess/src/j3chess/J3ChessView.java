package j3chess;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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
    private final String mChessboardImagePath = "src/j3chess/resources/graphics/chessboard.png";

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
        final ImageIcon chessboardImage = new ImageIcon(mChessboardImagePath);
        chessboardImage.setImage(chessboardImage.getImage());

        final int border = 12;
        mDrawPanelWidth = chessboardImage.getIconWidth() + border;
        mDrawPanelHeight = chessboardImage.getIconHeight() + border;

        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        // TODO Read values from image / style file
        final int drawPanelBorder = 8;
        mainPanel.setPreferredSize(new Dimension(mDrawPanelWidth
                + drawPanelBorder, mDrawPanelHeight + drawPanelBorder));
        mDrawPanel = new DrawPanel(mDrawPanelWidth, mDrawPanelHeight);
        mNotationPanel = new NotationPanel(NOTATIONPANELWIDTH,
                NOTATIONPANELHIGHT);
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
     * @brief reads the Abouttext from the About Textfile
     * @return the Abouttext
     */
    private String getAboutFromTextFile() {
        String returnValue = "";
        try {
            final FileReader filereader = new FileReader("about.txt");
            final BufferedReader br = new BufferedReader(filereader);
            String zeile = "";
            while ((zeile = br.readLine()) != null) {
                System.out.println(zeile);
                returnValue += zeile + "\n";
            }
            br.close();
        } catch (final FileNotFoundException e) {
            returnValue = "Aboutfile not found";
        } catch (final IOException e) {
            returnValue = "Aboutfile not readable";
        }
        return returnValue;
    }

    /**
     * @brief creates the About Message
     */
    private void createAboutMessage() {
        JOptionPane.showMessageDialog(null, getAboutFromTextFile(), "About",
                JOptionPane.DEFAULT_OPTION);
    }

    /**
     * @Brief creates the MenuBar
     */
    private void createMenuBar() {
        JMenuItem about;
        about = new JMenuItem("about");
        about.setMnemonic(KeyEvent.VK_A);
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
                ActionEvent.CTRL_MASK));
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                createAboutMessage();
            }
        });

        JMenuItem newGameDialog;
        newGameDialog = new JMenuItem("new game");
        newGameDialog.setMnemonic(KeyEvent.VK_N);
        newGameDialog.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                ActionEvent.CTRL_MASK));
        newGameDialog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                new NewGameDialog();
            }
        });

        // Exit Button
        JMenuItem mExit;
        mExit = new JMenuItem("exit");
        mExit.setMnemonic(KeyEvent.VK_E);
        mExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
                ActionEvent.CTRL_MASK)); // ctrl+q close the program
        mExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                getFrame().dispose();
            }
        });

        // ? menu
        JMenu menuQuestionMark;
        menuQuestionMark = new JMenu("?");
        menuQuestionMark.add(about);

        // game menu
        JMenu game;
        game = new JMenu("game");
        game.setMnemonic(KeyEvent.VK_G);

        // build menu
        game.add(newGameDialog);
        game.add(mExit);
        mMenuBar.add(game);
        mMenuBar.add(menuQuestionMark);
    }

    /**
     * @brief adds a move at the end of the NotationPanel.
     * @param move
     *            the move to add
     * @param game
     *            the game
     */
    public final void addMove(final String move, final Game game) {
        mNotationPanel.addMove(move, game);
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
     * @param game
     *            the game
     */
    public final void refreshCurrentPlayer(final Game game) {
        mNotationPanel.refreshCurrentPlayer(game);
    }

    /**
     * @return the DrawPanelHight
     */
    public final int getDrawPanelHeight() {
        return mDrawPanelHeight;
    }

    /**
     * @return the DrawPanelWidth
     */
    public final int getDrawPanelWidth() {
        return mDrawPanelWidth;
    }
}
