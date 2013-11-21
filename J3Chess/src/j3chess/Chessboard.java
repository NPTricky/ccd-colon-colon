package j3chess;

import j3chess.utility.Vector2d;

public class Chessboard {

	/**
	 * \brief Creates new instance of the Chessboard class.
	 */
	public Chessboard() {
		createFields();
		calculateDrawPositions();
	}
	
	/**
	 * \brief Creates all the fields and adds the required connections.
	 */
	private void createFields() {
		// Create field array
		mFields = new Field[6][24];
		
		// \todo remove and replace with Kai's implementation
		for (int m=0; m<6; m++) {
			for (int n=0; n<24; n++) {
				mFields[m][n] = new Field();
			}
		}
		
		// \todo #10 Add connections to fields
	}
	
	/**
	 * \brief Calculates draw positions for all fields from spheric to cartesian coordinates.
	 * 
	 * Sets the X and Y draw coordinates of the field. Coordinates are calculated in the
	 * range [-1..1], where [0,0] is the center of the board. The coordinates need to be
	 * scaled to the total board size before drawing.
	 */
	private void calculateDrawPositions() {
		// \todo parameterize! center field size
		for (int m=0; m<6; m++) {
			for (int n=0; n<24; n++) {
				// Calculate polar coordinates
				float r = 1.f - (2.f * m + 1.f) / (6.f * 2.f);
				float rho = (float)(2.f * Math.PI * (n/24.f));
				
				// Convert to cartesian
				Vector2d cartesian = Vector2d.fromPolarCoordinates(r, rho);
				
				// Set coordinates of field
				mFields[m][n].setDrawPosition(cartesian);
				System.out.println("["+m+"]["+n+"]:\t"+r+"\t"+rho+"\t"+cartesian.toString());
			}
		}
	}
	
	private Field mFields[][];
	
}
