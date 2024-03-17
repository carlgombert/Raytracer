package main.materials;

import main.HitRecord;
import main.Material;
import main.Ray;
import main.Raytracer;
import main.Sphere;
import main.Vec3;

public class Metal extends Material{
	
	private Vec3 albedo;
	
	public Metal(Vec3 albedo) {
		this.albedo = albedo;
	}
	public boolean scatter(Ray r_in, HitRecord rec, Vec3 attenuation, Ray scattered) {
		Vec3 reflected = r_in.getDirection().unitVector().reflect(rec.normal);
		Raytracer.scattered = new Ray(rec.p, reflected);
		Raytracer.attenuation = albedo;
		return Raytracer.scattered.getDirection().dot(rec.normal) > 0;
	}
}
