// imports
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/*
 * Andrew Hu & Ritvik Goyal
 * H. Strelkovska
 * ICS3U7
 * 2023-12-30
 * Main Menu: Displays Play, Instructions, Exit and Credits button
 */
class MainMenu extends JPanel implements ActionListener {
    JButton playBtn, instructionsBtn, exitBtn, creditsBtn;
    ImageIcon bgImg;
    public static int panelWidth, panelHeight, num;

    public MainMenu() {
        // defins all the buttons
        playBtn = new JButton("Play");
        instructionsBtn = new JButton("Instructions");
        exitBtn = new JButton("Exit");
        creditsBtn = new JButton("Credits");
        bgImg = new ImageIcon("gamedata/media/bgImg.png");
        // adds actionlistener to all the buttons
        exitBtn.addActionListener(this);
        playBtn.addActionListener(this);
        instructionsBtn.addActionListener(this);
        creditsBtn.addActionListener(this);
        this.setLayout(new FlowLayout());
        // Sets creative borders
        playBtn.setBorder(BorderFactory.createBevelBorder(1, Color.green, Color.red));
        this.add(playBtn);
        instructionsBtn.setBorder(BorderFactory.createBevelBorder(1, Color.green, Color.blue));
        this.add(instructionsBtn);
        exitBtn.setBorder(BorderFactory.createBevelBorder(0));
        this.add(exitBtn);
        creditsBtn.setBorder(BorderFactory.createBevelBorder(1, Color.green, Color.orange));
        this.add(creditsBtn);
    }
    // Paint componenet to display certain things
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        panelWidth = getWidth();
        panelHeight = getHeight();
        g.drawImage(bgImg.getImage(), 0, 0, panelWidth, panelHeight, null);
        playBtn.setBounds(panelWidth *35/100, panelHeight - panelHeight*7/10, panelWidth/4, panelHeight/8);
        instructionsBtn.setBounds(panelWidth *35/100, panelHeight - panelHeight*5/10, panelWidth/4, panelHeight/8);
        exitBtn.setBackground(Color.RED);
        exitBtn.setBounds(panelWidth *38/100, panelHeight - panelHeight/9, panelWidth/6, panelHeight/10);
        creditsBtn.setBounds(panelWidth *35/100, panelHeight - panelHeight*3/10, panelWidth/4, panelHeight/8);

        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("gamedata\\e.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            customFont = customFont.deriveFont(Font.PLAIN, 40);
            g.setFont(customFont);
        } catch(Exception e) {
            System.out.println("Error: "+e);
        }

        g.setColor(Color.RED);
        g.drawString("Paint", panelWidth/3,panelHeight/7);
        g.setColor(Color.BLACK);
        Font manFont = new Font("Arial", Font.PLAIN, 30);
        g.setFont(manFont);
        g.drawString("Man", panelWidth/3+85,panelHeight/7+10);
    }
    // Action listener to check which button was clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitBtn) {
            JOptionPane.showMessageDialog(null, "That was an exit button", "My exit message", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        } 
        else if (e.getSource() == playBtn) {
            num+=1;
            GameMenu game_menu = new GameMenu();
            Main.c.add("game_menu"+num, game_menu);
            Main.cardsL.show(Main.c,"game_menu"+num);
        }
        else if (e.getSource() == creditsBtn) {
            Main.cardsL.show(Main.c,"credits");
        }
        else if (e.getSource() == instructionsBtn) {
            Instructions inst = new Instructions();
            num+=1;
            Main.c.add("inst"+num, inst);
            Main.cardsL.show(Main.c,"inst"+num);
            JOptionPane.showMessageDialog(null, "ARE YOU READY TO EXPERIENCE DA NINJA PAINTER INSTRUCTIONS!!??", "Game Enterance", JOptionPane.PLAIN_MESSAGE);
        }
    }
}