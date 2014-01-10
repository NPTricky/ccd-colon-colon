package j3chess;

import j3chess.components.Movement;
import j3chess.components.Paintable;
import j3chess.utility.Helper;
import artemis.ComponentType;

/**
 * a piece of type bishop.
 */
public class PieceBishop extends Piece {

    /**
     * @brief constructor for a piece of type bishop
     * @param system
     *            the entity system to create entities into
     */
    public PieceBishop(final EntitySystem system) {
        super(system, PieceType.BISHOP);
    }

    /**
     * @brief construction of a bishop entity
     * @see j3chess.Piece#construct()
     */
    @Override
    protected final void construct() {
        // visualization of the bishop...
        final Paintable paint = new Paintable();
        this.getEntity().addComponent(
                paint,
                ComponentType.getTypeFor(Paintable.class));

        // movement abilities of the bishop...
        Movement move = new Movement(
                new MotionPattern(
                        new Motion(
                                Helper.Direction.fromGroup(DirectionGroup.Diagonal), 0)));
        this.getEntity().addComponent(
                move,
                ComponentType.getTypeFor(Movement.class));
    }

}
