package j3chess;

import j3chess.components.Paintable;
import j3chess.components.Position;
import artemis.ComponentType;
import artemis.Entity;

/**
 * abstract base class for every entity of type piece.
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
        mPiece = mEntitySystem.getWorld().createEntity();
        mPiece.addComponent(new Position(),
                ComponentType.getTypeFor(Position.class));
        mPiece.addComponent(new Paintable(),
                ComponentType.getTypeFor(Paintable.class));
        // mPiece.addComponent(new Movement(), ComponentType.getTypeFor(Movement.class));
    }

    /**
     * @brief do subclass level processing in this method...
     */
    protected abstract void construct();

    /**
     * @brief getter for the mEntitySystem member
     * @return the entity system
     */
    public final EntitySystem getEntitySystem() {
        return mEntitySystem;
    }

    /**
     * @brief getter for the mPiece member
     * @return the piece entity
     */
    protected final Entity getEntity() {
        return mPiece;
    }

}
