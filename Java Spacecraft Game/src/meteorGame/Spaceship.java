package meteorGame;


import java.awt.event.KeyEvent;

//Used to create the space ship and its location, then also look for the keyboard controls
public class Spaceship extends ImageControl {

    private int dx=0;
    private int dy=0;

    public Spaceship(int x, int y) {
        super(x, y);

        initialCraft();
    }

    private void initialCraft() {
        
    	//Loads the specified image from the job folder
        loadImage("Spaceship.jpg");
        getImageDimensions();
    }
    
    // Sets the movement of the spaceship when called
    public void move() {

        x += dx;
        y += dy;

        if (x < 1) {
            x = 1;
        }

        if (y < 1) {
            y = 1;
        }
    }
    
    //This method is used to control the spaceship movement by pixels
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -5;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 5;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -5;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 5;
        }
    }
    
    //Stops the Spaceship
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
}