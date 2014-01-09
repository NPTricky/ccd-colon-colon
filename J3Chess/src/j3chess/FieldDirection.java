package j3chess;

/**
 * enum of available directions of a single field on the chessboard.
 * eight neighborhood. sorted by maximum distance.
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
}
