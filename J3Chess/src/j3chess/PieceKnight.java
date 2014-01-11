package j3chess;

import j3chess.components.Movement;
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
        super(system, PieceType.KNIGHT);
    }

    /**
     * @brief construction of a knight entity
     * @see j3chess.Piece#construct()
     */
    @Override
    protected final void construct() {
        // movement abilities of the knight...
        Movement move = new Movement(
                new MotionPattern(
                        new Motion(Helper.Direction.fromGroup(DirectionGroup.Horizontal), 2),
                        new Motion(Helper.Direction.fromGroup(DirectionGroup.Vertical  ), 1)),
                new MotionPattern(
                        new Motion(Helper.Direction.fromGroup(DirectionGroup.Vertical  ), 2),
                        new Motion(Helper.Direction.fromGroup(DirectionGroup.Horizontal), 1)));
        move.getPatterns().get(0).setUnblockable(true);
        move.getPatterns().get(1).setUnblockable(true);
        this.getEntity().addComponent(
                move,
                ComponentType.getTypeFor(Movement.class));
    }

}
