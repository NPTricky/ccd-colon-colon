package j3chess;

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

    /** @brief container for the entities of our game */
    private final EntitySystem mEntitySystem;
    /** @brief represents the 3 person chessboard */
    private final Chessboard mChessboard;
    /** @brief convenience class to create the entities within our game */
    private final PieceFactory mPieceFactory;

    /** @brief the moves of all players */
    private List<Move> mMoveHistory;
    /** @brief the current players in this game */
    private List<Player> mCurrentPlayers;
    /** @brief the current player about to do his move */
    private int mCurrentPlayerIndex;

    /**
     * @brief main class that keeps track of all objects needed throughout the
     *        game.
     */

    public Game() {
        mEntitySystem = new EntitySystem();
        mEntitySystem.initialize();
        mChessboard = new Chessboard(mEntitySystem);
        mPieceFactory = new PieceFactory(mEntitySystem);
        mCurrentPlayerIndex = 0;

        initializePlayers(new ArrayList<Player>(EnumSet.allOf(Player.class)));
        initializePieces();

        mMoveHistory = new ArrayList<Move>();
    }

    /**
     * @brief initializes all the pieces for 3 players. Player 1 occupies fields
     *        A0 through H1 Player 2 occupies fields I0 through P1 Player 3
     *        occupies fields Q0 through X1
     */
    public final void initializePieces() {
        // Create array for each player's starting piece types
        final PieceType[][] formation = new PieceType[PIECE_FORMATION_HEIGHT][PIECE_FORMATION_WIDTH];

        // set types for the outermost circle of the formation
        formation[0][0] = PieceType.Rook;
        formation[0][1] = PieceType.Knight;
        formation[0][2] = PieceType.Bishop;
        formation[0][3] = PieceType.King;
        formation[0][4] = PieceType.Queen;
        formation[0][5] = PieceType.Bishop;
        formation[0][6] = PieceType.Knight;
        formation[0][7] = PieceType.Rook;

        // set types for the innermost circle of the formation
        for (int i = 0; i < PIECE_FORMATION_WIDTH; ++i) {
            formation[1][i] = PieceType.Pawn;
        }

        // for every player...
        for (final Player player : mCurrentPlayers) {
            // ...create every piece
            for (int y = 0; y < PIECE_FORMATION_HEIGHT; ++y) {
                for (int x = 0; x < PIECE_FORMATION_WIDTH; ++x) {
                    // get the field where the piece should be placed
                    final Field field = mChessboard.getField((player.ordinal()
                            * PIECE_FORMATION_WIDTH + x)
                            % Chessboard.NUMBEROFCOLUMNS, y);
                    create(formation[y][x], player, field);
                }
            }
        }
    }

    /**
     * @brief initializes the players of the game
     * @param currentPlayers
     *            the enum set of players
     */
    public final void initializePlayers(final List<Player> currentPlayers) {
        this.mCurrentPlayers = currentPlayers;
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
        mEntitySystem.getWorld().setDelta(DELTA_TIME);
        mEntitySystem.getWorld().process();
        /*
         * while (true) { boolean moveconditionmet = false; Move abouttomove =
         * mCurrentPlayer.getPlayerController().calculateMove(); if (abouttomove
         * != null) { doMove(abouttomove); moveconditionmet = true; } if
         * (moveconditionmet) { nextPlayer(); } }
         */
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
        // TODO other logic to apply a move
        move.execute();
        mMoveHistory.add(move);
        nextPlayer(1);
    }

//    public final void undoMove() {
//        Move move = mMoveHistory.get(mMoveHistory.size());
//        move.unexecute();
//        nextPlayer(-1);
//    }

    /**
     * @brief create a piece on the board
     * @param pieceType
     *            type of the piece
     * @param player
     *            owner of the piece
     * @param field
     *            on this field
     */
    public final void create(
            final PieceType pieceType,
            final Player player,
            final Field field) {
        mPieceFactory.create(pieceType, player, field);
    }

    /**
     * @brief switch to the next player
     * @param count the next player is +1 the player before -1
     */
    public final void nextPlayer(final int count) {
        mCurrentPlayerIndex =
                (mCurrentPlayerIndex + count) % mCurrentPlayers.size();
    }

    /**
     * @brief Notify the game about a click on a field
     * @param clickedField
     *            The field the user clicked on
     */
    public final void notifyFieldClicked(final Field clickedField) {
        // Check if there is a Human Controller currently active
        Player player = mCurrentPlayers.get(mCurrentPlayerIndex);
        if (player.getPlayerController().getClass() == HumanController.class) {
            ((HumanController) player.getPlayerController())
                .notifyFieldClicked(clickedField);
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
        // Get the group manager
        final GroupManager groupManager = mEntitySystem.getGroupManager();

        // Remove previous selection (should be only 1 entity)
        final ImmutableBag<Entity> selectedEntities = mEntitySystem
                .getGroupManager().getEntities(SELECTED_GROUP);
        for (int i = 0; i < selectedEntities.size(); ++i) {
            groupManager.remove(selectedEntities.get(i), SELECTED_GROUP);
        }

        // Select clicked entity
        mEntitySystem.getGroupManager().add(clickedEntity, SELECTED_GROUP);
        J3ChessApp.getLogger().info("Selected entity " + clickedEntity);
    }

    public final void setPlayer(
            final Player player,
            final PlayerController playerController) {

    }
}
