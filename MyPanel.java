/*
 * Andrew Hu & Ritvik Goyal
 * H. Strelkovska
 * ICS3U7
 * 2023-12-30
 * MyPanel: main game display for a level, contains methods to update game display
 */

// imports
import java.awt.*;
import javax.swing.*;

public class MyPanel extends JPanel {
    // r, c is dimensions of display grid; rectangles is flattened display grid; id is the incoming display grid from Dojo
    private java.util.List<MyRect> rectangles = new java.util.ArrayList<>();
    private int r, c;
    private String[][] id;
    private boolean hasBackBtn;

    // ninja sprite
    private NinjaSprite ninja = new NinjaSprite(1, 1);

    // constructor, initialize rectangles and other fields
    public MyPanel(String[][] id, int r, int c){
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                addRectangle(id[i][j]);
            }
        }
        
        this.r = r;
        this.c = c;
        this.id = id;
    }

    // add rectangle and update display
    public void addRectangle(String value) {
        rectangles.add(new MyRect(value));
        repaint();
    }

    // set rectangle to new value and update display
    public void set(int pos, String newValue) {
        rectangles.set(pos, new MyRect(newValue));
        repaint();
    }
    public void setBackBtn(boolean hasBackBtn){
        this.hasBackBtn = hasBackBtn;
    }

    // draw rectangles and ninja
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (hasBackBtn)
            this.add(PaintMain.j);
        // const size of each game tile
        int w = 62;
        int h = 62;

        // update each item in rectangles from id[][] and draw onto display; draw ninja at the end
        int count = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                rectangles.set(count, new MyRect(id[i][j], j * w, i * h, w, h));
                rectangles.get(count).paintComponent(g);
            }
            count++;
        }
        ninja.set(w,h);
        ninja.draw(g);
        
        
    }

    // setter for multiple fields from input from BlindDojo
    public void set(BlindDojo myDojo) {
        int c = 0;
        for (int i = 0; i < myDojo.get9().length; i++) {
            for (int j = 0; j < myDojo.get9()[0].length; j++) {
                set(c, myDojo.get9()[i][j]);
                c++;
            }
        }
        id = myDojo.get9();
    }

    // setter for multiple fields from input from Dojo
    public void set(Dojo myDojo) {
        int c = 0;
        for (int i = 0; i < myDojo.getArr().length; i++) {
            for (int j = 0; j < myDojo.getArr()[0].length; j++) {
                set(c, myDojo.getArr()[i][j]);
                c++;
            }
        }
        id = myDojo.getArr();
    }

    // set ninja animation and update display for BlindDojo
    public void setNinja(BlindDojo myDojo) {
        ninja.setCurrentAnimation(myDojo.getCurrentAnimation());
        repaint();
    }

    // set ninja animation and position and update display for Dojo
    public void setNinja(Dojo myDojo) {
        ninja.setCurrentAnimation(myDojo.getCurrentAnimation());
        ninja.setPlayerX(myDojo.getPlayerX());
        ninja.setPlayerY(myDojo.getPlayerY());
        repaint();
    }

    // change animation of ninja only
    public void setNinja(String newAnimation) {
        ninja.setCurrentAnimation(newAnimation);
        repaint();
    }

    // getter so that ninja.draw() can be directly called from PaintMain or BlindMain
    public NinjaSprite getNinja() {
        return ninja;
    }

    // screensize shenanigans
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public int width() {
        return (int) screenSize.getWidth();
    }
    
    public int height() {
        return (int)screenSize.getHeight();
    }
}