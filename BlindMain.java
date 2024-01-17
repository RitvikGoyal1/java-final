/*
 * Andrew Hu & Ritvik Goyal
 * H. Strelkovska
 * ICS3U7
 * 2023-12-30
 * BlindMain: standalone GUI display for blind-type level
 */

// TODO: issue with updating the 9 tiles

// JSwing imports
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

// driver program with initializers
public class BlindMain extends JFrame implements KeyListener {
    public static void main(String[] args) {
        new BlindMain(7);
    }

    // private fields for interacting with dojo
    public BlindDojo myDojo;
    private String[][] id; // array of game tiles

    // java swing fields

    private MyPanel panel;
    private Container c;

    // constructor, initialize Dojo and JSwing items with level parameter
    public BlindMain(int level) {
        // sets game window title
        super("Level " + level);

        // sets dojo class
        myDojo = new BlindDojo(level);
        id = myDojo.get9();

        // sets jswing container
        c = getContentPane();
        c.setLayout(new BorderLayout());
        addKeyListener(this);

        // sets jpanel
        panel = new MyPanel(id, id.length, id[0].length);
        panel.setNinja(myDojo);
        c.add(panel, BorderLayout.CENTER);

        // code for screen resizing

        int screen_w = panel.width();
        int screen_h = panel.height();

        // TODO: get rid of this in accordance with RITVIKGOYAL
		while (screen_w != screen_h) {
		    if (screen_h > screen_w)
			    screen_h -= 1;
		    if (screen_w > screen_h)
			    screen_w -= 1;
		}

        // set JSwing parameters
        setSize(screen_w-5, screen_h-5);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    // essentially the "gameloop" of the level, runs until the ninja hits a wall or ladder
    // animation, ticks, display update all provided here
    // called by KeyListener
    public void pushNinja(Integer[] direction) {
        // fields useful for graph theory; do not question what they do
        boolean b = true;
        boolean freshstart = true;

        // score decreases each time player moves, +50 when the player lands on a star; for maximized score,
        // use fewest number of moves to paint all the tiles
        myDojo.decreaseScore();

        // set starting animation based on initial takeoff direction
        myDojo.setPrelimAnimation(direction);

        // because of the ticks/player moving animations, b is true when the move is still going on
        while (b) {
            // do animation and display with delay
            panel.set(myDojo);
            for (int i = 0; i < 3; i++) {
                panel.setNinja(myDojo.getCurrentAnimation() + (i+1));
                panel.getNinja().set(62, 62);
                panel.getNinja().draw(panel.getGraphics());
                GameUtil.timeSleep(30);
            }
            panel.repaint();

            // update internal vars of myDojo
            b = myDojo.doMove(direction, freshstart);

            // update direction if needed; ie. if the ninja hits a half-wall
            if (myDojo.needsDirectionChange()) {
                direction = myDojo.getDirectionChange();
                myDojo.setHasDirectionChange(false);
            }

            // set ninja animation, then update game display
            panel.setNinja(myDojo);
            panel.set(myDojo);
            
            freshstart = false;

            // lose condition
            if (myDojo.checkLose()) {
                System.out.println("bruh you have lost! get cooked");
                System.exit(0); // TODO: fix this
            } else if (myDojo.checkWin()) { // win condition
                // System.out.println("You win!");
                // System.out.println("Score: " + myDojo.getScore());
                System.exit(0); // TODO: fix this
            }
        }
        return;
    }

    // check for keystrokes and do move
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            Integer[] direction = {0,1};
            pushNinja(direction);
            id = myDojo.get9();
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            Integer[] direction = {-1,0};
            pushNinja(direction);
            id = myDojo.get9();
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            Integer[] direction = {0,-1};
            pushNinja(direction);
            id = myDojo.get9();
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            Integer[] direction = {1,0};
            pushNinja(direction);
            id = myDojo.get9();
            return;
        }
    }
    
    // not implemented / empty implement
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
        }
    }
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
        }
    }
}