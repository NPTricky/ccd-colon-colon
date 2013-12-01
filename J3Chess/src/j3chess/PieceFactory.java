package j3chess;

import artemis.Entity;

/**
 * a helper factory for piece entities.
 */
public class PieceFactory {

    /** @brief the entity system to create entities into */
    private final EntitySystem mEntitySystem;

    /**
     * @brief constructor for piece factory
     * @param system the entity system to create entities into
     */
    PieceFactory(final EntitySystem system) {
        this.mEntitySystem = system;
    }

    /**
     * @brief helper method to create an piece entity
     * @param type the type of the chess piece
     * @return the chess piece
     */
    public final Entity create(final PieceType type) {
        Entity piece = null;
        switch (type) {
        case BISHOP:
            // piece = new PieceBishop(mEntitySystem).getEntity();
            break;
        case KING:
            piece = new PieceKing(mEntitySystem).getEntity();
            break;
        case KNIGHT:
            // piece = new PieceKnight(mEntitySystem).getEntity();
            break;
        case PAWN:
            // piece = new PiecePawn(mEntitySystem).getEntity();
            break;
        case QUEEN:
            // piece = new PieceQueen(mEntitySystem).getEntity();
            break;
        case ROOK:
            // piece = new PieceRook(mEntitySystem).getEntity();
            break;
        default:
            /* piece = null */
            break;
        }
        return piece;
    }

}
