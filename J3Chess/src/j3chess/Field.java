package j3chess;

import j3chess.utility.Vector2d;

public class Field {

	/** @brief Creates a field of the chessboard */
	public Field() {
		mDrawPos = new Vector2d();
	}
	
	/** @brief Sets the left inner neighbor of this field. */
	public void setLeftInnerNeighbor(Field f) {
		// TODO implement
	}
	
	/** @brief Returns the left inner neighbor of this field. */
	public Field getLeftInnerNeighbor() {
		// TODO implement
		return null;
	}
	
	/** @brief Sets the inner neighbor of this field. */
	public void setInnerNeighbor(Field f) {
		// TODO implement
	}
	
	/** @brief Returns the inner neighbor of this field. */
	public Field getInnerNeighbor() {
		// TODO implement
		return null;
	}
	
	/** @brief Sets the right inner neighbor of this field. */
	public void setRightInnerNeighbor(Field f) {
		// TODO implement
	}
	
	/** @brief Returns the right inner neighbor of this field. */
	public Field getRightInnerNeighbor() {
		// TODO implement
		return null;
	}
	
	/** @brief Sets the left neighbor of this field. */
	public void setLeftNeighbor(Field f) {
		// TODO implement
	}
	
	/** @brief Returns the left neighbor of this field. */
	public Field getLeftNeighbor() {
		// TODO implement
		return null;
	}
	
	/** @brief Sets the right neighbor of this field. */
	public void setRightNeighbor(Field f) {
		// TODO implement
	}
	
	/** @brief Returns the right neighbor of this field. */
	public Field getRightNeighbor() {
		// TODO implement
		return null;
	}
	
	/** @brief Sets the left outer neighbor of this field. */
	public void setLeftOuterNeighbor(Field f) {
		// TODO implement
	}
	
	/** @brief Returns the left outer neighbor of this field. */
	public Field getLeftOuterNeighbor() {
		// TODO implement
		return null;
	}
	
	/** @brief Sets the outer neighbor of this field. */
	public void setOuterNeighbor(Field f) {
		// TODO implement
	}
	
	/** @brief Returns the outer neighbor of this field. */
	public Field getOuterNeighbor() {
		// TODO implement
		return null;
	}
	
	/** @brief Sets the right outer neighbor of this field. */
	public void setRightOuterNeighbor(Field f) {
		// TODO implement
	}
	
	/** @brief Returns the right outer neighbor of this field. */
	public Field getRightOuterNeighbor() {
		// TODO implement
		return null;
	}
	
	/**
	 * @brief Sets the position where the field (i.e. its piece) will be drawn.
	 * @param vec	Cartesian 2-dimensional vector specifying the draw position
	 */
	public void setDrawPosition(Vector2d vec) {
		mDrawPos.x = vec.x;
		mDrawPos.y = vec.y;
	}
	
	/** @brief Position where the field is drawn */
	private Vector2d mDrawPos;
	
	// TODO Uncomment when Entity class exists
	/*private Entity mNeighborLeftInner;
	private Entity mNeighborInner;
	private Entity mNeighborRightInner;
	private Entity mNeighborLeft;
	private Entity mNeighborRight;
	private Entity mNeighborLeftOuter;
	private Entity mNeighborOuter;
	private Entity mNeighborRightOuter;*/
	
}
