package main;

public class Vec3 {
	
	private float[] e = new float[3];
	
	public Vec3() {}
	
	public Vec3(float e0, float e1, float e2) {
		e[0] = e0;
		e[1] = e1;
		e[2] = e2;
	}
	
	public float x() {return e[0];}
	
	public float y() {return e[1];}
	
	public float z() {return e[2];}
	
	public float r() {return e[0];}
	
	public float g() {return e[1];}
	
	public float b() {return e[2];}
	
	public float length() { 
		return (float) Math.sqrt(e[0] * e[0] + e[1] * e[1] + e[2] * e[2]); 	
	}
	
	
	public float get(int i) {
        return e[i];
    }
	
	public void set(int i, float val) { 
		e[i] = val; 
	}
	
	public Vec3 add(Vec3 v2) {
		return new Vec3(e[0] + v2.get(0),
        e[1] + v2.get(1),
        e[2] + v2.get(2));
    }
	
	public Vec3 subtract(Vec3 v2) {
		return new Vec3(e[0] - v2.get(0),
        e[1] - v2.get(1),
        e[2] - v2.get(2));
    }
	
	public Vec3 multiply(Vec3 v2) {
		return new Vec3(e[0] * v2.get(0),
        e[1] * v2.get(1),
        e[2] * v2.get(2));
    }
	
	public Vec3 divide(Vec3 v2) {
		return new Vec3(e[0] / v2.get(0),
        e[1] / v2.get(1),
        e[2] / v2.get(2));
    }
	
	public Vec3 multiply(float t) {
		return new Vec3(e[0] * t,
        e[1] * t,
        e[2] * t);
    }
	
	public Vec3 divide(float t) {
		return new Vec3(e[0] / t,
        e[1] / t,
        e[2] / t);
    }
	
	public Vec3 addEquals(Vec3 v2) {
        e[0] += v2.get(0);
        e[1] += v2.get(1);
        e[2] += v2.get(2);
        return this;
    }
	
	public Vec3 subtractEquals(Vec3 v2) {
        e[0] -= v2.get(0);
        e[1] -= v2.get(1);
        e[2] -= v2.get(2);
        return this;
    }
	
	public Vec3 multiplyEquals(Vec3 v2) {
        e[0] *= v2.get(0);
        e[1] *= v2.get(1);
        e[2] *= v2.get(2);
        return this;
    }
	
	public Vec3 divideEquals(Vec3 v2) {
        e[0] /= v2.get(0);
        e[1] /= v2.get(1);
        e[2] /= v2.get(2);
        return this;
    }
	
	public Vec3 multiplyEquals(float t) {
        e[0] *= t;
        e[1] *= t;
        e[2] *= t;
        return this;
	}
	
	public float dot(Vec3 v2) {
		return e[0]*v2.get(0) + e[1]*v2.get(1) + e[2]*v2.get(2);
	}
	
	public Vec3 cross(Vec3 v2) {
		return new Vec3(e[1]*v2.get(2) - e[2]*v2.get(1), 
		-(e[0]*v2.get(2) - e[2]*v2.get(0)), 
		e[0]*v2.get(1) - e[1]*v2.get(0));
	}
	
	public Vec3 unitVector() {
		return this.divide(this.length());
	}
	
	public Vec3 reflect(Vec3 n) {
		return this.subtract(n.multiply(this.dot(n)*2));
	}
	
}
