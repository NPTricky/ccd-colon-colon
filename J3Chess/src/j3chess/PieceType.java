package j3chess;

/**
 * enum of available piece types.
 */
public enum PieceType {
    /** @brief the piece type of a king */
    King("K"),
    /** @brief the piece type of a queen */
    Queen("Q"),
    /** @brief the piece type of a rook */
    Rook("R"),
    /** @brief the piece type of a bishop */
    Bishop("B"),
    /** @brief the piece type of a knight */
    Knight("N"),
    /** @brief the piece type of a pawn */
    Pawn("P");

    /** @brief the algebraic notation of this type */
    private String mAlgebraic;

    /**
     * @brief the private constructor of a piece type
     * @param algebraic the algebraic notation for this piece type
     */
    private PieceType(final String algebraic) {
        this.mAlgebraic = algebraic;
    }

    /**
     * @brief getter for the algebraic notation
     * @return algebraic notation of the piece type
     */
    public String toAlgebraic() {
        return mAlgebraic;
    }
}
