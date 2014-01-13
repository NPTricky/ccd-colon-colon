package j3chess;

import j3chess.components.Movement;
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
        super(system, PieceType.Queen);
    }

    /**
     * @brief construction of a queen entity
     * @see j3chess.Piece#construct()
     */
    @Override
    protected final void construct() {
        // movement abilities of the queen...
        Movement move = new Movement(
                new MotionPattern(
                        new Motion(Helper.Direction.fromGroup(DirectionGroup.Horizontal), 0)),
                new MotionPattern(
                        new Motion(Helper.Direction.fromGroup(DirectionGroup.Vertical  ), 0)),
                new MotionPattern(
                        new Motion(Helper.Direction.fromGroup(DirectionGroup.Diagonal  ), 0)));
        this.getEntity().addComponent(
                move,
                ComponentType.getTypeFor(Movement.class));
    }

}
