package main.materials;

import main.HitRecord;
import main.Material;
import main.Ray;
import main.Raytracer;
import main.Vec3;

public class Dielectric extends Material{

	private double refractionIndex;
	
	public Dielectric(double refractionIndex) {
		this.refractionIndex = refractionIndex;
	}
	
	public boolean scatter(Ray r_in, HitRecord rec, Vec3 attenuation, Ray scattered) {
		Raytracer.attenuation = new Vec3(1.0, 1.0, 1.0);
		
		float ri = (float)refractionIndex;
		if(rec.frontFace) {
			ri = 1f/(float)refractionIndex;
		}
		
		Vec3 unitDirection = r_in.getDirection().unitVector();
		
		Vec3 refracted = unitDirection.refract(rec.normal, ri);
		
		Raytracer.scattered = new Ray(rec.p, refracted);
		
		return true;
	}

}
