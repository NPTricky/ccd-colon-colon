package j3chess.utility;

public class Vector2d {

	public Vector2d() {
	}
	
	public Vector2d(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public static Vector2d fromPolarCoordinates(float r, float rho) {
		return new Vector2d((float)(r*Math.cos(rho)), (float)(r*Math.sin(rho)));
	}
	
	public float getAngle(Vector2d other) {
		// \todo implement
		return 0;
	}
	
	public String toString() {
		return x+"/"+y;
	}
	
	public float getDotProduct(Vector2d other) {
		return this.x * other.x + this.y * other.y;
	}
	
	public float x;
	public float y;
}
