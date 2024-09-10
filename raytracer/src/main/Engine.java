package main;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;


public class Engine extends Canvas implements Runnable{
	
	private Thread thread;
	private boolean running = false;
	
	public static final int WIDTH = 1000, HEIGHT = 500;
	
	private static final long serialVersionUID = -995199956205934258L;
	
	private static int direction = 0;
	
	private static Raytracer image;

	public static void main(String[] args) {
		new Engine();
	}
	
	public Engine() {
		
		image = new Raytracer(WIDTH, HEIGHT);
		
		image.generateImageData(false);
		
		image.draw();
		
		this.addKeyListener(new KeyInput());
		this.addMouseListener(new KeyInput());
		this.addMouseMotionListener(new KeyInput());
		
		
		new Window(WIDTH, HEIGHT, "raytracer", this, true);
		
		render();
		
		start();
	}
	
	public void paint(Graphics g) {
		 image.render(g);
    }
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		paint(g);
		
		g.dispose();
		bs.show();
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * The main game loop responsible for updating and rendering the game.
     */
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running)
				
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frames = 0;
			}
			
		}
		stop();
	}

	private void tick() {
		if(direction == 1) {
			Camera.moveForeward();
			image.generateImageData(false);
			image.draw();
			render();
		}
		if(direction == 2) {
			Camera.moveBackward();
			image.generateImageData(false);
			image.draw();
			render();
		}
		if(direction == 3) {
			Camera.moveLeft();
			image.generateImageData(false);
			image.draw();
			render();
		}
		if(direction == 4) {
			Camera.moveRight();
			image.generateImageData(false);
			image.draw();
			render();
		}
		if(direction == 5) {
			Camera.moveUp();
			image.generateImageData(false);
			image.draw();
			render();
		}
		if(direction == 6) {
			Camera.moveDown();
			image.generateImageData(false);
			image.draw();
			render();
		}
		if(direction == 7) {
			Camera.rotateLeft();
			image.generateImageData(false);
			image.draw();
			render();
		}
		if(direction == 8) {
			Camera.rotateRight();
			image.generateImageData(false);
			image.draw();
			render();
		}
		if(direction == 9) {
			Camera.rotateUp();
			image.generateImageData(false);
			image.draw();
			render();
		}
		if(direction == 10) {
			Camera.rotateDown();
			image.generateImageData(false);
			image.draw();
			render();
		}
	}
	
	public static void setDirection(int direction) {
		Engine.direction = direction;
	}
	
	public static int getDirection() {
		return direction;
	}
	
	public static void capture() {
		Popup popup = new Popup(image, WIDTH, HEIGHT);
		popup.run();
	}
}
