package j3chess.pieces;

import j3chess.EntitySystem;
import j3chess.components.Movement;
import j3chess.motion.DirectionGroup;
import j3chess.motion.Motion;
import j3chess.motion.MotionPattern;
import j3chess.utility.Helper;
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
        super(system, PieceType.Rook);
    }

    /**
     * @brief construction of a rook entity
     * @see j3chess.pieces.Piece#construct()
     */
    @Override
    protected final void construct() {
        // movement abilities of the rook...
        Movement move = new Movement(
                new MotionPattern(
                        new Motion(Helper.Direction.fromGroup(DirectionGroup.Horizontal), 0)),
                new MotionPattern(
                        new Motion(Helper.Direction.fromGroup(DirectionGroup.Vertical  ), 0)));
        this.getEntity().addComponent(
                move,
                ComponentType.getTypeFor(Movement.class));
    }

}
