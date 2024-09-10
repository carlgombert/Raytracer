package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

/**
 * The KeyInput class handles the mouse and key input from the user.
 */
public class KeyInput extends KeyAdapter implements MouseListener, MouseMotionListener, ActionListener
{	

	private int mouseX;
	private int mouseY;
	
	public KeyInput() {}
	
	public void mouseClicked(MouseEvent e) {
		
	}
	
	
	public void mouseReleased(MouseEvent e) 
	{
	
	}
	
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_W) Engine.setDirection(1);
		if(key == KeyEvent.VK_S) Engine.setDirection(2);
		if(key == KeyEvent.VK_A) Engine.setDirection(3);
		if(key == KeyEvent.VK_D) Engine.setDirection(4);
		if(key == KeyEvent.VK_SPACE) Engine.setDirection(5);
		if(key == KeyEvent.VK_SHIFT) Engine.setDirection(6);
		if(key == KeyEvent.VK_LEFT) Engine.setDirection(7);
		if(key == KeyEvent.VK_RIGHT) Engine.setDirection(8);
		if(key == KeyEvent.VK_UP) Engine.setDirection(9);
		if(key == KeyEvent.VK_DOWN) Engine.setDirection(10);
		if(key == KeyEvent.VK_C) Engine.capture();
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if((key == KeyEvent.VK_UP || key == KeyEvent.VK_W)) Engine.setDirection(0);
		if((key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S)) Engine.setDirection(0);
		if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) Engine.setDirection(0);
		if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) Engine.setDirection(0);
		if(key == KeyEvent.VK_SPACE) Engine.setDirection(0);
		if(key == KeyEvent.VK_SHIFT) Engine.setDirection(0);
	}

	public void mouseMoved(MouseEvent e)
	{
		
	}
	
	public void mouseDragged(MouseEvent e) 
	{

	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
