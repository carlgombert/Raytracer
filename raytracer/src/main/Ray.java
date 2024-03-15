package main;

public class Ray {

	private Vec3 origin;
	private Vec3 direction;
	
	public Ray() {}
	
	public Ray(Vec3 origin, Vec3 direction) {
		this.origin = origin;
		this.direction = direction;
	}
	
	public Vec3 pointAt(float t) {
		return origin.add(direction.multiply(t));
	}
	
	public Vec3 getOrigin() {
		return origin;
	}
	
	public Vec3 getDirection() {
		return direction;
	}
}
