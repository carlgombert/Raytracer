package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import main.materials.Lambertian;
import main.materials.Metal;

/**
 * Raytracer generates the raytraced image for the engine
 */
public class Raytracer {
	
	// raytraced image
	private BufferedImage image; 
	
	//image in the form of a 2D pixel array
	private Color[][] imageData;
	
	private int width, height;
	
	public static HitRecord rec;
	
	public static Ray scattered;
	public static Vec3 attenuation;
	
	public Raytracer(int width, int height) {
		this.width = width;
		this.height = height;
		
		imageData = new Color[height][width];
	}
	
	/**
	 * determines if given ray intersects a sphere
	 *
	 * @param center 3D position of sphere center
	 * @param radius radius of the sphere
	 * @param r ray checking for intersection
	 * @return         boolean if the sphere is hit or not
	 */
	/*public float hitSphere(Vec3 center, float radius, Ray r) {
		Vec3 oc = r.getOrigin().subtract(center);
		float a = r.getDirection().dot(r.getDirection());
		float b = 2.0f * oc.dot(r.getDirection());
		float c = oc.dot(oc) - radius * radius;
		float discriminant = b*b - 4*a*c;
		if(discriminant < 0) {
			return -1f;
		}
		else {
			return (-b - (float)Math.sqrt(discriminant))  / (2f * a);			
		}
	}/*
	
	/**
	 * generates the color for a given ray
	 *
	 * @param r ray for determining color
	 * @return         color for the given ray
	 */
	public Vec3 color(Ray r, Hitable world, int depth) {
		rec = new HitRecord();
		
		if(world.hit(r, 0.001f, Float.MAX_VALUE, rec)) {
			attenuation = new Vec3();
			scattered = new Ray();
			if(depth < 50 && rec.mat.scatter(r, rec, attenuation, scattered)) {
				return attenuation.multiply(color(scattered, world, depth+1));
			}
			else {
				return new Vec3(0, 0, 0);
			}
		}
		else {
			Vec3 unitDir = r.getDirection().unitVector();
			float t = 0.5f * (unitDir.y() + 1.0f);
			return (new Vec3(1.0f, 1.0f, 1.0f)).multiply((1.0f - t)).add((new Vec3(0.5f, 0.7f, 1.0f)).multiply(t));
		}
	}
	
	/**
	 * generates the image in the form of a 2d pixel array
	 */
	public void generateImageData() {
		
		int ns = 100;
		
		List<Hitable> list = new ArrayList<Hitable>();
		list.add(new Sphere(new Vec3(0f, -0.1f, -1f), 0.4f, new Metal(new Vec3(0.8, 0.8, 0.8))));
		list.add(new Sphere(new Vec3(0f, -400.5f, -1f), 400f, new Metal(new Vec3(0.6f, 0.7f, 0.9f))));
		list.add(new Sphere(new Vec3(2f, 0f, -2f), 0.5f, new Metal(new Vec3(0.4f, 0.4f, 0.4f))));
		list.add(new Sphere(new Vec3(1f, -0.3f, -0.7f), 0.2f, new Metal(new Vec3(0.450980392157f, 0.619607843137f, 0.560784313725f))));
		list.add(new Sphere(new Vec3(-1f, 0f, -1f), 0.5f, new Metal(new Vec3(0.8f, 0.8f, 0.8f))));
		list.add(new Sphere(new Vec3(-1.2f, -0.35f, -0.4f), 0.15f, new Lambertian(new Vec3(0.6f, 0.2f, 0.2f))));
		list.add(new Sphere(new Vec3(-0.5f, -0.4f, 0f), 0.1f, new Lambertian(new Vec3(0.6f, 0.6f, 0.7f))));
		list.add(new Sphere(new Vec3(0.4f, -0.4f, -0.2f), 0.1f, new Lambertian(new Vec3(0.8f, 0.6f, 0.2f))));
		list.add(new Sphere(new Vec3(0.2f, -0.4f, -0.3f), 0.1f, new Lambertian(new Vec3(0.8f, 0.8f, 0.2f))));
		list.add(new Sphere(new Vec3(-4f, 0.5f, -4f), 1f, new Lambertian(new Vec3(0.8f, 0.6f, 0.2f))));
		list.add(new Sphere(new Vec3(-0.1f, -0.4f, 0.0f), 0.1f, new Lambertian(new Vec3(0.1f, 0.1f, 0.3f))));
		
		Hitable world = new HitableList(list);
		
		Camera cam = new Camera();
		
		for(int y = height-1; y >= 0; y--){
	        for(int x = 0; x < width; x++){
	        	Vec3 col = new Vec3(0f, 0f, 0f);
	        	
	        	// averaging colors in a section for a smoother blend between colors instead of a hard
	        	// pixel cutoff
	        	for(int s = 0; s < ns; s++) {
	        		float u = (float)(x+Math.random()) / (float)width;
		        	float v = (float)(y+Math.random()) / (float)height;
		        	
		        	Ray r = cam.getRay(u, v);
		        	
		        	Vec3 p = r.pointAt(2f);
		        	
		        	col.addEquals(color(r, world, 0));
	        		
	        	}
	        	
	        	col.divideEquals((float)ns);
	        	
	        	col = new Vec3(Math.sqrt(col.r()), Math.sqrt(col.g()), Math.sqrt(col.b()));
	        	
	            int ir = (int)(255.99 * col.r());
	            int ig = (int)(255.99 * col.g());
	            int ib = (int)(255.99 * col.b());
	            
	            
	            imageData[height-y-1][x] = new Color(ir, ig, ib);
	         
	        }
	    }
	}
	
	/**
	 * draws the image from the form of a 2d pixel array to a buffered image
	 */
	public void draw() {
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		for(int y = height-1; y >= 0; y--){
	        for(int x = 0; x < width; x++){
	        	int rgb = imageData[y][x].getRGB();
	        	
	        	image.setRGB(x, y, rgb);
        	}
        }
		
		/*File outputfile = new File("progress4.jpg");
		try {
			ImageIO.write(image, "jpg", outputfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
	
	/**
	 * renders the image to screen
	 */
	public void render(Graphics g) {
		g.drawImage(image, 0, 0, null);
	}
}
