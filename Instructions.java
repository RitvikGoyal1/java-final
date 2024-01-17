//imports
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*
 * Andrew Hu & Ritvik Goyal
 * H. Strelkovska
 * ICS3U7
 * 2023-12-30
 * Instructions: A demo version of the game which is interactive to players movements and displays what the players shoudl do next
 */
public class Instructions extends JPanel implements ActionListener{
    // Variable declaration
    static boolean right;
    static JLabel label;
    ImageButton backBtn;
    public Instructions(){
        this.setLayout(null);
        // Makes an instance of PaintMain level 1 for the user to play in
        PaintMain paintman = new PaintMain(1,true);
        paintman.setBounds(0,0,400,400);
        this.add(paintman);
        // Outputs the instructions step by step
        label = new JLabel("Welcome, Follow the Instructions shown here!");
        label.setBounds(400,40,400,30);
        this.add(label);
        label = new JLabel("Start by going Right to paint the Walls and get Star!");
        label.setBounds(400,60,400,30);
        this.add(label);
        // Back button
        ImageIcon back = new ImageIcon("gamedata/media/back.png");
        backBtn = new ImageButton("");
        backBtn.setBI(back.getImage());
        backBtn.addActionListener(this);
        backBtn.setBounds(500,0,70,20);
        this.add(backBtn);
        this.add(label);
        repaint();
    }
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        // Updates the arrow keys and wasd keys desplayed on the screen that the user should click next
        g.setColor(Color.DARK_GRAY);
        if (label.getText().equals("Go Up To Finish the game!"))g.fillRect(450, 100, 50, 30);
        g.drawRect(450, 100, 50, 30);
        g.drawString("\u2191", 470, 120);
        if (label.getText().equals("Go Left to paint the Walls and get the Star!"))g.fillRect(400, 130, 50, 30);
        g.drawRect(400, 130, 50, 30);
        g.drawString("\u2190", 420, 150);
        if (label.getText().equals("Go Down to paint the walls and get the star!"))g.fillRect(450, 130, 50, 30);
        g.drawRect(450, 130, 50, 30);
        g.drawString("\u2193", 470, 150);
        if (label.getText().equals("Start by going Right to paint the Walls and get Star!"))g.fillRect(500, 130, 50, 30);
        g.drawRect(500, 130, 50, 30);
        g.drawString("\u2192", 520, 150);
        g.drawString("You May Use WASD Or Arrow Keys as Controls.",380,190);
        if (label.getText().equals("Go Up To Finish the game!"))g.fillRect(450, 200, 50, 30);
        g.drawRect(450, 200, 50, 30);
        g.drawString("W", 470, 220);
        if (label.getText().equals("Go Left to paint the Walls and get the Star!"))g.fillRect(400, 230, 50, 30);
        g.drawRect(400, 230, 50, 30);
        g.drawString("A", 420, 250);
        if (label.getText().equals("Go Down to paint the walls and get the star!"))g.fillRect(450, 230, 50, 30);
        g.drawRect(450, 230, 50, 30);
        g.drawString("S", 470, 250);
        if (label.getText().equals("Start by going Right to paint the Walls and get Star!"))g.fillRect(500, 230, 50, 30);
        g.drawRect(500, 230, 50, 30);
        g.drawString("D", 520, 250);
    }
    // Updates the string that tells the user what to do next
    public static void setString(String s){
        if (s.length()>0){
            label.setText(s);
            label.getParent().repaint();
        }
    }
    // Checks if the back button was clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backBtn){
            Main.cardsL.show(Main.c,"mainmenu");
        }
    }

}
