package j3chess;

import artemis.Entity;

/**
 * a class to define a move.
 */
public class Move {

    /** @brief the type of this move */
    private MoveType mMoveType;
    /** @brief the start position of this move */
    private Field mStart;
    /** @brief the target position of this move */
    private Field mTarget;
    /** @brief the piece executing the move */
    private Entity mPiece;
    /** @brief the description of this move in chess notation */
    private String mDescription;

    /**
     * @brief default constructor for a move
     */
    public Move() {
    }

    /**
     * @brief complete constructor for a move
     * @param type type of the move
     * @param start start of the move
     * @param target target of the move
     */
    public Move(
            final MoveType type,
            final Field start,
            final Field target) {
        this.mMoveType = type;
        this.mStart = start;
        this.mTarget = target;
    }

    /**
     * @brief custom to string for easy translation into chess notation
     *
     * file - column
     * rank - row
     *
     * Common Move: <PieceType><mTarget>
     * mType.toAlgebraic() + mTarget.toString()
     * Disambiguating Move: <PieceType><mStart.File><mStart.Rank>...
     * mType.toAlgebraic() + mStart.toString() ...
     *
     * Capture: <PieceType>x<mTarget>
     * mType.toAlgebraic() + "x" + mTarget.toString()
     * Capture En Passant: <mStart.File>x<mTarget>e.p.
     * mStart.toFile() + "x" + mTarget.toString() + "e.p."
     *
     * Promotion: <mTarget><PieceType>
     * mTarget.toString() + mPromotionType.toAlgebraic()
     *
     * [TRIVIAL]
     * Kingside Castling: 0-0
     * Queenside Castling: 0-0-0
     *
     * [SUFFIX]
     * Check: <CompleteMove>+
     * Checkmate: <CompleteMove>#
     *
     * End Of Game: 0-1-0 (Player 1->Player 2->Player 3 [Player 2 Won])
     *
     * @return move in chess notation
     */
    @Override
    public final String toString() {
        return mDescription;
    }

    /**
     * @brief setter for the mDescription member
     * @param description the description
     */
    public final void setDescription(final String description) {
        this.mDescription = description;
    }
}
