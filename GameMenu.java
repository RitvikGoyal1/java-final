/*
 * Andrew Hu & Ritvik Goyal
 * H. Strelkovska
 * ICS3U7
 * 2023-12-30
 * GameMenu: Displays all the possible levels available and redirects the user to the level that they have selected.
 */

// imports
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameMenu extends JPanel implements ActionListener{
    // level buttons, array for level buttons, background, and hasClicked logic
    ImageIcon bgImg,star1,star2,star3,back;
    ImageButton backBtn;
    ImageButton[] buttons = new ImageButton[12];
    public static int[] done = new int[12];
    public static int[] stars = new int[12];
    static int num;

    // constructor, initialize fields
    public GameMenu() {
        this.setLayout(new FlowLayout());
        // Sets images to the variables
        bgImg = new ImageIcon("gamedata/media/bgImg.png");
        star1 = new ImageIcon("gamedata/media/star1.png");
        star2 = new ImageIcon("gamedata/media/star2.png");
        star3 = new ImageIcon("gamedata/media/star3.png");
        back = new ImageIcon("gamedata/media/back.png");
        this.setLayout(null);
        // Sets x and y for the buttons
        int x = 20;
        int y = 200;
        // Makes a back Button
        backBtn = new ImageButton("");
        backBtn.setBI(back.getImage());
        backBtn.addActionListener(this);
        backBtn.setBounds(500,0,70,20);
        this.add(backBtn);
        // construct buttons in a pattern
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new ImageButton("Game " + (i + 1));
            buttons[i].addActionListener(this);
            
            buttons[i].setBounds(x, y, 100, 30);
            // Uses arrays and if statements to set the correct color and number of stars
            if (done[i]==1)
                if (stars[i]==1)
                    buttons[i].setBI(star1.getImage());
                else if (stars[i]==2)
                    buttons[i].setBI(star2.getImage());
                else if (stars[i]==3)
                    buttons[i].setBI(star3.getImage());
                else
                    buttons[i].setC(Color.GREEN);
            else if (done[i]==2)
                buttons[i].setC(Color.RED);
            else
            buttons[i].setC(Color.ORANGE);
            this.add(buttons[i]);
            // Uses if statements to make a pattern with the game buttons
            if ((i+1) % 4 == 0) {
                x = 20;
                y += 50; // add 50 to y
                if (i == 7) {
                    x += 120; // add 120 to x
                }
            }
            x += 120; // add 120 to x
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g); // paint Component g in super
        // Gets the window's width and height
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        g.drawImage(bgImg.getImage(), 0, 0, panelWidth, panelHeight, null);
        Font title = new Font("Arial", Font.PLAIN, 30);
        g.setFont(title);
        g.drawString("Choose A Game!", 150,panelHeight/7);
    }
    // doneL is used to add values which indicate whether the player was able to finish the level or not and add that to done list
    public static void doneL(int index, int val){
        done[index]= val;
    }
    // starL is used to add how many starts the players scored and adds that to the stars list
    public static void starL(int index, int val){
        stars[index]= val;
    }
    // Returns the number of stars for a specific level
    public static int getStars(int level){
        return stars[level];
    }
    // Checks for actions
    public void actionPerformed(ActionEvent e) {
        // Checks if any game button is clicked and if it is then loads the appropriate level
        for (int i = 0; i < buttons.length; i++) {
            if (e.getSource() == buttons[i]) {
                // load game level
                PaintMain paintmain = new PaintMain(i+1,false);
                if (done[i]==0) done[i] = 1;
                num+=1;
                Main.c.add("paintmain"+num, paintmain);
                JOptionPane.showMessageDialog(null, "ARE YOU READY TO EXPERIENCE DA NINJA PAINTER LEVEL "+(i+1)+"!!??", "Game Enterance", JOptionPane.PLAIN_MESSAGE);
                Main.cardsL.show(Main.c,"paintmain"+num);
            }
        
        }
        // checks if back button is clicked
        if (e.getSource()==backBtn){
            System.out.println("e");
            Main.cardsL.show(Main.c,"mainmenu");
        }
    }
}
