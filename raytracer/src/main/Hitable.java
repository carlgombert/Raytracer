package main;

public abstract class Hitable {
    public abstract boolean hit(Ray r, float tMin, float tMax, HitRecord rec);
}