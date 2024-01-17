/*
 * Andrew Hu & Ritvik Goyal
 * H. Strelkovska
 * ICS3U7
 * 2023-12-30
 * GameUtil: class with useful functions and constants to be accessed globally
 */

import javax.swing.ImageIcon;

public class GameUtil {
    // image icons for game tiles and ninja character
    public static ImageIcon awall = new ImageIcon("gamedata\\media\\awall.png");
    public static ImageIcon laddr = new ImageIcon("gamedata\\media\\laddr.png");
    public static ImageIcon empty = new ImageIcon("gamedata\\media\\empty.png");
    public static ImageIcon paint = new ImageIcon("gamedata\\media\\paint.png");
    public static ImageIcon astar = new ImageIcon("gamedata\\media\\astar.png");
    public static ImageIcon alava = new ImageIcon("gamedata\\media\\alava.png");
    public static ImageIcon blank = new ImageIcon("gamedata\\media\\blank.png");
    public static ImageIcon ur = new ImageIcon("gamedata\\media\\ur.png");
    public static ImageIcon ul = new ImageIcon("gamedata\\media\\ul.png");
    public static ImageIcon dr = new ImageIcon("gamedata\\media\\dr.png");
    public static ImageIcon dl = new ImageIcon("gamedata\\media\\dl.png");

    public static ImageIcon upppp = new ImageIcon("gamedata\\media\\upppp.png");
    public static ImageIcon downn = new ImageIcon("gamedata\\media\\downn.png");
    public static ImageIcon leftt = new ImageIcon("gamedata\\media\\leftt.png");
    public static ImageIcon right = new ImageIcon("gamedata\\media\\right.png");
    public static ImageIcon climb = new ImageIcon("gamedata\\media\\climb.png");

    public static ImageIcon zoomu = new ImageIcon("gamedata\\media\\zoomu.png");
    public static ImageIcon zoomd = new ImageIcon("gamedata\\media\\zoomd.png");
    public static ImageIcon zooml = new ImageIcon("gamedata\\media\\zooml.png");
    public static ImageIcon zoomr = new ImageIcon("gamedata\\media\\zoomr.png");

    public static ImageIcon zoomu1 = new ImageIcon("gamedata\\media\\zoomu1.png");
    public static ImageIcon zoomu2 = new ImageIcon("gamedata\\media\\zoomu2.png");
    public static ImageIcon zoomu3 = new ImageIcon("gamedata\\media\\zoomu3.png");
    public static ImageIcon zoomd1 = new ImageIcon("gamedata\\media\\zoomd1.png");
    public static ImageIcon zoomd2 = new ImageIcon("gamedata\\media\\zoomd2.png");
    public static ImageIcon zoomd3 = new ImageIcon("gamedata\\media\\zoomd3.png");
    public static ImageIcon zooml1 = new ImageIcon("gamedata\\media\\zooml1.png");
    public static ImageIcon zooml2 = new ImageIcon("gamedata\\media\\zooml2.png");
    public static ImageIcon zooml3 = new ImageIcon("gamedata\\media\\zooml3.png");
    public static ImageIcon zoomr1 = new ImageIcon("gamedata\\media\\zoomr1.png");
    public static ImageIcon zoomr2 = new ImageIcon("gamedata\\media\\zoomr2.png");
    public static ImageIcon zoomr3 = new ImageIcon("gamedata\\media\\zoomr3.png");
    
    public static Integer[] pairInt(int a, int b) {
        Integer[] arr = {a, b};
        return arr;
    }

    public static Integer[] pairInt(String a, String b) {
        return pairInt(Integer.parseInt(a), Integer.parseInt(b));
    }

    // turns "y x" into {y, x}
    public static Integer[] pairInt(String s) {
        String[] arrr = s.split(" ");
        return pairInt(arrr[0], arrr[1]);
    }

    // within the gameloop/search function, pause player movement for x milliseconds
    public static void timeSleep(int delay) {
        double base = System.currentTimeMillis();
        double goal = base + delay;
        while (System.currentTimeMillis() < goal) {
        }
    }
}
