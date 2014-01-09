package j3chess.utility;

import j3chess.DirectionGroup;
import j3chess.PieceDirection;
import j3chess.FieldDirection;

import java.util.EnumSet;

/**
 * a helper class which aggregates anything helper function related.
 */
public final class Helper {

    /**
     * @brief helper related to directions in general
     */
    public static final class Direction {

        /**
         * @brief returns the piece directions grouped by direction groups
         * @param group the enum value to group by
         * @return grouped piece directions
         */
        public static EnumSet<PieceDirection> fromGroup(
                final DirectionGroup group) {

            EnumSet<PieceDirection> result;
            switch (group) {
            case Diagonal:
                result = EnumSet.of(
                        PieceDirection.ForwardRight,
                        PieceDirection.ForwardLeft,
                        PieceDirection.BackwardLeft,
                        PieceDirection.BackwardRight);
                break;
            case Horizontal:
                result = EnumSet.of(
                        PieceDirection.Right,
                        PieceDirection.Left);
                break;
            case Vertical:
                result = EnumSet.of(
                        PieceDirection.Forward,
                        PieceDirection.Backward);
                break;
            default:
                result = EnumSet.noneOf(PieceDirection.class);
                break;
            }
            return result;
        }

        public static EnumSet<FieldDirection> toFieldDirections(
                final EnumSet<PieceDirection> pieceDirections) {

            return toFieldDirections(pieceDirections, false);
        }

        public static EnumSet<FieldDirection> toFieldDirections(
                final EnumSet<PieceDirection> pieceDirections,
                final boolean crossedCenter) {

            return null;
        }

    }

}
