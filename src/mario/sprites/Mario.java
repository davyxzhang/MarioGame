package mario.sprites;
import java.awt.Image;

import javax.swing.ImageIcon;
/**
 * This class updates Mario's positions
 * @author Davy and Chris
 *
 */
public class Mario {
	
	private float x = 410;
	private float y = 422;
	boolean collide = false;
	
	Image mario1 = new ImageIcon("img\\mariofly.gif").getImage();
	public Mario(){
	}
	
	public void updateXLeft(){
		if(x<5){
			x = 0;
		}else{
			x += -20F;	
		}
	}
	
	public void updateXRight(){
		if(x>758){
			x = 774;
		}else{
			x += 20F;	
		}
	}
	
	public void updateYUp(){
		if(y<20){
			y = 0;
		}
		else{
			y += -20F;	
		}
	}
	
	public void updateYDown(){
		if(y>550){
			y = 572;
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