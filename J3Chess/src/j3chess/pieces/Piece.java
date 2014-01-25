package j3chess.pieces;

import j3chess.EntitySystem;
import j3chess.Field;
import j3chess.J3ChessApp;
import j3chess.components.Paintable;
import j3chess.components.PieceContext;
import j3chess.components.Position;
import j3chess.components.ValidMovement;
import j3chess.controller.Player;
import artemis.ComponentType;
import artemis.Entity;

/**
 * abstract base class for the construction of entities of type piece.
 */
public abstract class Piece {

    /** @brief the entity system to create entities into */
    private final EntitySystem mEntitySystem;
    /** @brief the type of the piece */
    private final PieceType mType;
    /** @brief the entity of the piece */
    private Entity mPiece;

    /**
     * @brief constructor of the piece class
     * @param system
     *            the entity system to create entities into
     * @param type
     *            the type of the piece
     */
    public Piece(final EntitySystem system, final PieceType type) {
        this.mEntitySystem = system;
        this.mType = type;
        initialize();
    }

    /**
     * @brief do one time processing in this method...
     */
    private void initialize() {
        // initialize the entity
        mPiece = mEntitySystem.getWorld().createEntity();

        // initialize the default components for an entity of type piece
        mPiece.addComponent(new ValidMovement(),
                ComponentType.getTypeFor(ValidMovement.class));
    }

    /**
     * @brief initialize the piece entity with context information provided by
     *        the factory
     * @param player
     *            the owning player
     * @param field
     *            the field this piece stands on
     */
    protected final void initializeContext(final Player player,
            final Field field) {

        J3ChessApp.getLogger().debug(
                "Building <" + mType.toString() + ">" + " for <"
                        + player.toString() + ">" + " on Field <"
                        + field.toString() + ">");

        mPiece.addComponent(new Position(field),
                ComponentType.getTypeFor(Position.class));
        mPiece.addComponent(new PieceContext(player, mType),
                ComponentType.getTypeFor(PieceContext.class));
        mPiece.addComponent(new Paintable(generateImagePath(player, mType)),
                ComponentType.getTypeFor(Paintable.class));
    }

    /**
     * @brief automatically generates the image path string from the given
     *        context information (player & pieceType)
     * @param player
     *            the owner of this piece
     * @param pieceType
     *            the type of this piece
     * @return the image path string of this piece
     */
    private String generateImagePath(final Player player,
            final PieceType pieceType) {
        final String imagePath = J3ChessApp.RESOURCEPATH + "pieces_"
                + player.name().toLowerCase() + "/"
                + pieceType.name().toLowerCase() + ".png";
        J3ChessApp.getLogger().debug("Path: <" + imagePath + ">");
        return imagePath;
    }

    /**
     * @brief do subclass level processing in this method...
     */
    protected abstract void construct();

    /**
     * @brief getter for the mPiece member
     * @return the piece entity
     */
    public final Entity getEntity() {
        return mPiece;
    }

}
