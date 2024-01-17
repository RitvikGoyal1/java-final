// imports
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Andrew Hu & Ritvik Goyal
 * H. Strelkovska
 * ICS3U7
 * 2023-12-30
 * BG: standalone GUI for initial Play button and curtain animation
 */

public class BG extends JPanel implements ActionListener {
    // components of GUI display
    private Timer t;
    private ImageButton startBtn;
    private ImageIcon startImg1, startImg2,bgImg, startImg;
    private int curtainWidth = 0;
    public int panelWidth, panelHeight;

    // constructor, initialize fields
    public BG() {
        this.setLayout(null);
        startImg1 = new ImageIcon("gamedata/media/curtain1.png");
        startImg2 = new ImageIcon("gamedata/media/curtain2.png");
        bgImg = new ImageIcon("gamedata/media/bgImg.png");
        startImg = new ImageIcon("gamedata/media/start.png");
        startBtn = new ImageButton("");
        startBtn.setBI(startImg.getImage());
        startBtn.addActionListener(this);
        repaint();
        startBtn.setBounds(500,0,100,50);
        this.add(startBtn);
        this.setBackground(Color.BLACK);
        t = new Timer(20, this);
    }

    // display update
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        panelWidth = getWidth();
        panelHeight = getHeight();
        // Draws the background image which shows as the curtains open
        g.drawImage(bgImg.getImage(), 0, 0, panelWidth, panelHeight, null);
        // Draws the curtain images based on their position to fit the animation
        g.drawImage(startImg1.getImage(), 0, 0, panelWidth / 2 - curtainWidth, panelHeight, null);
        g.drawImage(startImg2.getImage(), panelWidth / 2 + curtainWidth, 0, panelWidth / 2, panelHeight, null);
    }

    // listen for button actions
    public void actionPerformed(ActionEvent e) {
        // Checks if start button is clicked 
        if (e.getSource() == startBtn) {
            t.start();
            startBtn.setVisible(false);
        }
        // Checks if the timer hit the 20 millisecond mark, if it did then increases the curtain width by 5
        else if (e.getSource() == t) {
            curtainWidth += 5;
            // checks if the curtainWidth is bigger than half of the window size
            if (curtainWidth >= getWidth()/2) {
                // If it is, it moves on to the new scene, Main Menu
                Main.cardsL.show(Main.c,"mainmenu");
                t.stop();
            }
            // Repaints it to update the curtain position
            repaint();
        }
    }
}
