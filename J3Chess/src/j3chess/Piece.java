package j3chess;

import j3chess.components.Paintable;
import j3chess.components.PieceStatus;
import j3chess.components.Position;
import j3chess.components.Selectable;
import j3chess.components.ValidMovement;

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
        J3ChessApp.getLogger().debug("Building " + mType.toString());

        // initialize the entity
        mPiece = mEntitySystem.getWorld().createEntity();

        // initialize the default components for an entity of type piece
        mPiece.addComponent(new Selectable(),
                ComponentType.getTypeFor(Selectable.class));
        mPiece.addComponent(new ValidMovement(),
                ComponentType.getTypeFor(ValidMovement.class));
    }

    /**
     * @brief initialize the piece entity with context information provided
     * by the factory
     * @param player the owning player
     * @param field the field this piece stands on
     * @return the piece status component
     */
    protected final PieceStatus initializeContext(
            final Player player,
            final Field field) {

        mPiece.addComponent(new Position(field),
                ComponentType.getTypeFor(Position.class));
        mPiece.addComponent(new PieceStatus(player, mType),
                ComponentType.getTypeFor(PieceStatus.class));
        mPiece.addComponent(new Paintable(generateImagePath(player)),
                ComponentType.getTypeFor(Paintable.class));

        return mPiece.getComponent(PieceStatus.class);
    }

    /**
     * @brief automatically generates the image path string from the given
     * context information
     * @param player the owner of this piece
     * @return the image path string of this piece
     */
    private String generateImagePath(final Player player) {
        final String imagePath = J3ChessApp.RESOURCEPATH + "pieces_"
                + player.name().toLowerCase()
                + "/"
                + mType.name().toLowerCase()
                + ".png";
        J3ChessApp.getLogger().info("Generated Path: " + imagePath);
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
