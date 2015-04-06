package mario.sprites;
import java.awt.Image;

import mario.screen.Animation;

/**
 * This is the movement for one of the bowsers
 * @author Davy
 *
 */
public class Sprite2 {
	
	private Animation a;
	private float x = 700;
	private float y = 0;
	//velocity of x and y.
	private float vx ;
	private float vy ;
	
	public Sprite2(Animation a){
		this.a = a;
	}
	
	/**
	 * This changes the position of the sprite based on the amount of time passed.
	 * @param timePassed This is the time passed.
	 */
	public void update(long timePassed){
		x += vx * timePassed;
		y += vy * timePassed;
		a.update(timePassed);
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
	 * This will set the x position
	 */
	public void setX(float x){
		this.x = x;
	}
	
	/**
	 * This will set the y position.
	 */
	public void setY(float y){
		this.y = y;
	}
	
	/**
	 * This will get the sprite's width.
	 */
	public int getWidth(){
		return a.getImage().getWidth(null);
	}
	
	/**
	 * This will get the sprite's height.
	 */
	public int getHeight(){
		return a.getImage().getHeight(null);
	}
	
	/**
	 * This will get the horizontal velocity (speed).
	 */
	public float getVelocityX(){
		return vx;
	}
	
	/**
	 * This will get the vertical velocity (speed).
	 */
	public float getVelocityY(){
		return vy;
	}
	
	/**
	 * This will set the horizontal velocity
	 */
	public void setVelocityX(float vx){
		this.vx = vx;
	}
	
	/**
	 * This will set the vertical velocity
	 */
	public void setVelocityY(float vy){
		this.vy = vy;
	}
	
	/**
	 * This will get the sprite/image.
	 */
	public Image getImage(){
		return a.getImage();
	}
}