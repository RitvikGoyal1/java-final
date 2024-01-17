// Imports
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*
 * Andrew Hu & Ritvik Goyal
 * H. Strelkovska
 * ICS3U7
 * 2023-12-30
 * Credits: Display the authers, teacher and course code
 */

public class Credits extends JPanel implements ActionListener{
    // Variable Declaration
    private String name1, name2;
    ImageIcon bgImg;
    JButton backBtn;
    // Constructor
    public Credits(){
        // Assigning values to the variables
        bgImg = new ImageIcon("gamedata/media/bgImg.png");
        name1 = "Andrew Hu";
        name2 = "Ritvik Goyal";
        // Creating Back Buttons
        backBtn = new JButton("Back");
        backBtn.addActionListener(this);
        backBtn.setBounds(900,100,70,20);
        backBtn.setBackground(Color.RED);
        this.add(backBtn);
        // Calls paint component
        repaint();
        
    }
    // Outputs the string and rectangles
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        // Gets the windows width and height
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        // Draws the background image for the credits page
        g.drawImage(bgImg.getImage(), 0, 0, panelWidth, panelHeight, null);
        // Draws the rectange
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(320, 30, 200, 190);
        // Draws the strings 
        g.setColor(Color.BLACK);
        g.drawString("Authors:", 350,50);
        g.drawString(name1, 350,80);
        g.drawString(name2, 350,110);
        g.drawString("Teacher: Ms. Strelkovoska", 350,140);
        g.drawString("Course: ICS 3U7", 350,170);

    }
    // Actionlistener to check for back button click
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backBtn){
            Main.cardsL.show(Main.c,"mainmenu");
        }
    }
}
