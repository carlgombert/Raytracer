package main;

public class Camera {
	
	private static Vec3 lowerLeftCorner = new Vec3(-2.0f, -1.0f, -1.0f);
	private static Vec3 horizontal = new Vec3(4.0f, 0.0f, 0.0f);
	private static Vec3 vertical = new Vec3(0.0f, 2.0f, 0.0f);
	private static Vec3 origin = new Vec3(0.0f, 0.0f, 1.0f);
	
	public Camera() {
		
	}
	
	public static Vec3 getOrigin() {
		return origin;
	}
	
	public Ray getRay(float u, float v) {
		return new Ray(origin, lowerLeftCorner.add(horizontal.multiply(u)).add(vertical.multiply(v)));
	}
	
	public static void moveForeward() {
		origin.set(2, origin.z() - 0.1f);
	}
	
	public static void moveBackward() {
		origin.set(2, origin.z() + 0.1f);
	}
	
	public static void moveLeft() {
		origin.set(0, origin.x() - 0.1f);
	}
	
	public static void moveRight() {
		origin.set(0, origin.x() + 0.1f);
	}
	
	public static void moveUp() {
		origin.set(1, origin.y() + 0.1f);
	}
	
	public static void moveDown() {
		origin.set(1, origin.y() - 0.1f);
	}
	
	public static void rotateLeft() {
		lowerLeftCorner.set(0, lowerLeftCorner.x() - 0.1f);
	}
	
	public static void rotateRight() {
		lowerLeftCorner.set(0, lowerLeftCorner.x() + 0.1f);
	}
	
	public static void rotateUp() {
		lowerLeftCorner.set(1, lowerLeftCorner.y() + 0.1f);
	}
	
	public static void rotateDown() {
		lowerLeftCorner.set(1, lowerLeftCorner.y() - 0.1f);
	}

}
