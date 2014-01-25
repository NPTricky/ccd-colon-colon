package j3chess;

import j3chess.components.Selection;
import j3chess.controller.HumanController;
import j3chess.controller.Player;
import j3chess.controller.PlayerController;
import j3chess.gui.J3ChessView;
import j3chess.motion.Move;
import j3chess.pieces.PieceFactory;
import j3chess.pieces.PieceType;
import j3chess.systems.PaintSystem;
import j3chess.systems.SelectedSystem;
import j3chess.systems.ValidMovementSystem;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import artemis.Entity;
import artemis.managers.GroupManager;
import artemis.utils.ImmutableBag;

/**
 * the game class represents a single party of chess.
 */
public class Game {
    /** @brief delta time */
    public static final float DELTA_TIME = 20.0f;
    /** @brief width of a piece formation */
    public static final int PIECE_FORMATION_WIDTH = 8;
    /** @brief height of a piece formation */
    public static final int PIECE_FORMATION_HEIGHT = 2;
    /** @brief selected entity group name */
    public static final String SELECTED_GROUP = "selected";

    /** @brief instance of the J3Chess view that displays everything */
    private final J3ChessView mView;
    /** @brief container for the entities of our game */
    private final EntitySystem mEntitySystem;
    /** @brief represents the 3 person chessboard */
    private final Chessboard mChessboard;
    /** @brief convenience class to create the entities within our game */
    private final PieceFactory mPieceFactory;

    /** @brief the moves of all players */
    private final List<Move> mMoveHistory;
    /** @brief the current player about to do his move */
    private int mCurrentPlayerIndex;
    /** @brief the current players in this game */
    private List<Player> mCurrentPlayers;

    /**
     * @brief main class that keeps track of all objects needed throughout the
     *        game.
     * @param view
     *            J3ChessView for the Game to use
     */
    public Game(final J3ChessView view) {
        mMoveHistory = new ArrayList<Move>();
        mView = view;
        mView.reset();
        mEntitySystem = new EntitySystem();

        // systems...
        mEntitySystem.setSystem(new PaintSystem(view));
        mEntitySystem.setSystem(new ValidMovementSystem());
        mEntitySystem.setSystem(new SelectedSystem(view));
        mEntitySystem.initialize();

        mChessboard = new Chessboard(mEntitySystem);
        mPieceFactory = new PieceFactory(mEntitySystem);
        mCurrentPlayerIndex = 0;

        initializePieces();
        initializePlayers(EnumSet.allOf(Player.class));

    }

    /**
     * @brief initializes all the pieces for 3 players. Player 1 occupies fields
     *        A0 through H1 Player 2 occupies fields I0 through P1 Player 3
     *        occupies fields Q0 through X1
     */
    public final void initializePieces() {
        // Create array for each player's starting piece types
        final PieceType[][] pieceFormation = new PieceType[PIECE_FORMATION_HEIGHT][PIECE_FORMATION_WIDTH];

        // set types for the outermost circle of the formation
        pieceFormation[0][0] = PieceType.Rook;
        pieceFormation[0][1] = PieceType.Knight;
        pieceFormation[0][2] = PieceType.Bishop;
        pieceFormation[0][3] = PieceType.King;
        pieceFormation[0][4] = PieceType.Queen;
        pieceFormation[0][5] = PieceType.Bishop;
        pieceFormation[0][6] = PieceType.Knight;
        pieceFormation[0][7] = PieceType.Rook;

        // set types for the innermost circle of the formation
        for (int i = 0; i < PIECE_FORMATION_WIDTH; ++i) {
            pieceFormation[1][i] = PieceType.Pawn;
        }

        // for every player...
        for (final Player player : EnumSet.allOf(Player.class)) {
            // ...create every piece
            for (int y = 0; y < PIECE_FORMATION_HEIGHT; ++y) {
                for (int x = 0; x < PIECE_FORMATION_WIDTH; ++x) {
                    // get the field where the piece should be placed
                    final Field field = mChessboard.getField((player.ordinal()
                            * PIECE_FORMATION_WIDTH + x)
                            % Chessboard.NUMBEROFCOLUMNS, y);
                    create(pieceFormation[y][x], player, field);
                }
            }
        }
    }

    /**
     * @brief initializes the players of the game
     * @param currentPlayers
     *            the enum set of players
     */
    public final void initializePlayers(final EnumSet<Player> currentPlayers) {
        this.mCurrentPlayers = new ArrayList<Player>(currentPlayers);
    }

    /**
     * @return the chessboard
     */
    public final Chessboard getChessboard() {
        return mChessboard;
    }

    /**
     * @brief update the game
     */
    public final void update() {
        mEntitySystem.process(DELTA_TIME);
    }

    /** @return the number of moves already played */
    public final int getMoveCounter() {
        return mMoveHistory.size();
    }

    /**
     * @brief execute a move
     * @param move
     *            the move to be executed
     */
    public final void doMove(final Move move) {
        // display the move in the table
        mView.addMove(move.toString(), this);

        // internally save the move
        mMoveHistory.add(move);

        // Finally execute the move
        move.execute();
        nextPlayer();
    }

    /**
     * @brief create a piece on the board
     * @param pieceType
     *            type of the piece
     * @param player
     *            owner of the piece
     * @param field
     *            on this field
     */
    public final void create(final PieceType pieceType, final Player player,
            final Field field) {
        mPieceFactory.create(pieceType, player, field);
    }

    /**
     * @brief switch to the next player
     */
    public final void nextPlayer() {
        mCurrentPlayerIndex = (mCurrentPlayerIndex + 1)
                % mCurrentPlayers.size();

        // Clear selection
        clearSelection();

        mView.refreshCurrentPlayer();
    }

    /**
     * @brief Get the ID of the player who currently has a turn.
     * @return ID of the player in the range 0..2
     */
    public final int getCurrentPlayerID() {
        return mCurrentPlayerIndex;
    }

    /**
     * @brief Notify the game about a click on a field
     * @param clickedField
     *            The field the user clicked on
     */
    public final void notifyFieldClicked(final Field clickedField) {
        // Check if there is a Human Controller currently active
        final PlayerController currentController = mCurrentPlayers.get(
                mCurrentPlayerIndex).getPlayerController();
        if (currentController.getClass() == HumanController.class) {
            final HumanController controller = (HumanController) currentController;
            controller.notifyFieldClicked(clickedField, this);
        }
    }

    /**
     * @brief Get the currently selected piece
     * @return Selected entity, or null if none
     */
    public final Entity getSelectedPiece() {
        // Get all entities in group selected
        final ImmutableBag<Entity> selectedEntities = mEntitySystem
                .getGroupManager().getEntities(SELECTED_GROUP);

        Entity result = null;
        if (selectedEntities.size() == 1) {
            // Exactly one selected piece, great!
            result = selectedEntities.get(0);
        }
        return result;
    }

    /**
     * @brief Selects a piece on the chessboard. Previous selections are
     *        cleared.
     * @param clickedEntity
     *            The piece to be selected
     */
    public final void selectPiece(final Entity clickedEntity) {
        // Remove previous selection (should be only 1 entity)
        clearSelection();

        // Select clicked entity
        mEntitySystem.getGroupManager().add(clickedEntity, SELECTED_GROUP);
        clickedEntity.addComponent(new Selection());
        clickedEntity.changedInWorld();
        J3ChessApp.getLogger().info("Selected entity " + clickedEntity);
    }

    /**
     * @brief Deselects the currently selected piece, if any.
     */
    public final void clearSelection() {
        // Get the group manager
        final GroupManager groupManager = mEntitySystem.getGroupManager();

        // Remove previous selection (should be only 1 entity)
        final ImmutableBag<Entity> selectedEntities = groupManager
                .getEntities(SELECTED_GROUP);
        for (int i = 0; i < selectedEntities.size(); ++i) {
            selectedEntities.get(i).removeComponent(Selection.class);
            selectedEntities.get(i).changedInWorld();
            groupManager.remove(selectedEntities.get(i), SELECTED_GROUP);
        }
    }
}
