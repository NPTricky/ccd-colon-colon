package j3chess;

import j3chess.utility.Vector2d;

public class Field {

	public Field() {
		mDrawPos = new Vector2d();
	}
	
	public void setLeftInnerNeighbour() {
		// \todo implement
	}
	
	public void setInnerNeighbour() {
		// \todo implement
	}
	
	public void setRightInnerNeighbour() {
		// \todo implement
	}
	
	public void setLeftNeighbour() {
		// \todo implement
	}
	
	public void setRightNeighbour() {
		// \todo implement
	}
	
	public void setLeftOuterNeighbour() {
		// \todo implement
	}
	
	public void setOuterNeighbour() {
		// \todo implement
	}
	
	public void setRightOuterNeighbour() {
		// \todo implement
	}
	
	public void setDrawPosition(Vector2d vec) {
		mDrawPos.x = vec.x;
		mDrawPos.y = vec.y;
	}
	
	/** \brief Position where the field is drawn */
	private Vector2d mDrawPos;
	
	// \todo Uncomment when Entity class exists
	/*private Entity mNeighbourLeftInner;
	private Entity mNeighbourInner;
	private Entity mNeighbourRightInner;
	private Entity mNeighbourLeft;
	private Entity mNeighbourRight;
	private Entity mNeighbourLeftOuter;
	private Entity mNeighbourOuter;
	private Entity mNeighbourRightOuter;*/
	
}
