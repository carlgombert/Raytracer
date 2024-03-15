package main;

public class Sphere extends Hitable{
	
	private Vec3 center;
	private float radius;

	public Sphere(Vec3 center, float radius) {
		this.center = center;
		this.radius = radius;
	}
	
	boolean hit(Ray r, float tMin, float tMax, HitRecord rec) {
		Vec3 oc = r.getOrigin().subtract(center);
		float a = r.getDirection().dot(r.getDirection());
		float b = 2.0f * oc.dot(r.getDirection());
		float c = oc.dot(oc) - radius * radius;
		float discriminant = b*b - 4*a*c;
		if(discriminant > 0) {
			float temp = (-b - (float)Math.sqrt(b*b - a*c))  / (a);
			if(temp < tMax && temp > tMin) {
				rec.t = temp;
				rec.p = r.pointAt(rec.t);
				rec.normal = (rec.p.subtract(center)).divide(radius);
				return true;
			}
			temp = (-b + (float)Math.sqrt(b*b - a*c))  / (a);
			if(temp < tMax && temp > tMin) {
				rec.t = temp;
				rec.p = r.pointAt(rec.t);
				rec.normal = (rec.p.subtract(center)).divide(radius);
				return true;
			}
		}
		return false;
	}

}
