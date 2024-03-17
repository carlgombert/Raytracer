package main;

public abstract class Material {
	public abstract boolean scatter(Ray r_in, HitRecord rec, Vec3 attenuation, Ray scattered);
}
