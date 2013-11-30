package j3chess;

/**
 * abstraction of the direction within our different reference systems.
 */
public class Direction {

    /**
     * @brief enum of available directions of a single field on the chessboard
     * eight neighbourship. sorted by maximum distance.
     */
    public enum Field {
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

    /**
     * @brief enum of available directions of a single piece on the chessboard
     * eight neighbourship. sorted by maximum distance.
     */
    public enum Piece {
        /** @brief right from the piece */
        Right,
        /** @brief forward from the piece */
        Forward,
        /** @brief forward to the right of the piece in a diagonal manner */
        ForwardRight,
        /** @brief forward to the left of the piece in a diagonal manner */
        ForwardLeft,
        /** @brief left from the piece */
        Left,
        /** @brief backward from the piece */
        Backward,
        /** @brief backward to the left of the piece in a diagonal manner */
        BackwardLeft,
        /** @brief backward to the right of the piece in a diagonal manner */
        BackwardRight;
    }

}
