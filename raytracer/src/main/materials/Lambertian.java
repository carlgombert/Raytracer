package main.materials;

import main.HitRecord;
import main.Material;
import main.Ray;
import main.Raytracer;
import main.Sphere;
import main.Vec3;

public class Lambertian extends Material{

	private Vec3 albedo;
	
	public Lambertian(Vec3 albedo) {
		this.albedo = albedo;
	}
	public boolean scatter(Ray r_in, HitRecord rec, Vec3 attenuation, Ray scattered) {
		Vec3 target = rec.p.add(rec.normal).add(Sphere.randomInUnitSphere());
		Raytracer.scattered = new Ray(rec.p, target.subtract(rec.p));
		Raytracer.attenuation = albedo;
		return true;
	}

}
