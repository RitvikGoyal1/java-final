/*
 * Andrew Hu & Ritvik Goyal
 * H. Strelkovska
 * ICS3U7
 * 2023-12-30
 * Main: driver program
 */

// imports
import java.awt.*;
import javax.swing.*;

public class Main extends JFrame{
    static CardLayout cardsL;
	static Container c;
    public static int screenHeight;
    public static void main(String[] args) {
        // initializing game window
        Main m = new Main();
        // set game window
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenHeight = (int)screenSize.getHeight()-50;
        m.setSize(1100, screenHeight);
        m.setVisible(true);
    }
    public Main(){
        c=getContentPane();
        cardsL=new CardLayout();
	    c.setLayout(cardsL);	   
	    BG bg = new BG();
        Credits credits = new Credits();
        GameMenu gamemenu = new GameMenu();
        Instructions instructions = new Instructions();
        MainMenu mainmenu = new MainMenu();
  	    c.add("bg", bg);
        c.add("credits", credits);
        c.add("gamemenu", gamemenu);
        c.add("instructions", instructions);
        c.add("mainmenu", mainmenu);
        cardsL.show(c,"bg");
    }
}