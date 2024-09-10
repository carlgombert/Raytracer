package main;

import java.awt.Canvas;
import java.awt.Graphics;

public class Popup extends Canvas implements Runnable{

	private Raytracer image;
	private int width, height;
	
	public Popup(Raytracer image, int width, int height) {
		
		this.image = image;
		
		this.width = width;
		
		this.height = height;
		
	}
	
	public void paint(Graphics g) {
		 image.render(g);
   }

	
	public void run() {
		image.generateImageData(true);
		
		image.drawHighDef();
		
		new Window(width, height, "capture", this, false);
		
	}
}
