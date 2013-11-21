package j3chess;

import j3chess.utility.Vector2d;

/**
 * @brief Represents the game's board. Keeps track of all the fields an pieces
 * in the game.
 */
public class Chessboard {

	/**
	 * @brief Creates new instance of the Chessboard class.
	 */
	public Chessboard() {
		// Create all logical fields
		createFields();
		
		// Calculate the fields' 2D drawing positions
		calculateDrawPositions(0.9f);
	}
	
	/**
	 * @brief Creates all the fields and adds the required connections.
	 */
	private void createFields() {
		// Create field array
		mFields = new Field[6][24];
		
		// TODO remove and replace with Kai's implementation
		for (int m=0; m<6; m++) {
			for (int n=0; n<24; n++) {
				mFields[m][n] = new Field();
			}
		}
		
		// TODO #10 Add connections to fields
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
				mFields[m][n].setDrawPosition(cartesian);
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
	
	/** @brief 6x24 array of all fields in the game */
	private Field mFields[][];
	
}
