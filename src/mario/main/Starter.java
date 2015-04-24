package mario.main;
import java.awt.*;

import javax.swing.*;

import mario.screen.Animation;
import mario.screen.ScreenManager;
import mario.sprites.Mario;
import mario.sprites.Sprite;
import mario.sprites.Sprite2;

/**
 * This is the main method that runs the whole game
 * @author Davy and Chris and Veng
 *
 */
public class Starter{	
	
	public static void main(String [] args){
		Starter ma = new Starter();
		/**
		 * This will call on the run method to display what we want on the screen.
		 */
		ma.run();
	}
	
	private ScreenManager screen;
	private Image bg;
	private Animation a1;
	private Animation a2;
	private Sprite sprite1 = new Sprite(a1);
	private Sprite2 sprite2 = new Sprite2(a2);
	private Mario m = new Mario();
	static boolean collide = false;
	static int k = 0;
	private Font f1 = new Font("Arial", 0, 16);
	private Font f2 = new Font("Arial", 0, 20);

	
	private static final DisplayMode modes1[] = {
		new DisplayMode(800, 600, 32, 0),
		new DisplayMode(800, 600, 24, 0),
		new DisplayMode(800, 600, 16, 0),
		new DisplayMode(640, 480, 32, 0),
		new DisplayMode(640, 480, 24, 0),
		new DisplayMode(640, 480, 16, 0),
	};
	
	/**
	 * This loads pictures from computer to java and then add the pictures into the scenes in the Animation class.
	 */
	public void loadPics(){
		bg = new ImageIcon("img\\MarioBG.jpg").getImage();
		bg = bg.getScaledInstance(screen.getWidth(), screen.getHeight(), Image.SCALE_DEFAULT);
		Image bowser1 = new ImageIcon("img\\bowserleft.gif").getImage();
		Image bowser2 = new ImageIcon("img\\bowserright.gif").getImage();
		
		a1 = new Animation();
		a1.addScene(bowser1, 450);
		a1.addScene(bowser2, 450);
		
		a2 = new Animation();
		a2.addScene(bowser1, 450);
		a2.addScene(bowser2, 450);
		
		sprite1 = new Sprite(a1);
		sprite1.setVelocityX(0.3F);
		sprite1.setVelocityY(0.3F);
		
		sprite2 = new Sprite2(a2);
		sprite2.setVelocityX(0.3F);
		sprite2.setVelocityY(0.3F);
	}
	
	/**
	 * This method will load the pictures and restore the screen.
	 * @param dm Opens the display mode.
	 */
	public void run(){
		screen = new ScreenManager();
		try{
			DisplayMode dm = screen.findFirstCompatibleMode(modes1);

			screen.setFullScreen(dm);
			ScreenManager.width = screen.getWidth();
			ScreenManager.height = screen.getHeight();
			loadPics();
			movieLoop();
		}finally{
			//screen.restoreScreen();
		}
	}
	
	/**
	 * This is the main movie loop method.
	 */
	public void movieLoop(){
		long startingTime = System.currentTimeMillis();
		long cumulativeTime = startingTime;
		
		/**
		 * Breaks out of the loop when the time passes 5 seconds.
		 */
		while(!collide){
			long timePassed = System.currentTimeMillis() - cumulativeTime;
			cumulativeTime += timePassed;
			a1.update(timePassed);
			a2.update(timePassed);
			
			Graphics2D g = screen.getGraphics();
			draw(g);
			g.dispose();
			screen.update();
			this.update(timePassed);
			//this will be used as score
			k++;
			
			int xdiff = ((int)(m.getX()+13)) - ((int)(sprite1.getX()+32));
			int ydiff = (int)((m.getY()+14)- (int)(sprite1.getY()+48));
						
			int xdiff3 = (int)((m.getX()+13)-(int)(sprite2.getX()+32));
			int ydiff3 = (int)((m.getY()+14)-(int)(sprite2.getY()+48));
			
			//checks for collision
			if((Math.abs(xdiff) < 32 && Math.abs(ydiff)< 48)||(Math.abs(xdiff3) < 32 && Math.abs(ydiff3)< 48)){	
				try{
					Thread.sleep(30000);
					break;
				}catch(Exception e){}
			}			
			/**
			 * This lets things catch up if anything is running.
			 */
			try{
				Thread.sleep(20);
			}catch(Exception ex){}
		}
	}
	
	Image mario1 = new ImageIcon("mariofly.gif").getImage();
	/**
	 *Draw method 
	 */
	public void draw(Graphics g){
		g.drawImage(bg, 0, 0, null);
		/**
		 * Sets the location of the images.
		 */
		g.drawImage(sprite1.getImage(), Math.round(sprite1.getX()), Math.round(sprite1.getY()), null);
		g.drawImage(sprite2.getImage(), Math.round(sprite2.getX()), Math.round(sprite2.getY()), null);
		g.drawImage(m.getImage(), Math.round(m.getX()), Math.round(m.getY()), null);
		
		FontMetrics fm = g.getFontMetrics();
		fm = g.getFontMetrics();
		g.setFont(f2);
		g.drawString("Mario Survival", screen.getWidth()/2-fm.stringWidth("Mario Survival")/2, 70-fm.getHeight()/2);
		g.setFont(f1);
		g.drawString("Score:"+k, screen.getWidth()/2-fm.stringWidth("Score:         ")/2, 90-fm.getHeight()/2);
		g.drawString("Press ESC to exit", screen.getWidth()/2-fm.stringWidth("Press ESC to exit")/2, 110-fm.getHeight()/2);

	}
	
	/**
	 * This will update the sprite's positions. This will also make sure the sprite does not go off the screen.
	 * @param timePassed update's sprite's positions based on the time passed.
	 */
	public void update(long timePassed){
		//sprite1 movement
		if(sprite1.getX() < 0){
			sprite1.setVelocityX(Math.abs(sprite1.getVelocityX()));
		}else if(sprite1.getX() + sprite1.getWidth() >= screen.getWidth()){
			sprite1.setVelocityX(-Math.abs(sprite1.getVelocityX()+(float).05));
		}
		
		if(sprite1.getY() < 0){
			sprite1.setVelocityY(Math.abs(sprite1.getVelocityY()));
		}else if(sprite1.getY() + sprite1.getHeight() >= screen.getHeight()){
			sprite1.setVelocityY(-Math.abs(sprite1.getVelocityY()+(float).05));
		}
		
		//sprite2 movement
		if(sprite2.getX() < 0){
			sprite2.setVelocityX(Math.abs(sprite2.getVelocityX()));
		}else if(sprite2.getX() + sprite2.getWidth() >= screen.getWidth()){
			sprite2.setVelocityX(-Math.abs(sprite2.getVelocityX()+(float).05));
		}
		
		if(sprite2.getY() < 0){
			sprite2.setVelocityY(Math.abs(sprite2.getVelocityY()));
		}else if(sprite2.getY() + sprite2.getHeight() >= screen.getHeight()){
			sprite2.setVelocityY(-Math.abs(sprite2.getVelocityY()+(float).05));
		}

		sprite1.update(timePassed);
		sprite2.update(timePassed);
		
		//position of mario update
		if(screen.keyDown){
			m.updateYDown();
			screen.keyDown = false;
			screen.keyUp = false;
			screen.keyLeft = false;
			screen.keyRight = false;
		}
		if(screen.keyUp){
			m.updateYUp();
			screen.keyDown = false;
			screen.keyUp = false;
			screen.keyLeft = false;
			screen.keyRight = false;
		}
		if(screen.keyLeft){
			m.updateXLeft();
			screen.keyDown = false;
			screen.keyUp = false;
			screen.keyLeft = false;
			screen.keyRight = false;
		}
		if(screen.keyRight){
			m.updateXRight();
			screen.keyDown = false;
			screen.keyUp = false;
			screen.keyLeft = false;
			screen.keyRight = false;
		}
	}
}
