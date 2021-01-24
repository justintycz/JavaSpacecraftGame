package meteorGame;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class GameOver extends JFrame {

    public GameOver() {
        
        initial();
    }
    
    // Calls the Display and builds the initial Display and gives it title
    private void initial() {
        
        add(new Display());
        
        setResizable(false);
        pack();
        
        setTitle("Meteor Shower");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            GameOver ex = new GameOver();
            ex.setVisible(true);
        });
    }
}
