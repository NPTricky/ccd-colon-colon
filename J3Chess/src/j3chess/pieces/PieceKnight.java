package j3chess.pieces;

import j3chess.EntitySystem;
import j3chess.components.Movement;
import j3chess.motion.DirectionGroup;
import j3chess.motion.Motion;
import j3chess.motion.MotionPattern;
import j3chess.utility.Helper;
import artemis.ComponentType;

/**
 * a piece of type knight.
 */
public class PieceKnight extends Piece {

    /**
     * @brief constructor for a piece of type knight
     * @param system
     *            the entity system to create entities into
     */
    public PieceKnight(final EntitySystem system) {
        super(system, PieceType.Knight);
    }

    /**
     * @brief construction of a knight entity
     * @see j3chess.pieces.Piece#construct()
     */
    @Override
    protected final void construct() {
        // movement abilities of the knight...
        final Movement move = new Movement(new MotionPattern(true, new Motion(
                Helper.Direction.fromGroup(DirectionGroup.Horizontal), 2),
                new Motion(Helper.Direction.fromGroup(DirectionGroup.Vertical),
                        1)), new MotionPattern(true, new Motion(
                Helper.Direction.fromGroup(DirectionGroup.Vertical), 2),
                new Motion(Helper.Direction
                        .fromGroup(DirectionGroup.Horizontal), 1)));
        this.getEntity().addComponent(move,
                ComponentType.getTypeFor(Movement.class));
    }

}
