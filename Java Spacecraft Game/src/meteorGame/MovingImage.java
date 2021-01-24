package meteorGame;

import java.awt.EventQueue;
import javax.swing.JFrame;

//Creates the initial Display
public class MovingImage extends JFrame {

    public MovingImage() {
        
        initial();
    }
    
    //Creates the display and sets it up
    private void initial() {

        add(new Display());

        setTitle("Meteor Shower");
        setSize(1000, 700);
        
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            MovingImage ex = new MovingImage();
            ex.setVisible(true);
        });
    }
}