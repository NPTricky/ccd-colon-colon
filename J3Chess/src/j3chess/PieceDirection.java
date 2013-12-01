package j3chess;

/**
 * enum of available directions of a single piece on the chessboard
 * eight neighborhood. sorted by maximum distance.
 */
public enum PieceDirection implements Direction {
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

    /*
    @Override
    public Neighborhood translate(final PieceDirection direction) {
        Neighborhood result = null;
        switch (direction) {
        case Right:
            result = Neighborhood.Right;
            break;
        case Forward:
            result = Neighborhood.Top;
            break;
        case ForwardRight:
            result = Neighborhood.TopRight;
            break;
        case ForwardLeft:
            result = Neighborhood.TopLeft;
            break;
        case Left:
            result = Neighborhood.Left;
            break;
        case Backward:
            result = Neighborhood.Bottom;
            break;
        case BackwardLeft:
            result = Neighborhood.BottomLeft;
            break;
        case BackwardRight:
            result = Neighborhood.BottomRight;
            break;
        default:
            break;
        }
        return result;
    }
    */
}