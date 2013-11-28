package j3chess;

import j3chess.Piece;
import j3chess.PieceType;

/**
 * @brief The factory for piece entities.
 *
 */
public class PieceFactory {
	
	private EntitySystem mEntitySystem;
	
	PieceFactory(EntitySystem system) {
		this.mEntitySystem = system;
	}
	
	/**
	 * @brief Create an entity.
	 */
	public Piece create(PieceType type) {
		Piece piece = null;
		switch(type) {
		case BISHOP:
			//piece = new PieceBishop(mEntitySystem);
			break;
		case KING:
			piece = new PieceKing(mEntitySystem);
			break;
		case KNIGHT:
			//piece = new PieceKnight(mEntitySystem);
			break;
		case PAWN:
			//piece = new PiecePawn(mEntitySystem);
			break;
		case QUEEN:
			//piece = new PieceQueen(mEntitySystem);
			break;
		case ROOK:
			//iece = new PieceRook(mEntitySystem);
			break;
		default:
			/* piece = null */
			break;
		
		}
		return piece;
	}

}
