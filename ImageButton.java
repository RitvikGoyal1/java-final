// Imports
import java.awt.*;
import javax.swing.*;

class ImageButton extends JButton {
    // Variable declaration
    private Image backgroundImage;
    Color c;
    String s;
    // Constructor 
    public ImageButton(String s) {
        this.s= s;
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setPreferredSize(new Dimension(100, 30));
    }
    // Paint the image or color over the button
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
        if (c!=null){
            g.setColor(c);
            g.fillRect(0, 0, 100, 30);}
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
        g.setColor(Color.BLACK);
        g.drawString(s, 30, 20);
    }

    // getter and setter methods for backgroundImage
    // Set background image is a method used to update the background image used for the button
    public void setBI(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
        repaint(); // Repaint the button when the background image changes
    }
    // setC is used to set the color of the button
    public void setC(Color c){
        this.c= c;
    }
}