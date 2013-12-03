package j3chess;

import j3chess.components.Movement;
import j3chess.components.Paintable;
import j3chess.utility.Helper;
import artemis.ComponentType;

/**
 * a piece of type king.
 */
public class PieceKing extends Piece {

    /**
     * @brief constructor for a piece of type king
     * @param system
     *            the entity system to create entities into
     */
    public PieceKing(final EntitySystem system) {
        super(system, PieceType.KING);
    }

    /**
     * @brief construction of a king entity
     * @see j3chess.Piece#construct()
     */
    @Override
    protected final void construct() {
        // visualization of the king...
        Paintable paint = new Paintable();
        this.getEntity().addComponent(
                paint,
                ComponentType.getTypeFor(Paintable.class));

        // movement abilities of the king...
        Movement move = new Movement(
                new MotionPattern(
                        new Motion(Helper.Direction.group(DirectionGroup.Horizontal), 1, false)),
                new MotionPattern(
                        new Motion(Helper.Direction.group(DirectionGroup.Vertical  ), 1, false)),
                new MotionPattern(
                        new Motion(Helper.Direction.group(DirectionGroup.Diagonal  ), 1, false)));
        this.getEntity().addComponent(
                move,
                ComponentType.getTypeFor(Movement.class));
    }

}
