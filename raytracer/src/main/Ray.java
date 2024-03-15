package main;

/**
 * Ray is a vector representing a ray of light
 */
public class Ray {

	private Vec3 origin; 
	private Vec3 direction;
	
	public Ray() {}
	
	public Ray(Vec3 origin, Vec3 direction) {
		this.origin = origin;
		this.direction = direction;
	}
	
	/**
	 * function for finding the position of a point alone the ray
	 *
	 * @param  t length along the ray from origin
	 * @return         3D position of point alone the ray
	 */
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
