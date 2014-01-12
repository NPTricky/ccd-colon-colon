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
    public PieceFactory(final EntitySystem system) {
        this.mEntitySystem = system;
    }

    /**
     * @brief helper method to create an piece entity
     * @param type the type of the chess piece
     * @param player the player that owns this piece
     * @param field the field where the chess piece is placed
     * @return the chess piece
     */
    public final Entity create(
            final PieceType type,
            final Player player,
            final Field field) {

        Piece piece;

        switch (type) {
        case BISHOP:
            piece = new PieceBishop(mEntitySystem);
            break;
        case KING:
            piece = new PieceKing(mEntitySystem);
            break;
        case KNIGHT:
            piece = new PieceKnight(mEntitySystem);
            break;
        case PAWN:
            piece = new PiecePawn(mEntitySystem);
            break;
        case QUEEN:
            piece = new PieceQueen(mEntitySystem);
            break;
        case ROOK:
            piece = new PieceRook(mEntitySystem);
            break;
        default:
            piece = null;
            break;
        }

        if (piece == null) {
            J3ChessApp.getLogger().error(
                    "unable to create the requested piece");
            return null;
        }

        piece.construct();

        field.setPiece(piece.initializeContext(player, field));

        piece.getEntity().addToWorld(); // last instruction (!)
        return piece.getEntity();
    }
}