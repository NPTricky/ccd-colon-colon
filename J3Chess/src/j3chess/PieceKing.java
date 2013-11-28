package j3chess;

/**
 * @brief
 *
 */
public class PieceKing extends Piece {

	/**
	 * @param system Entity system to create entities into.
	 */
	public PieceKing(EntitySystem system) {
		super(system,PieceType.KING);
		construct();
	}

	/**
	 * @brief Construction of a king entity.
	 * @see j3chess.Piece#construct()
	 */
	@Override
	protected void construct() {
		//this.mPiece.addComponent(component, type);
	}

}
