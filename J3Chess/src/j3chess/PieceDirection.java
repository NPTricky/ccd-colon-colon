package j3chess;

/**
 * enum of available directions of a single piece on the chessboard
 * eight neighborhood. sorted by maximum distance.
 */
public enum PieceDirection implements Direction, TranslatableToGeneric<PieceDirection> {
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

    @Override
    public GenericDirection translate(final PieceDirection direction) {
        GenericDirection result = null;
        switch (direction) {
        case Right:
            result = GenericDirection.Right;
            break;
        case Forward:
            result = GenericDirection.Top;
            break;
        case ForwardRight:
            result = GenericDirection.TopRight;
            break;
        case ForwardLeft:
            result = GenericDirection.TopLeft;
            break;
        case Left:
            result = GenericDirection.Left;
            break;
        case Backward:
            result = GenericDirection.Bottom;
            break;
        case BackwardLeft:
            result = GenericDirection.BottomLeft;
            break;
        case BackwardRight:
            result = GenericDirection.BottomRight;
            break;
        default:
            break;
        }
        return result;
    }

}