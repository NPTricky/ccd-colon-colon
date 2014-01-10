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
    public final Entity create(final PieceType type, final Player player, Field field) {
        Piece piece = null;
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
            /* piece = null */
            break;
        }

        piece.construct();
        piece.getEntity().addToWorld();

        // Set field, if we have one
        if (field != null) {
            piece.getPositionComponent().setPosition(field);
            field.setPiece(piece.getPieceStatusComponent());
        }

        // Set the player
        piece.getPieceStatusComponent().setPlayer(player);

        // Set the image, dependent on the player
        piece.setPieceImage();

        return piece.getEntity();
    }

    /**
     * @brief helper method to create an piece entity
     * @param type the type of the chess piece
     * @param player the player that owns this piece
     * @return the chess piece
     */
    public final Entity create(final PieceType type, final Player player) {
        return create(type, player, null);
    }
}