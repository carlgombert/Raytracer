package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Raytracer {
	private BufferedImage image;
	
	private Color[][] imageData;
	
	private int width, height;
	
	private Vec3 sphere = new Vec3(0f,0f,-1f);
	private float sphereRadius = 0.5f;
	
	
	public Raytracer(int width, int height) {
		this.width = width;
		this.height = height;
		
		imageData = new Color[height][width];
	}
	
	public boolean hitSphere(Vec3 center, float radius, Ray r) {
		Vec3 oc = r.getOrigin().subtract(center);
		float a = r.getDirection().dot(r.getDirection());
		float b = 2.0f * oc.dot(r.getDirection());
		float c = oc.dot(oc) - radius * radius;
		float discriminant = b*b - 4*a*c;
		return discriminant > 0;
	}
	
	public Vec3 color(Ray r) {
		
		if(hitSphere(sphere, sphereRadius, r)) {
			return new Vec3(1f, 0f , 0f);
		}
		
		
		Vec3 unitDir = r.getDirection().unitVector();
		float t = 0.5f * (unitDir.y() + 1.0f);
		return (new Vec3(1.0f, 1.0f, 1.0f)).multiply((1.0f - t)).add((new Vec3(0.5f, 0.7f, 1.0f)).multiply(t));
	}
	
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
	
	public void Draw() {
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
	
	public void render(Graphics g) {
		g.drawImage(image, 0, 0, null);
	}
}
