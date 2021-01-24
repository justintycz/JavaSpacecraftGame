package meteorGame;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

//This Class is used just to create the initial display/background
public class Display extends JPanel implements ActionListener {

    private Timer timer;
    private Spaceship spaceship;
    private List<Meteors> meteors;
    private boolean game_start;
    private final int StartPos_X = 40;
    private final int StartPos_Y = 60;
    private final int B_WIDTH = 1000;
    private final int B_HEIGHT = 1400;
    private final int DELAY = 15;
    private int score;
    
    private JButton calcButton;
    private JPanel button;
    
    //Used to create a random multidimentional array, for position on the meteors
    private final int[][] pos;
    private RandomPosition random_meteors = new RandomPosition();
    
    //Main method that sets up the program
    public Display() {
    	
    	pos = random_meteors.getArray();
        Board();
    }
    
    //Creates the first panel, with a Black background at the set HxW
    private void Board() {
    	
    	//Requests focus so when it is reset the key listener refocuses to the Board()
    	requestFocusInWindow();
    	//Used to look for the Keyboard
        addKeyListener(new Keyboard());
        //Sets the next method up to be ready to receive data
        setFocusable(true);
        //Sets the background to black
        setBackground(Color.BLACK);
        
        game_start = true;
        
        setLayout(new BorderLayout());
        
        buildButton();
        add(button, BorderLayout.SOUTH);
        //Sets the size of the Display
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
		
		//Creates a spaceship that looks for the created Spaceship Class
        spaceship = new Spaceship(StartPos_X, StartPos_Y);
       
        startMeteor();
        
        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    //Builds the button that restarts the program
    private void buildButton() {
		
		Font font = new Font(Font.SERIF, 0, 30);
		
		calcButton = new JButton("START");
		calcButton.setFont(font);
		calcButton.addActionListener(new CalcButtonListener());
		
		button = new JPanel();
		
		button.setBackground(Color.lightGray);
		button.addKeyListener(new Keyboard());
		button.add(calcButton);
		
		button.setVisible(true);
	}
    
    //Class that Looks for when the button is pressed and resets
    private class CalcButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			Board();
		}
	}

    //Creates a list of meteors and calls out the meteor program
    public void startMeteor() {
        
        meteors = new ArrayList<>();

        for (int[] p : pos) {
            meteors.add(new Meteors(p[0], p[1]));
        }
    }
    
    //Used to control the game, whether its started or you lose, it controls the graphics
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (game_start) {

            drawObjects(g);

        } else {

            drawGameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }
    
    //When the game is started this gets the initial graphics
    private void drawObjects(Graphics g) {
    	
    	Font font = new Font(Font.SERIF, 0, 30);
		
		g.setFont(font);
		
    	//When the game starts the display will get the image and its location
        if (spaceship.isVisible()) {
            g.drawImage(spaceship.getImage(), spaceship.getX(), spaceship.getY(),
                    this);
        }
        
        //This will generate the meteors and their locations in the array
        for (Meteors meteor : meteors) {
            if (meteor.isVisible()) {
                g.drawImage(meteor.getImage(), meteor.getX(), meteor.getY(), this);
            }
        }
        g.setColor(Color.WHITE);
        g.drawString("SCORE: " + score, 5, 30);
    }
    //When you lose it will display GAME OVER
    private void drawGameOver(Graphics g) {
		
        String msg = "Game Over";
        String msg_score = "Your score: " + score;
        Font small = new Font("Helvetica", Font.BOLD, 50);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
                B_HEIGHT / 2);
        g.drawString(msg_score, ((B_WIDTH - fm.stringWidth(msg)) / 2) - 40,
                (B_HEIGHT / 2) + 60);
    }
    
    //Looks for any action performed and updates the images on the screen
    @Override
    public void actionPerformed(ActionEvent e) {

        inGame();

        updateShip();
        updateMeteors();

        checkCollisions();

        repaint();
    }
    
    //If the game start is set to FALSE it will stop the timer
    private void inGame() {

        if (!game_start) {
            timer.stop();
        }
    }
    //Used to update the Ships image
    private void updateShip() {

        if (spaceship.isVisible()) {
            
            spaceship.move();
        }
    }

    //Used to update the meteors image and take it off the screen when the visibility gets turned off
    private void updateMeteors() {
    	
    	
    	
    	//If the meteor does not contain anything, then end the game
        if (meteors.isEmpty()) {

            game_start = false;
            return;
        }
        
        //Sets up every meteor to move or disappear
        for (int i = 0; i < meteors.size(); i++) {

            Meteors a = meteors.get(i);
            
            if(a.zero()) {
            	score += 1;
            }
            
            if (a.isVisible()) {
                a.move();
            } else {
                meteors.remove(i);
            }
        }
    }
    
    //Checks if the spaceship and meteor images touch, then ends the game
    public void checkCollisions() {

        Rectangle r3 = spaceship.getBounds();

        for (Meteors meteor : meteors) {
            
            Rectangle r2 = meteor.getBounds();
            
            //if the two images intersect
            if (r3.intersects(r2)) {
                
                spaceship.setVisible(false);
                meteor.setVisible(false);
                game_start = false;
            }
        }
    }
    
    //Checks the space ship control to see if a key has been pressed or released
    class Keyboard extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            spaceship.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            spaceship.keyPressed(e);
        }
    }
}