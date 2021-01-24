package meteorGame;

import java.util.*;

//Creates the meteors image and its initial position it resets from
public class Meteors extends ImageControl {

    private final int INITIAL_X = 2000;
    private Random rand = new Random();
    private int random;
    
    private boolean zero;
    
    //When the Class is called it will input a position
    public Meteors(int x, int y) {
        super(x, y);

        startMeteor();
    }
    
    //Gets the image of 3 possible meteor sizes, randomly
    private void startMeteor() {
    	
    	random = rand.nextInt(3);
    	
    	if(random == 0) {
    		loadImage("Meteor.jpg");
            getImageDimensions();
    	}
        if(random == 1) {
        	loadImage("Meteor1.jpg");
            getImageDimensions();
        }
        if(random == 2) {
        	loadImage("Meteor2.jpg");
            getImageDimensions();
        }
        if(random == 3) {
        	loadImage("Meteor3.jpg");
            getImageDimensions();
        }
    }
    
    //Resets the meteors if it crosses the back end and sets the value to zero, if its not at zero it moves the meteor 5 pixels 
    public void move() {
    	
        if (x < 0) {
            x = INITIAL_X;
            zero = true;
        }

        x -= 5;
    }
    
    //This is used to tell the Display that a meteor crossed 0
    public boolean zero() {
    	
    	if(zero == true) {
    		zero = false;
    		return true;
    	}
    	else {
    		return false;
    	}
    }
}
