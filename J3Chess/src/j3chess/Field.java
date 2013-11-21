package j3chess;

public class Field {

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
	
	public void setDrawPosition(float x, float y) {
		mDrawPosX = x;
		mDrawPosY = y;
	}
	
	/** \brief Horizontal position where the field is drawn */
	private float mDrawPosX;
	/** \brief Vertical position where the field is drawn */
	private float mDrawPosY;
	
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
