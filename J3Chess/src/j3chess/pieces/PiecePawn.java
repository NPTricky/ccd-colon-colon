package j3chess.pieces;

import j3chess.EntitySystem;
import j3chess.components.Movement;
import j3chess.motion.DirectionGroup;
import j3chess.motion.Motion;
import j3chess.motion.MotionPattern;
import j3chess.motion.PieceDirection;
import j3chess.utility.Helper;

import java.util.EnumSet;

import artemis.ComponentType;

/**
 * a piece of type pawn.
 */
public class PiecePawn extends Piece {

    /**
     * @brief constructor for a piece of type pawn
     * @param system
     *            the entity system to create entities into
     */
    public PiecePawn(final EntitySystem system) {
        super(system, PieceType.Pawn);
    }

    /**
     * @brief construction of a pawn entity
     * @see j3chess.pieces.Piece#construct()
     */
    @Override
    protected final void construct() {
        // movement abilities of the pawn...
        final Movement move = new Movement(
                new MotionPattern(EnumSet.of(PieceDirection.Forward),
                        new Motion(Helper.Direction
                                .fromGroup(DirectionGroup.Vertical), 1)),
                new MotionPattern(EnumSet.of(PieceDirection.Forward),
                        new Motion(Helper.Direction
                                .fromGroup(DirectionGroup.Vertical), 2)));
        this.getEntity().addComponent(move,
                ComponentType.getTypeFor(Movement.class));
    }

}
