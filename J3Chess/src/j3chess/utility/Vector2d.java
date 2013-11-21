package j3chess.utility;

public class Vector2d {

	/**
	 * @brief Creates a zero vector per default.
	 */
	public Vector2d() {
		this.x = 0;
		this.y = 0;
	}
	
	/**
	 * @brief Creates a vector from the given cartesian coordinates.
	 * @param x	Cartesian X coordinate
	 * @param y	Cartesian Y coordinate
	 */
	public Vector2d(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @brief Creates a cartesian vector from polar coordinates.
	 * @param r	Radius of the vector
	 * @param rho	Angle in radians
	 * @return
	 */
	public static Vector2d fromPolarCoordinates(float r, float rho) {
		return new Vector2d((float)(r*Math.cos(rho)), (float)(r*Math.sin(rho)));
	}
	
	/** @brief Cartesian X coordinate */
	public float x;
	/** @brief Cartesian Y coordinate */
	public float y;
}
