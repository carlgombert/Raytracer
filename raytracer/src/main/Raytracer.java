package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Raytracer generates the raytraced image for the engine
 */
public class Raytracer {
	
	// raytraced image
	private BufferedImage image; 
	
	//image in the form of a 2D pixel array
	private Color[][] imageData;
	
	private int width, height;
	
	private Vec3 sphere = new Vec3(0f,0f,-1f);
	private float sphereRadius = 0.5f;
	
	
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
	public float hitSphere(Vec3 center, float radius, Ray r) {
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
	}
	
	/**
	 * generates the color for a given ray
	 *
	 * @param r ray for determining color
	 * @return         color for the given ray
	 */
	public Vec3 color(Ray r) {
		float t = hitSphere(sphere, sphereRadius, r);
		if(t > 0) {
			Vec3 n = (r.pointAt(t).subtract(new Vec3(0f, 0f, -1))).unitVector();
			Vec3 col = new Vec3(n.x()+1, n.y()+1, n.z()+1);
			return col.multiply(0.5f);
		}
		
		Vec3 unitDir = r.getDirection().unitVector();
		t = 0.5f * (unitDir.y() + 1.0f);
		return (new Vec3(1.0f, 1.0f, 1.0f)).multiply((1.0f - t)).add((new Vec3(0.5f, 0.7f, 1.0f)).multiply(t));
	}
	
	/**
	 * generates the image in the form of a 2d pixel array
	 */
	public void generateImageData() {
		
		Vec3 lowerLeftCorner = new Vec3(-2.0f, -1.0f, -1.0f);
		Vec3 horizontal = new Vec3(4.0f, 0.0f, 0.0f);
		Vec3 vertical = new Vec3(0.0f, 2.0f, 0.0f);
		Vec3 origin = new Vec3(0.0f, 0.0f, 0.0f);
		
		for(int y = height-1; y >= 0; y--){
	        for(int x = 0; x < width; x++){
	        	float u = (float)x / (float)width;
	        	float v = (float)y / (float)height;
	        	
	        	Ray r = new Ray(origin, lowerLeftCorner.add(horizontal.multiply(u)).add(vertical.multiply(v)));
	        	
	        	Vec3 col = color(r);
	        	
	            int ir = (int)(255.99 * col.r());
	            int ig = (int)(255.99 * col.g());
	            int ib = (int)(255.99 * col.b());
	            
	            
	            imageData[y][x] = new Color(ir, ig, ib);
	         
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
		
		/*File outputfile = new File("testImage.jpg");
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
