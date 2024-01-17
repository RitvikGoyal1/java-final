/*
 * Andrew Hu & Ritvik Goyal
 * H. Strelkovska
 * ICS3U7
 * 2023-12-30
 * NinjaSprite: wrapper class for ninja character and display
 */

// imports
import java.awt.*;

public class NinjaSprite {
    // position and animation; position is fixed at (1,1) for BlindDojo where the display array is 3x3
    private int playerY, playerX,width,height;
    private String currentAnimation = "upppp";

    // constructor setting position; animation is always "upppp" from the start
    public NinjaSprite(int playerY, int playerX) {
        this.playerY = playerY;
        this.playerX = playerX;
    }
    public int set(int width, int height) {
        this.width = width;
        this.height = height;
        return 1;
    }
    // setters for fields
    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }

    public void setCurrentAnimation(String currentAnimation) {
        this.currentAnimation = currentAnimation;
    }

    // draw image corresponding to fields onto display
    public void draw(Graphics g) {
        if (g!=null){
        if (currentAnimation.equals("upppp")) g.drawImage(GameUtil.upppp.getImage(), playerX * width, playerY * height, width, height, null);
        else if (currentAnimation.equals("downn")) g.drawImage(GameUtil.downn.getImage(), playerX * width, playerY * height, width, height, null);
        else if (currentAnimation.equals("leftt")) g.drawImage(GameUtil.leftt.getImage(), playerX * width, playerY * height, width, height, null);
        else if (currentAnimation.equals("right")) g.drawImage(GameUtil.right.getImage(), playerX * width, playerY * height, width, height, null);
        else if (currentAnimation.equals("climb")) g.drawImage(GameUtil.climb.getImage(), playerX * width, playerY * height, width, height, null);
        else if (currentAnimation.equals("zoomu")) g.drawImage(GameUtil.zoomu.getImage(), playerX * width, playerY * height, width, height, null);
        else if (currentAnimation.equals("zoomd")) g.drawImage(GameUtil.zoomd.getImage(), playerX * width, playerY * height, width, height, null);
        else if (currentAnimation.equals("zooml")) g.drawImage(GameUtil.zooml.getImage(), playerX * width, playerY * height, width, height, null);
        else if (currentAnimation.equals("zoomr")) g.drawImage(GameUtil.zoomr.getImage(), playerX * width, playerY * height, width, height, null);
        else if (currentAnimation.equals("zoomu1")) g.drawImage(GameUtil.zoomu1.getImage(), playerX * width, playerY * height, width, height, null);
        else if (currentAnimation.equals("zoomu2")) g.drawImage(GameUtil.zoomu2.getImage(), playerX * width, playerY * height, width, height, null);
        else if (currentAnimation.equals("zoomu3")) g.drawImage(GameUtil.zoomu3.getImage(), playerX * width, playerY * height, width, height, null);
        else if (currentAnimation.equals("zoomd1")) g.drawImage(GameUtil.zoomd1.getImage(), playerX * width, playerY * height, width, height, null);
        else if (currentAnimation.equals("zoomd2")) g.drawImage(GameUtil.zoomd2.getImage(), playerX * width, playerY * height, width, height, null);
        else if (currentAnimation.equals("zoomd3")) g.drawImage(GameUtil.zoomd3.getImage(), playerX * width, playerY * height, width, height, null);
        else if (currentAnimation.equals("zooml1")) g.drawImage(GameUtil.zooml1.getImage(), playerX * width, playerY * height, width, height, null);
        else if (currentAnimation.equals("zooml2")) g.drawImage(GameUtil.zooml2.getImage(), playerX * width, playerY * height, width, height, null);
        else if (currentAnimation.equals("zooml3")) g.drawImage(GameUtil.zooml3.getImage(), playerX * width, playerY * height, width, height, null);
        else if (currentAnimation.equals("zoomr1")) g.drawImage(GameUtil.zoomr1.getImage(), playerX * width, playerY * height, width, height, null);
        else if (currentAnimation.equals("zoomr2")) g.drawImage(GameUtil.zoomr2.getImage(), playerX * width, playerY * height, width, height, null);
        else if (currentAnimation.equals("zoomr3")) g.drawImage(GameUtil.zoomr3.getImage(), playerX * width, playerY * height, width, height, null);
    }}
}