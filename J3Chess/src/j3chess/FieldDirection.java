package j3chess;

import java.util.EnumSet;

/**
 * enum of available directions of a single field on the chessboard. eight
 * neighborhood. sorted by maximum distance.
 */
public enum FieldDirection implements
        Direction,
        Groupable<EnumSet<FieldDirection>, DirectionGroup> {
    /** @brief into the clockwise direction */
    Clockwise,
    /** @brief towards the center of the chessboard */
    In,
    /**
     * @brief towards the center of the chessboard in a clockwise diagonal
     *        manner
     */
    InClockwise,
    /**
     * @brief towards the center of the chessboard in a counter clockwise
     *        diagonal manner
     */
    InCounterClockwise,
    /** @brief into the counter clockwise direction */
    CounterClockwise,
    /** @brief away from the center of the chessboard */
    Out,
    /**
     * @brief away from the center of the chessboard in a counter clockwise
     *        diagonal manner
     */
    OutCounterClockwise,
    /**
     * @brief away from the center of the chessboard in a clockwise diagonal
     *        manner
     */
    OutClockwise;

    @Override
    public EnumSet<FieldDirection> groupBy(final DirectionGroup group) {
        EnumSet<FieldDirection> result;
        switch (group) {
        case Diagonal:
            result = EnumSet.of(
                    FieldDirection.InClockwise,
                    FieldDirection.InCounterClockwise,
                    FieldDirection.OutCounterClockwise,
                    FieldDirection.OutClockwise);
            break;
        case Horizontal:
            result = EnumSet.of(
                    FieldDirection.Clockwise,
                    FieldDirection.CounterClockwise);
            break;
        case Vertical:
            result = EnumSet.of(
                    FieldDirection.In,
                    FieldDirection.Out);
            break;
        default:
            result = EnumSet.noneOf(FieldDirection.class);
            break;
        }
        return result;
    }
}
