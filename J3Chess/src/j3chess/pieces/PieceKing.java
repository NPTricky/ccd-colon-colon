package j3chess.pieces;

import j3chess.EntitySystem;
import j3chess.components.Movement;
import j3chess.motion.DirectionGroup;
import j3chess.motion.Motion;
import j3chess.motion.MotionPattern;
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
        super(system, PieceType.King);
    }

    /**
     * @brief construction of a king entity
     * @see j3chess.pieces.Piece#construct()
     */
    @Override
    protected final void construct() {
        // movement abilities of the king...
        final Movement move = new Movement(new MotionPattern(new Motion(
                Helper.Direction.fromGroup(DirectionGroup.Every), 1)));
        this.getEntity().addComponent(move,
                ComponentType.getTypeFor(Movement.class));
    }

}
