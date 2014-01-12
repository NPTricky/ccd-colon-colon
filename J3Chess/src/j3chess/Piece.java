package j3chess;

import j3chess.components.Paintable;
import j3chess.components.PieceStatus;
import j3chess.components.Position;
import j3chess.components.Selectable;
import j3chess.components.ValidMovement;
import j3chess.utility.Vector2d;

import javax.swing.ImageIcon;

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
        mPiece.addComponent(new Position(),
                ComponentType.getTypeFor(Position.class));
        mPiece.addComponent(new Selectable(),
                ComponentType.getTypeFor(Selectable.class));
        mPiece.addComponent(new PieceStatus(),
                ComponentType.getTypeFor(PieceStatus.class));
        mPiece.addComponent(new ValidMovement(),
                ComponentType.getTypeFor(ValidMovement.class));
        getPieceStatusComponent().setPieceType(mType);
    }

    /**
     * @brief automatically generates the path of the image and loads it into
     *        the paintable component of the piece
     */
    protected final void loadPieceImage() {
        final String imagePath = J3ChessApp.RESOURCEPATH + "pieces_"
                + getPieceStatusComponent().getPlayer().name().toLowerCase()
                + "/"
                + getPieceStatusComponent().getPieceType().name().toLowerCase()
                + ".png";
        J3ChessApp.getLogger().info("Generated Path: " + imagePath);
        loadPieceImage(imagePath);
    }

    /**
     * @brief loads the image of the corresponding image path into the paintable
     *        component of the piece
     * @param imagePath
     *            path of the image to load
     */
    protected final void loadPieceImage(final String imagePath) {
        mPiece.addComponent(new Paintable(imagePath),
                ComponentType.getTypeFor(Paintable.class));
    }

    /**
     * @brief Get the Position component.
     * @return Position component
     */
    protected final Position getPositionComponent() {
        return (Position) mPiece.getComponent(ComponentType
                .getTypeFor(Position.class));
    }

    /**
     * @brief Get the Selectable component.
     * @return Selectable component
     */
    protected final Selectable getSelectableComponent() {
        return (Selectable) mPiece.getComponent(ComponentType
                .getTypeFor(Selectable.class));
    }

    /**
     * @brief Get the Paintable component.
     * @return Paintable component
     */
    protected final Paintable getPaintableComponent() {
        return (Paintable) mPiece.getComponent(ComponentType
                .getTypeFor(Paintable.class));
    }

    /**
     * @brief Get the PieceStatus component.
     * @return PieceStatus component
     */
    protected final PieceStatus getPieceStatusComponent() {
        return (PieceStatus) mPiece.getComponent(ComponentType
                .getTypeFor(PieceStatus.class));
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
