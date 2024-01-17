/*
 * Andrew Hu & Ritvik Goyal
 * H. Strelkovska
 * ICS3U7
 * 2023-12-30
 * MyRect: wrapper class for individual game tile; draw() function for logic and comparing
 */

// imports
import java.awt.*;
public class MyRect {
    // 5-char string repr of current value; ie awall, alava, apaint, etc.
    private String value;
    int x, y, width, height;
    // constructor initializing value
    public MyRect(String value,int x, int y, int width, int height) {
        this.value = value;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public MyRect(String value) {
        this.value = value;
    }

    // draw image corresponding to String value onto display
    public void paintComponent(Graphics g) {
        if (value.equals("awall")) {
            g.drawImage(GameUtil.awall.getImage(), x, y, width, height, null);
            g.drawRect(x, y, width, height);
        }
        else if (value.equals("laddr")) {
            g.drawImage(GameUtil.laddr.getImage(), x, y, width, height, null);
            g.drawRect(x, y, width, height);
        }
        else if (value.equals("empty")) {
            g.drawImage(GameUtil.empty.getImage(), x, y, width, height, null);
            g.drawRect(x, y, width, height);
        }
        else if (value.equals("paint")) {
            g.drawImage(GameUtil.paint.getImage(), x, y, width, height, null);
            g.drawRect(x, y, width, height);
        }
        else if (value.equals("alava")) {
            g.drawImage(GameUtil.alava.getImage(), x, y, width, height, null);
            g.drawRect(x, y, width, height);
        }
        else if (value.equals("astar")) {
            g.drawImage(GameUtil.astar.getImage(), x, y, width, height, null);
            g.drawRect(x, y, width, height);
        }
        else if (value.equals("blank")) {
            g.drawImage(GameUtil.blank.getImage(), x, y, width, height, null);
            g.drawRect(x, y, width, height);
        }
        else if (value.equals("v--->")) {
            g.drawImage(GameUtil.dr.getImage(), x, y, width, height, null);
            g.drawRect(x, y, width, height);
        }
        else if (value.equals("^--->")) {
            g.drawImage(GameUtil.ur.getImage(), x, y, width, height, null);
            g.drawRect(x, y, width, height);
        }
        else if (value.equals("<---v")) {
            g.drawImage(GameUtil.dl.getImage(), x, y, width, height, null);
            g.drawRect(x, y, width, height);
        }
        else if (value.equals("<---^")) {
            g.drawImage(GameUtil.ul.getImage(), x, y, width, height, null);
            g.drawRect(x, y, width, height);
        }
    }
}