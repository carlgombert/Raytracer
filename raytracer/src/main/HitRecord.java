package main;

public class HitRecord {
	public float t;
    public Vec3 p;
    public Vec3 normal;
    public Material mat;
    public boolean frontFace;
    
    public HitRecord() {}
    
    public void setFaceNormal(Ray r, Vec3 outward_normal) {
        // Sets the hit record normal vector.
        // NOTE: the parameter `outward_normal` is assumed to have unit length.

        frontFace = r.getDirection().dot(outward_normal) < 0;
        if(frontFace) {
        	normal = outward_normal;
        }
        else {
        	normal = outward_normal.multiply(-1f);
        }
    }
}
