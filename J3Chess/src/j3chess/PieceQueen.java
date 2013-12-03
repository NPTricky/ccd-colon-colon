package j3chess;

import j3chess.components.Movement;
import j3chess.components.Paintable;
import j3chess.utility.Helper;
import artemis.ComponentType;

/**
 * a piece of type queen.
 */
public class PieceQueen extends Piece {

    /**
     * @brief constructor for a piece of type queen
     * @param system
     *            the entity system to create entities into
     */
    public PieceQueen(final EntitySystem system) {
        super(system, PieceType.QUEEN);
    }

    /**
     * @brief construction of a queen entity
     * @see j3chess.Piece#construct()
     */
    @Override
    protected final void construct() {
        // visualization of the queen...
        Paintable paint = new Paintable();
        this.getEntity().addComponent(
                paint,
                ComponentType.getTypeFor(Paintable.class));

        // movement abilities of the queen...
        Movement move = new Movement(
                new MotionPattern(
                        new Motion(Helper.Direction.group(DirectionGroup.Horizontal), 0, false)),
                new MotionPattern(
                        new Motion(Helper.Direction.group(DirectionGroup.Vertical  ), 0, false)),
                new MotionPattern(
                        new Motion(Helper.Direction.group(DirectionGroup.Diagonal  ), 0, false)));
        this.getEntity().addComponent(
                move,
                ComponentType.getTypeFor(Movement.class));
    }

}
