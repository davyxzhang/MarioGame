package mario.screen;
import java.awt.*;
import java.util.ArrayList;
/**
 * This will animate the two bowsers
 * @author Davy
 *
 */
public class Animation {
	
	private ArrayList scenes;
	private int sceneIndex;
	private long movieTime;
	private long totalTime;
	
	/**
	 * Constructor that contains a new arraylist and sets the total time to 0.
	 */
	public Animation(){
		scenes = new ArrayList();
		totalTime = 0;
		start();
	}

	/**
	 * This will allow the variables to be changed one at a time since it is synchronized.
	 * This will also add another picture/scene to the array list and sets the time for the scene.
	 * @param i The first image you want to appear.
	 * @param t How long you want to let it appear for.
	 */
	public synchronized void addScene(Image i, long t){
		totalTime +=t;
		scenes.add(new OneScene(i, totalTime));
	}
	
	/**
	 * Start the animation from the beginning. Resets everything to 0.
	 */
	public synchronized void start(){
		movieTime = 0;
		sceneIndex = 0;
	}
	
	/**
	 * Changing scenes. This causes the animation to stop after it is done.
	 */
	public synchronized void update(long timePassed){
		if(scenes.size()>1){
			movieTime += timePassed;
			if(movieTime >= totalTime){
				movieTime = 0;
				sceneIndex = 0;
			}
			/**
			 * This tells the program to move to the next  after one  is done.
			 */
			while(movieTime > getScene(sceneIndex).endTime){
				sceneIndex++;
			}
		}
	}
	
	/**
	 * This method will get the current animation
	 */
	public synchronized Image getImage(){
		if(scenes.size() == 0){
			return null;
		}else{
			return getScene(sceneIndex).pic;
		}
	}
	
	/**
	 * This method gets the image.
	 */
	private OneScene getScene(int x){
		return (OneScene)scenes.get(x);
	}
	
	/**
	 * Building the image class. You can try separating this into another separate class.
	 * This creates an object for each picture you pass into it.
	 */
	private class OneScene{
		Image pic;
		long endTime;
		
		public OneScene(Image pic, long endTime){
			this.pic = pic;
			this.endTime = endTime;
		}
	}
}
