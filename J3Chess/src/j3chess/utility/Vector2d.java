package j3chess.utility;

/**
 * Class to present a 2d vector.
 */
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
     * @param x Cartesian X coordinate
     * @param y	 Cartesian Y coordinate
     */
    public Vector2d(final float x, final float y) {
        this.x = x;
        this.y = y;
    }


    /**
     * @brief Creates a cartesian vector from polar coordinates.
     * @param radius	Radius of the vector
     * @param rho	Angle in radians
     * @return return a cartesian 2D vector
     */
    public static Vector2d fromPolarCoordinates(final float radius, final float rho) {
        return new Vector2d((float) (radius * Math.cos(rho)), (float) (radius * Math.sin(rho)));
    }

    /** @brief Cartesian X coordinate */
    public float x;
    /** @brief Cartesian Y coordinate */
    public float  y;
}
