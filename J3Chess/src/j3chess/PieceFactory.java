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
        case Bishop:
            piece = new PieceBishop(mEntitySystem);
            break;
        case King:
            piece = new PieceKing(mEntitySystem);
            break;
        case Knight:
            piece = new PieceKnight(mEntitySystem);
            break;
        case Pawn:
            piece = new PiecePawn(mEntitySystem);
            break;
        case Queen:
            piece = new PieceQueen(mEntitySystem);
            break;
        case Rook:
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
        piece.initializeContext(player, field);

        Entity entity = piece.getEntity();
        field.setPiece(entity);
        entity.addToWorld(); // last instruction (!)
        return entity;
    }
}