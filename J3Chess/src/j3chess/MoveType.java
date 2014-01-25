package j3chess;

/**
 * enum of available move types.
 */
public enum MoveType {
    /** @brief common move */
    Common,
    /** @brief common move with disambiguation requirements */
    Disambiguating,
    /** @brief capture move */
    Capture,
    /** @brief capture move via en passant rule */
    CaptureEnPassant,
    /** @brief promotion of a pawn */
    Promotion,
    /** @brief kingside castling */
    KingsideCastling,
    /** @brief queenside castling */
    QueensideCastling,
}
