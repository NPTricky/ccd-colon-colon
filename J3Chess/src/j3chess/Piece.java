package j3chess;

import j3chess.artemis.Entity;
import j3chess.PieceType;

public abstract class Piece {
	
	protected EntitySystem mEntitySystem;
	protected PieceType mType;
	protected Entity mPiece;
	
	/**
	 * @brief Abstract piece class to create new entities
	 *
	 */
	public Piece(EntitySystem system, PieceType type) {
		this.mEntitySystem = system;
		this.mType = type;
		initialize();
	}
	
	/**
	 * @brief Do one time processing in this method... 
	 */	
	private void initialize() {
		J3ChessApp.getLogger().debug("Building " + mType.toString());
		mPiece = mEntitySystem.getWorld().createEntity();
	}
	
	/**
	 * @brief Do subclass level processing in this method... 
	 */
	protected abstract void construct();

	/**
	 * @return the entity system
	 */
	public EntitySystem getEntitySystem() {
		return mEntitySystem;
	}
	
	/**
	 * @return the type of the piece
	 */
	public PieceType getType() {
		return mType;
	}
	
	/**
	 * @return the piece entity
	 */
	public Entity getPiece() {
		return mPiece;
	}
	
}
