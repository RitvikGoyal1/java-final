/*
 * Andrew Hu & Ritvik Goyal
 * H. Strelkovska
 * ICS3U7
 * 2023-12-30
 * PaintMain: standalone GUI display for regular level
 */

// JSwing imports
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class PaintMain extends JPanel implements KeyListener, ActionListener {

    // private fields for interacting with dojo
    public Dojo myDojo;
    private String[][] id; // array of game tiles

    // java swing fields

    private MyPanel panel;
    int level;
    static ImageButton j;
    boolean instructions;
    static int num;
    // constructor, initialize Dojo and JSwing items with level parameter
    public PaintMain(int level, boolean instructions) {
        // sets game window title
        this.instructions = instructions;
        this.level= level;
        // sets dojo class
        this.setLayout(new BorderLayout());
        myDojo = new Dojo(level);
        id = myDojo.getArr();
        ImageIcon back = new ImageIcon("gamedata/media/back.png");
        j = new ImageButton("");
        j.setBI(back.getImage());
        j.addActionListener(this);
        j.setBounds(500,0,70,20);
        this.add(j);

        // sets jswing container
        this.addKeyListener(this);
        

        // sets jpanel
        panel = new MyPanel(id, id.length,id[0].length);
        panel.setNinja(myDojo);
        if (!instructions)
            panel.setBackBtn(true);
        this.add(panel, BorderLayout.CENTER);
        setFocusable(true);
        this.requestFocus();        
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
            this.add(j);
            panel.set(myDojo);
            for (int i = 0; i < 3; i++) {
                panel.setNinja(myDojo.getCurrentAnimation() + (i+1));
                panel.getNinja().set(62,62);
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

            if (myDojo.getArr()[myDojo.getPlayerY()][myDojo.getPlayerX()].equals("alava") && !myDojo.checkWin()) {
                JOptionPane.showMessageDialog(null, "You went in the lava and lost.", "Game Exit", JOptionPane.PLAIN_MESSAGE);
                b = false;
                GameMenu.doneL(level-1, 2);
                GameMenu game_menu = new GameMenu();
                num+=1;
                Main.c.add("game_menu"+num, game_menu);
                Main.cardsL.show(Main.c,"game_menu"+num);
            }

            freshstart = false;
            GameMenu.starL(level-1, myDojo.getStars(level-1));
            if (myDojo.checkWin()) {
                JOptionPane.showMessageDialog(null, "You Won.\nScore: "+myDojo.getScore()+"\nStars: "+GameMenu.getStars(level-1), "Game Exit", JOptionPane.PLAIN_MESSAGE);
                b = false;
                if (!instructions){
                GameMenu.doneL(level-1, 1);
                num+=1;
                GameMenu game_menu = new GameMenu();
                Main.c.add("game_menu"+num, game_menu);
                Main.cardsL.show(Main.c,"game_menu"+num);
            }
            if (instructions){
                Main.cardsL.show(Main.c,"mainmenu");
            }
            } else if (myDojo.checkLose()) { 
                JOptionPane.showMessageDialog(null, "You went in the lava and lost.", "Game Exit", JOptionPane.PLAIN_MESSAGE);
                b = false;
                GameMenu.doneL(level-1, 2);
                GameMenu game_menu = new GameMenu();
                num+=1;
                Main.c.add("game_menu"+num, game_menu);
                Main.cardsL.show(Main.c,"game_menu"+num);
            }
        }
        return;
    }

    // check for keystrokes and do move
    public void keyReleased(KeyEvent e) {
        if (!myDojo.checkLose() && !myDojo.checkWin()){
            if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
                Integer[] direction = {0,1};
                if (instructions) Instructions.setString("Go Down to paint the walls and get the star!");
                pushNinja(direction);

            }
            if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
                Integer[] direction = {-1,0};
                pushNinja(direction);
                
                return;
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                Integer[] direction = {0,-1};
                pushNinja(direction);
                if (instructions) Instructions.setString("Go Up To Finish the game!");
                return;
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
                Integer[] direction = {1,0};
                pushNinja(direction);
                if (instructions) Instructions.setString("Go Left to paint the Walls and get the Star!");
                return;
            }
        }
    }
    public void keyTyped(KeyEvent e) {}
    public void keyPressed(KeyEvent e) {}

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==j){
        num+=1;
        GameMenu.doneL(level-1,0);
        GameMenu game_menu = new GameMenu();
        Main.c.add("game_menu"+num, game_menu);
        Main.cardsL.show(Main.c,"game_menu"+num);
        }
    }
}