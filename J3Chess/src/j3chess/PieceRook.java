package j3chess;

import j3chess.components.Movement;
import j3chess.components.Paintable;
import artemis.ComponentType;

/**
 * a piece of type rook.
 */
public class PieceRook extends Piece {

    /**
     * @brief constructor for a piece of type rook
     * @param system
     *            the entity system to create entities into
     */
    public PieceRook(final EntitySystem system) {
        super(system, PieceType.ROOK);
    }

    /**
     * @brief construction of a rook entity
     * @see j3chess.Piece#construct()
     */
    @Override
    protected final void construct() {
        // visualization of the rook...
        Paintable paint = new Paintable();
        this.getEntity().addComponent(
                paint,
                ComponentType.getTypeFor(Paintable.class));

        // movement abilities of the rook...
        Movement move = new Movement(
                new MotionPattern(
                        new Motion(DirectionGroup.Horizontal, 0, false)),
                new MotionPattern(
                        new Motion(DirectionGroup.Vertical  , 0, false)));
        this.getEntity().addComponent(
                move,
                ComponentType.getTypeFor(Movement.class));
    }

}