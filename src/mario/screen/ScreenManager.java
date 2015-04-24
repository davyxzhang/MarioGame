package mario.screen;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import javax.swing.JFrame;

import mario.sprites.Mario;

/**
 * This fixes the problem of the images flickering on the screen. We got this from youtube tutorials.
 * @author Davy and Chris
 */
public class ScreenManager extends KeyAdapter implements KeyListener{

	private GraphicsDevice gd;
	Mario m = new Mario();
	
	//TODO: make these private and create setters and getters; don't want expose them to all classes
	public boolean keyLeft = false;
	public boolean keyRight = false;
	public boolean keyUp = false;
	public boolean keyDown = false;
	public static int width;
	public static int height;
	
	public void keyPressed(KeyEvent e){
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_ESCAPE){
			System.exit(0);
		}

		if (keyCode == KeyEvent.VK_UP) {
			if(m.getY()>=0 && m.getY()<=600){
				keyUp = true;
			}

		}
		else if (keyCode == KeyEvent.VK_DOWN) {
			if(m.getY()>=0 && m.getY()<=600){
				keyDown = true;
			}
		} 
		else if (keyCode == KeyEvent.VK_LEFT) {
			if(m.getX()>=0 && m.getX()<=800){
				keyLeft = true;
			}
		} 
		else if(keyCode == KeyEvent.VK_RIGHT) {
			if(m.getX()>=0 && m.getX() <= 800){
				keyRight = true;
			}
		} 
		else if(keyCode == KeyEvent.VK_RIGHT && keyCode == KeyEvent.VK_DOWN){
			m.updateXRight();
			m.updateYDown();
			keyRight = true;
			keyDown = true;
		}
		
	}
	
	public ScreenManager(){
		GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
		gd = e.getDefaultScreenDevice();
	}
	
	/**
	 * This will get the compatible display mode
	 * @return
	 */
	public DisplayMode [] getCompatibleDsiplayModes(){
		return gd.getDisplayModes();
	}
	
	/**
	 * This will compare the display modes in to the video card and see if they match
	 */
	public DisplayMode findFirstCompatibleMode(DisplayMode modes[]){
		DisplayMode goodModes[] = gd.getDisplayModes();
		for(int x=0; x<modes.length; x++){
			for(int y=0; y<goodModes.length; y++){
				if(displayModesMatch(modes[x], goodModes[y])){
					return modes[x];
				}
			}
		}
		return null;
	}
	/**
	 * This will get the current display mode you're using
	 */
	public DisplayMode getCurrentDisplayMode(){
		return gd.getDisplayMode();
	}
	
	/**
	 * This checks if two display modes match.
	 */
	public boolean displayModesMatch(DisplayMode m1, DisplayMode m2){
		if(m1.getWidth() != m2.getWidth() || m1.getHeight() != m2.getHeight()){
			return false;
		}
		if(m1.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI && m2.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI && m1.getBitDepth() != m2.getBitDepth()){
			return false;
			
		}
		if(m1.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN && m2.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN && m1.getRefreshRate() != m2.getRefreshRate()){
			return false;
		}
		return true;
	}
	
	/**
	 * This will make the frame full screen.
	 */
	public void setFullScreen(DisplayMode dm){
		JFrame f = new JFrame();
		f.setUndecorated(false);
		f.setIgnoreRepaint(true);
		f.setResizable(true);
		gd.setFullScreenWindow(f);
		f.addKeyListener(this);
	
		if(dm != null && gd.isDisplayChangeSupported()){
			try{
				//lets not change the users resolution...its a hassle
				//gd.setDisplayMode(dm);
			}catch(Exception ex){}
		}
		//two different buffers we'll be using
		f.createBufferStrategy (2);
	}
	
	/**
	 * This will set the graphics object.
	 */
	public Graphics2D getGraphics(){
		Window w = gd.getFullScreenWindow();
	
		if(w != null){
			BufferStrategy s = w.getBufferStrategy();
			return (Graphics2D)s.getDrawGraphics();
		}else{
			return null;
		}
	}
	
	/**
	 * This will update the display.
	 */
	public void update(){
		Window w = gd.getFullScreenWindow();
		if(w != null){
			BufferStrategy bs = w.getBufferStrategy();
			if(!bs.contentsLost()){
				bs.show();
			}
		}
	}
	
	/**
	 * This will return the full screen window.
	 */
	public Window getFullScreenWindow(){
		return gd.getFullScreenWindow();
	}

	public int getWidth(){
		Window w = gd.getFullScreenWindow();
		if(w != null){
			return w.getWidth();
		}else{
			return 0;
		}
	}
	
	/**
	 * This will get the width of the window.
	 */
	public int getHeight(){
		Window w = gd.getFullScreenWindow();
		if(w != null){
			return w.getHeight();
		}else{
			return 0;
		}
	}
	
	/**
	 * This will restore the screen to normal.
	 */
	public void restoreScreen(){
		Window w = gd.getFullScreenWindow();
		if(w != null){
			w.dispose();
		}
		gd.setFullScreenWindow(null);
	}
	
	/**
	 * This will create the image that is compatible with the monitor.
	 */
	public BufferedImage createCompatibleImage(int w, int h, int t){
		Window window = gd.getFullScreenWindow();
		if(window != null){
			GraphicsConfiguration gc = window.getGraphicsConfiguration();
			return gc.createCompatibleImage(w, h, t);
		}
		return null;
	}
}