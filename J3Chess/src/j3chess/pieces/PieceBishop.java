package j3chess.pieces;

import j3chess.EntitySystem;
import j3chess.components.Movement;
import j3chess.motion.DirectionGroup;
import j3chess.motion.Motion;
import j3chess.motion.MotionPattern;
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
        super(system, PieceType.Bishop);
    }

    /**
     * @brief construction of a bishop entity
     * @see j3chess.pieces.Piece#construct()
     */
    @Override
    protected final void construct() {
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
