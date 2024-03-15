package main;

class HitRecord {
    float t;
    Vec3 p;
    Vec3 normal;
}

public abstract class Hitable {
    abstract boolean hit(Ray r, float tMin, float tMax, HitRecord rec);
}