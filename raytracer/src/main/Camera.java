package main;

public class Camera {
	
	private Vec3 lowerLeftCorner = new Vec3(-2.0f, -1.0f, -1.0f);
	private Vec3 horizontal = new Vec3(4.0f, 0.0f, 0.0f);
	private Vec3 vertical = new Vec3(0.0f, 2.0f, 0.0f);
	private Vec3 origin = new Vec3(0.0f, 0.0f, 0.9f);
	
	public Camera() {
		
	}
	
	public Ray getRay(float u, float v) {
		return new Ray(origin, lowerLeftCorner.add(horizontal.multiply(u)).add(vertical.multiply(v)));
	}
}
