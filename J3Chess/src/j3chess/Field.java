package j3chess;

import j3chess.utility.Vector2d;



public class Field {

	/** @brief Position where the field is drawn */
	private Vector2d mDrawPos;

	private Field leftNeighbor;
	private Field rightNeighbor;
	private Field innerNeighbor;
	private Field outerNeighbor;
	
	private Field leftInnerNeighbor;
	private Field rightInnerNeighbor;
	private Field leftOuterNeighbor;
	private Field rightOuterNeighbor;
	
	public Integer circle;
	public int column;
	public char columnNames[] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X'};

	
	
	/** @brief Creates a field of the chessboard */
	public Field(int circle, int column) {
		mDrawPos = new Vector2d();
		this.circle=circle;
		this.column=column;
	}
	
	/** @brief Returns the left neighbor of this field. */
	public Field getLeftNeighbor() {
		return leftNeighbor;
	}
  
	
	/** @brief Sets the left neighbor of this field. */
	public void setLeftNeighbor(Field leftNeighbor) {
		this.leftNeighbor = leftNeighbor;
	}

	/** @brief Returns the right neighbor of this field. */
	public Field getRightNeighbor() {
		return rightNeighbor;
	}
	/** @brief Sets the right neighbor of this field. */
	public void setRightNeighbor(Field rightNeighbor) {
		this.rightNeighbor = rightNeighbor;
	}
	/** @brief Returns the inner neighbor of this field. */
	public Field getInnerNeighbor() {
		return innerNeighbor;
	}

	/** @brief Sets the inner neighbor of this field. */
	public void setInnerNeighbor(Field innerNeighbor) {
		this.innerNeighbor = innerNeighbor;
	}
	/** @brief Returns the outer neighbor of this field. */
	public Field getOuterNeighbor() {
		return outerNeighbor;
	}
	/** @brief Sets the outer neighbor of this field. */
	public void setOuterNeighbor(Field outerNeighbor) {
		this.outerNeighbor = outerNeighbor;
	}

	/** @brief Returns the left inner neighbor of this field. */
	public Field getLeftInnerNeighbor() {
		return leftInnerNeighbor;
	}

	/** @brief Sets the left inner neighbor of this field. */
	public void setLeftInnerNeighbor(Field leftInnerNeighbor) {
		this.leftInnerNeighbor = leftInnerNeighbor;
	}
	/** @brief Returns the right inner neighbor of this field. */
	public Field getRightInnerNeighbor() {
		return rightInnerNeighbor;
	}
	/** @brief Sets the right inner neighbor of this field. */
	public void setRightInnerNeighbor(Field rightInnerNeighbor) {
		this.rightInnerNeighbor = rightInnerNeighbor;
	}
	/** @brief Returns the left outer neighbor of this field. */
	public Field getLeftOuterNeighbor() {
		return leftOuterNeighbor;
	}

	/** @brief Sets the left outer neighbor of this field. */
	public void setLeftOuterNeighbor(Field leftOuterNeighbor) {
		this.leftOuterNeighbor = leftOuterNeighbor;
	}
	/** @brief Returns the right outer neighbor of this field. */
	public Field getRightOuterNeighbor() {
		return rightOuterNeighbor;
	}
	/** @brief Sets the right outer neighbor of this field. */
	public void setRightOuterNeighbor(Field rightOuterNeighbor) {
		this.rightOuterNeighbor = rightOuterNeighbor;
	}

	/** @brief prints the field with his Neighbors - just for debugging */
	public void print(){
		System.out.println(this.toString() + 
				" left:" + leftNeighbor + 
				" right:" + rightNeighbor + 
				" inner:" + innerNeighbor + 
				" Outer:" + outerNeighbor + 
				" LeftOuter:" + leftOuterNeighbor + 
				" RightOuter:" + rightOuterNeighbor + 
				" LeftInner:" + leftInnerNeighbor + 
				" RightInner:" + rightInnerNeighbor);
	}	

	
	public String toString(){
		return columnNames[column] + circle.toString();
	}	

	
	/**
	 * @brief Sets the position where the field (i.e. its piece) will be drawn.
	 * @param vec	Cartesian 2-dimensional vector specifying the draw position
	 */
	public void setDrawPosition(Vector2d vec) {
		mDrawPos.x = vec.x;
		mDrawPos.y = vec.y;
	}
	

	
}
