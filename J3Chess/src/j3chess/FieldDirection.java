package j3chess;

/**
 * enum of available directions of a single field on the chessboard. eight
 * neighborhood. sorted by maximum distance.
 */
public enum FieldDirection implements Direction {
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

    /*
    @Override
    public FieldDirection translate(final Neighborhood direction) {
        FieldDirection result = null;
        switch (direction) {
        case Right:
            result = FieldDirection.Clockwise;
            break;
        case Top:
            result = FieldDirection.In;
            break;
        case TopRight:
            result = FieldDirection.InClockwise;
            break;
        case TopLeft:
            result = FieldDirection.InCounterClockwise;
            break;
        case Left:
            result = FieldDirection.CounterClockwise;
            break;
        case Bottom:
            result = FieldDirection.Out;
            break;
        case BottomLeft:
            result = FieldDirection.OutCounterClockwise;
            break;
        case BottomRight:
            result = FieldDirection.OutClockwise;
            break;
        default:
            break;
        }
        return result;
    }
    */

}
