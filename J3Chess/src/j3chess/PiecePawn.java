package j3chess;

import java.util.EnumSet;

import j3chess.components.Movement;
import j3chess.components.Paintable;
import j3chess.utility.Helper;
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
        super(system, PieceType.PAWN);
    }

    /**
     * @brief construction of a pawn entity
     * @see j3chess.Piece#construct()
     */
    @Override
    protected final void construct() {
        // visualization of the pawn...
        Paintable paint = new Paintable();
        this.getEntity().addComponent(
                paint,
                ComponentType.getTypeFor(Paintable.class));

        // movement abilities of the pawn...
        Movement move = new Movement(
                EnumSet.of(PieceDirection.Forward),
                new MotionPattern(
                        new Motion(Helper.Direction.group(DirectionGroup.Vertical), 1, false)),
                new MotionPattern(
                        new Motion(Helper.Direction.group(DirectionGroup.Vertical), 2, false)));
        this.getEntity().addComponent(
                move,
                ComponentType.getTypeFor(Movement.class));
    }

}