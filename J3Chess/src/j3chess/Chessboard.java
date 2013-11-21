package j3chess;

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
		
		// \todo #10 Add connections to fields
	}
	
	/**
	 * \brief Calculates draw positions for all fields from spheric to cartesian coordinates.
	 */
	private void calculateDrawPositions() {
		// \todo #11 Calculate positions
	}
	
	private Field mFields[][];
	
}
