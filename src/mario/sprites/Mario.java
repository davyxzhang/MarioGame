package mario.sprites;
import java.awt.Image;
import mario.screen.ScreenManager;

import javax.swing.ImageIcon;
/**
 * This class updates Mario's positions
 * @author Davy and Chris
 *
 */
public class Mario {
	
	private float x;
	private float y;
	boolean collide = false;
	
	Image mario1 = new ImageIcon("img\\mariofly.gif").getImage();
	
	public void setX(float newX) {
		x = newX;
	}
	
	public void setY(float newY) {
		y = newY;
	}
	
	public void updateXLeft(){
		if(x<43){
			x = 10;
		}else{
			x += -20F;	
		}
	}
	
	public void updateXRight(){
		if(x>ScreenManager.width-50){
			x = ScreenManager.width-30;
		}else{
			x += 20F;	
		}
	}
	
	public void updateYUp(){
		if(y<60){
			y = 35;
		}
		else{
			y += -20F;	
		}
	}
	
	public void updateYDown(){
		if(y>ScreenManager.height-60){
			y = ScreenManager.height-40;
		}
		else{
			y += 20F;	
		}
	}	
	/**
	 * This will get the x position
	 */
	public float getX(){
		return x;
	}
	/**
	 * This will get the y position
	 */
	public float getY(){
		return y;
	}
	
	/**
	 * This will get the sprite's width.
	 */
	public int getWidth(){
		return mario1.getWidth(null);
	}
	
	/**
	 * This will get the sprite's height.
	 */
	public int getHeight(){
		return mario1.getHeight(null);
	}
	
	
	/**
	 * This will get the sprite/image.
	 */
	public Image getImage(){
		return mario1;
	}
}