package meteorGame;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

//Controls the Image
public class ImageControl {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean visible;
    protected Image image;

    //When called it requires a position
    public ImageControl(int x, int y) {
    	
    	//Gives the position to the keylistener
        this.x = x;
        this.y = y;
        visible = true;
    }
    
    //Gets the dimension of the image square
    protected void getImageDimensions() {

        width = image.getWidth(null);
        height = image.getHeight(null);
    }
    
    //Loads the image
    protected void loadImage(String imageName) {

        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}