package j3chess;

/**
 * a piece of type king.
 */
public class PieceKing extends Piece {

    /**
     * @brief constructor for a piece of type king
     * @param system the entity system to create entities into
     */
    public PieceKing(final EntitySystem system) {
        super(system, PieceType.KING);
        construct();
    }

    /**
     * @brief construction of a king entity
     * @see j3chess.Piece#construct()
     */
    @Override
    protected void construct() {
        // this.mPiece.addComponent(component, type);
    }

}
