package j3chess;

import j3chess.utility.Vector2d;

/**
 * @brief Represents the game's board. Keeps track of all the fields an pieces
 * in the game.
 */
public class Chessboard {

	public final int NUMBEROFCIRCELS=6;
	public final int NUMBEROFCOLUMNS=24; 
	/** @brief 6x24 array of all fields in the game */
	private Field mFields[][];
	
	
	/**
	 * @brief Creates new instance of the Chessboard class.
	 */
	public Chessboard() {
		// Create all logical fields
		createFields();
		//set the Neighbors of every Field
		setNeighbors();
		//print all Fields - just for debugging
		printAllFields();
		
		// Calculate the fields' 2D drawing positions
		calculateDrawPositions(0.9f);
	}
	private void printAllFields(){
		for(int circles = 0; circles < NUMBEROFCIRCELS; circles++) {
			for(int columns = 0; columns < NUMBEROFCOLUMNS; columns++) {
				mFields[columns][circles].print();
			}
		}		
	}
	
	private void setNeighbors(){
		for(int circle = 0; circle < NUMBEROFCIRCELS; circle++) {
			for(int column = 0; column < NUMBEROFCOLUMNS; column++) {
				Field field=mFields[column][circle];
				setInner(field,column,circle);
				setOuter(field,column,circle);
				setLeft(field,column,circle);
				setRight(field,column,circle);
				setLeftInner(field,column,circle);
				setLeftOuter(field,column,circle);
				setRightInner(field,column,circle);
				setRightOuter(field,column,circle);
			}
		}		
	}
	
	
	private void setRight(Field field, int column, int circle) {
		field.setRightNeighbor(mFields[((column+1)%NUMBEROFCOLUMNS)][circle]);	
	}
	
	private void setLeft(Field field, int column, int circle) {
		if(column==0){
			field.setLeftNeighbor(mFields[NUMBEROFCOLUMNS-1][circle]);
		}else{
			field.setLeftNeighbor(mFields[column-1][circle]);
		}
	}
	
	private void setInner(Field field, int column, int circle) {
		if(circle<NUMBEROFCIRCELS-1){
			field.setInnerNeighbor(mFields[column][circle+1]);
		}else
			field.setInnerNeighbor(mFields[((column+NUMBEROFCOLUMNS/2)%NUMBEROFCOLUMNS)][circle]);
	}
	
	private void setOuter(Field field, int column, int circle) {
		if(circle>0){
			field.setOuterNeighbor(mFields[column][circle-1]);
		}
	}
	
	private void setRightOuter(Field field, int column, int circle) {
		if(circle>0){
			field.setRightOuterNeighbor(mFields[(column+1)%NUMBEROFCOLUMNS][circle-1]);
		}
	}
	
	private void setLeftOuter(Field field, int column, int circle) {
		if(circle>0){
			if(column>0){
				field.setLeftOuterNeighbor(mFields[column-1][circle-1]);
			}else{
				field.setLeftOuterNeighbor(mFields[NUMBEROFCOLUMNS-1][circle-1]);	
			}
		}
	}
	
	private void setRightInner(Field field, int column, int circle) {
		if(circle<NUMBEROFCIRCELS-1){
			field.setRightInnerNeighbor(mFields[(column+1)%NUMBEROFCOLUMNS][circle+1]);
		}else{
			//diagonal through the middle
			field.setRightInnerNeighbor(mFields[(column+((NUMBEROFCIRCELS-1)*2))%NUMBEROFCOLUMNS][circle]);
		}
	}
	
	private void setLeftInner(Field field, int column, int circle) {
		if(circle<NUMBEROFCIRCELS-1){
			if(column!=0){
				field.setLeftInnerNeighbor(mFields[column-1][circle+1]);
			}else
				field.setLeftInnerNeighbor(mFields[NUMBEROFCOLUMNS-1][circle+1]);
		}else{
			//diagonal through the middle
			field.setLeftInnerNeighbor(mFields[(column+(NUMBEROFCOLUMNS-((NUMBEROFCIRCELS-1)*2)))%NUMBEROFCOLUMNS][circle]);
		}
	}
	/**
	 * @brief Creates all the fields and adds the required connections.
	 */
	private void createFields() {
		mFields=new Field[NUMBEROFCOLUMNS][NUMBEROFCIRCELS];
		for(int i = 0; i < NUMBEROFCOLUMNS; i++) {
			for(int j = 0; j < NUMBEROFCIRCELS; j++) {
				mFields[i][j] = new Field(j,i);
			}
		}
	}
	
	/**
	 * @brief Calculates draw positions for all fields from spheric to cartesian coordinates.
	 * 
	 * Sets the X and Y draw coordinates of the field. Coordinates are calculated in the
	 * range [-1..1], where [0,0] is the center of the board. The coordinates need to be
	 * scaled to the total board size before drawing.
	 * Visualization: https://github.com/NPTricky/ccd-colon-colon/issues/11#issuecomment-29002979
	 * 
	 * @param centerSize	Relative size of the center circle, e.g. 0.2 means 20% of the total diameter 
	 */
	private void calculateDrawPositions(float centerSize) {
		for (int m=0; m<6; m++) {
			for (int n=0; n<24; n++) {
				// Calculate radius of the fields
				// Divide the total radius by 13, where each 2m+1 is the center of one field
				// Can be visualized using the following 13 chars: |1|2|3|4|5|6|
				// Each number marks a center of a field
				float r = (1.f - (2.f * m + 1.f) / (6.f * 2.f));
				// Scale according to centerSize
				r = (1.f-centerSize)*r + centerSize;
				// Calculate angle
				float rho = (float)(2.f * Math.PI * (n/24.f));
				
				// Convert to cartesian
				Vector2d cartesian = Vector2d.fromPolarCoordinates(r, rho);
				
				// Set coordinates of field
				mFields[n][m].setDrawPosition(cartesian);
			}
		}
	}
	
	/**
	 * @brief Returns a field object by its coordinate.
	 * Coordinates are m from the center to the outside and n around the circular chessboard.    
	 * @param m	Coordinate from the center to the outside 
	 * @param n	Coordinate around the circular chessboard
	 * @return Field object
	 */
	public Field getField(int m, int n) {
		// Safety checks
		if (mFields!=null && m<mFields.length && n < mFields[0].length) {
			return mFields[m][n];
		}
		// TODO log error
		// Return null on error
		return null;
	}
	
	
}
