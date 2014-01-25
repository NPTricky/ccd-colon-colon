package j3chess.utility;

import j3chess.J3ChessApp;
import j3chess.motion.DirectionGroup;
import j3chess.motion.FieldDirection;
import j3chess.motion.PieceDirection;

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
         * @param group
         *            the enum value to group by
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
            case Every:
                result = EnumSet.allOf(PieceDirection.class);
                break;
            default:
                result = EnumSet.noneOf(PieceDirection.class);
                break;
            }
            return result;
        }

        /**
         * @brief translates a set of field directions into a set of piece
         *        directions
         * @param pieceDirections
         *        the directions of the piece about to be mapped into field
         *        directions
         * @param crossedCenter
         *        whether the piece already crossed the center of the board
         * @return the translated field directions
         */
        public static EnumSet<FieldDirection> toFieldDirections(
                final EnumSet<PieceDirection> pieceDirections,
                final boolean crossedCenter) {

            // initialize the result as an empty set
            final EnumSet<FieldDirection> result = EnumSet
                    .noneOf(FieldDirection.class);

            // translate each direction by itself
            for (final PieceDirection pieceDirection : pieceDirections) {
                result.add(toFieldDirection(pieceDirection, crossedCenter));
            }

            return result;
        }

        /**
         * @brief translates a field direction into a piece direction
         * @param pieceDirection
         *        the direction of the piece about to be mapped into a field
         *        direction
         * @param crossedCenter
         *        whether the piece already crossed the center of the board
         * @return the translated field direction
         */
        public static FieldDirection toFieldDirection(
                final PieceDirection pieceDirection,
                final boolean crossedCenter) {

            FieldDirection result = null;
            if (crossedCenter) {
                switch (pieceDirection) {
                case Right:
                    result = FieldDirection.Clockwise;
                    break;
                case Forward:
                    result = FieldDirection.Out;
                    break;
                case ForwardRight:
                    result = FieldDirection.OutClockwise;
                    break;
                case ForwardLeft:
                    result = FieldDirection.OutCounterClockwise;
                    break;
                case Left:
                    result = FieldDirection.CounterClockwise;
                    break;
                case Backward:
                    result = FieldDirection.In;
                    break;
                case BackwardLeft:
                    result = FieldDirection.InCounterClockwise;
                    break;
                case BackwardRight:
                    result = FieldDirection.InClockwise;
                    break;
                default:
                    J3ChessApp.getLogger().error(
                            "Impossible to map PieceDirection "
                            + pieceDirection.toString() + " into a"
                            + "FieldDirection.");
                    break;
                }
            } else { // !crossedCenter
                switch (pieceDirection) {
                case Right:
                    result = FieldDirection.CounterClockwise;
                    break;
                case Forward:
                    result = FieldDirection.In;
                    break;
                case ForwardRight:
                    result = FieldDirection.InCounterClockwise;
                    break;
                case ForwardLeft:
                    result = FieldDirection.InClockwise;
                    break;
                case Left:
                    result = FieldDirection.Clockwise;
                    break;
                case Backward:
                    result = FieldDirection.Out;
                    break;
                case BackwardLeft:
                    result = FieldDirection.OutClockwise;
                    break;
                case BackwardRight:
                    result = FieldDirection.OutCounterClockwise;
                    break;
                default:
                    J3ChessApp.getLogger().error(
                            "Impossible to map PieceDirection "
                            + pieceDirection.toString() + " into a"
                            + "FieldDirection.");
                    break;
                }
            }
            return result;
        }

    }

}
