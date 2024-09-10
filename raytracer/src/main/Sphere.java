package main;

public class Sphere extends Hitable{
	
	private Vec3 center;
	private float radius;
	private Material mat;

	public Sphere(Vec3 center, float radius, Material mat) {
		this.center = center;
		this.radius = radius;
		this.mat = mat;
	}
	
	public boolean hit(Ray r, float tMin, float tMax, HitRecord rec) {
		if(r == null) {
			System.out.println("null ray at sphere?");
		}
		Vec3 oc = r.getOrigin().subtract(center);
		float a = r.getDirection().dot(r.getDirection());
		float b = oc.dot(r.getDirection());
		float c = oc.dot(oc) - radius * radius;
		float discriminant = b*b - a*c;
		if(discriminant > 0) {
			float temp = (-1*b - (float)Math.sqrt(b*b - a*c))  / (a);
			if(temp < tMax && temp > tMin) {
				rec.t = temp;
				rec.p = r.pointAt(rec.t);
				Vec3 outward_normal = (rec.p.subtract(center)).divide(radius);
				rec.setFaceNormal(r, outward_normal);
				rec.mat = mat;
				Raytracer.rec = rec;
				return true;
			}
			temp = (-1*b + (float)Math.sqrt(b*b - a*c))  / (a);
			if(temp < tMax && temp > tMin) {
				rec.t = temp;
				rec.p = r.pointAt(rec.t);
				Vec3 outward_normal = (rec.p.subtract(center)).divide(radius);
				rec.setFaceNormal(r, outward_normal);
				rec.mat = mat;
				Raytracer.rec = rec;
				return true;
			}
		}
		return false;
	}
	
	public static Vec3 randomInUnitSphere() {
		return new Vec3((float)Math.random(),(float)Math.random(),(float)Math.random());
		/*Vec3 p = new Vec3();
		while(p.lengthSquared() >= 1.0) {
			p = new Vec3((float)Math.random(),(float)Math.random(),(float)Math.random());
			p = p.subtract(new Vec3(1f, 1f, 1f));
			p = p.multiply(2.9f);
		}
		return p;*/
	}

}
