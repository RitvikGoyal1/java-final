import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

/*
 * Andrew Hu & Ritvik Goyal
 * H. Strelkovska
 * ICS3U7
 * 2023-12-30
 * Dojo: Class representation of a single level. Interacts with GUI through its various functions.
 * Contains various useful maps for internal and external use
 * Graph theory/searching done internally, and outputs position, animation, need of direction change, and change in game display
 */
public class Dojo {
    // animationMap turns direction as "int int" into animation value. For moving character only
    private static HashMap<String, String> animationMap = new HashMap<>();
    static {
        animationMap.put("0 1","zoomr");
        animationMap.put("0 -1","zooml");
        animationMap.put("1 0","zoomd");
        animationMap.put("-1 0","zoomu");
    }
    // same as animationMap, but for standing character/not moving
    // note: standing positions is the cardinal direction that the head is pointing in. If character is standing upright direction is upppp
    private static HashMap<String, String> staticAnimationMap = new HashMap<>();
    static {
        staticAnimationMap.put("0 1","leftt");
        staticAnimationMap.put("0 -1","right");
        staticAnimationMap.put("1 0","upppp");
        staticAnimationMap.put("-1 0","downn");
    }

    /*
     * The below code does the following:
     * 1. reads input from turndata.txt, then adds the data into HashMap turnTile
     * 2. turnTile is String -> String, ie "v--->,0 1" maps to "0 0"
     * Implementation used with GameUtil.pairInt to turn "0 1" back into {0, 1}
     */
    private static Scanner dojosc;
    static {
        try {
            dojosc = new Scanner(new File("gamedata/turndata.txt"));
        } catch (Exception e) {}
    }

    private static String[] tempstr;
    private static HashMap<String, String> turnTile = new HashMap<>();
    static {
        while (dojosc.hasNextLine()) {
            tempstr = dojosc.nextLine().split(":");
            turnTile.put(tempstr[0], tempstr[1]);
        }
        dojosc.close();
    }

    // array of obstacles and unpainted tiles; the "map" of the level; setter and getter below
    private String[][] arr;

    public void setArr(String[][] arr) {
        this.arr = arr;
    }

    public String[][] getArr() {
        return arr;
    }

    // position of player as two separate x and y components, with respective getter and setter
    private int playerY, playerX;

    public int getPlayerX() {
        return playerX;
    }

    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }

    // name of current animation of character to be displayed on GUI with getter
    private String currentAnimation = "upppp"; // upppp downn leftt right zooml zoomr zoomu zoomd ...

    // getter of above field ^^^
    public String getCurrentAnimation() {
        return currentAnimation;
    }

    // boolean repr of if the player has already fallen in lava yet
    private boolean hasLost = false;
    public boolean checkLose() {
        return hasLost;
    }

    // basic constructor
    public Dojo(String[][] arr, int playerY, int playerX) {
        this.arr = arr;
        this.playerX = playerX;
        this.playerY = playerY;
    }

    // constructor given level chosen
    public Dojo(int levelSelected) {
        this(null, levelSelected, levelSelected);
        Scanner fsc = new Scanner(System.in);
        try {
            fsc = new Scanner(new File("gamedata\\levels\\data" + levelSelected + ".txt"));
        } catch (Exception e) {}
        String[][] boardData = new String[fsc.nextInt()][fsc.nextInt()];
        int playerY = fsc.nextInt(), playerX = fsc.nextInt();
        fsc.nextLine();
        int y = 0;
        while (fsc.hasNextLine()) {
            boardData[y] = fsc.nextLine().split(" ");
            y++;
        }
        setPlayerX(playerX);
        setPlayerY(playerY);
        setArr(boardData);
        fsc.close();
    }

    // temporary variables to relay information back to main() and tell the direction to change; getters and setters
    private boolean hasDirectionChange = false;
    public boolean needsDirectionChange() {
        return this.hasDirectionChange;
    }

    public void setHasDirectionChange(boolean hasDirectionChange) {
        this.hasDirectionChange = hasDirectionChange;
    }

    // temporary variable that given hasDirectionChange == true, change direction outside of Dojo class
    private Integer[] directionChange;
    public Integer[] getDirectionChange() {
        return directionChange;
    }

    // score getter and public incrementer
    private int score = 0;
    public int getScore() {
        return score;
    }

    // for out of class use
    public void decreaseScore() {score--;}

    // temporary variable used for ladder mechanics
    private boolean ladderFreshStart = false;

    // doMove() moves character one tile in specified direction; returns boolean to tell whether character should keep going
    // is a form of recursion and graph searching
    public boolean doMove(Integer[] direction, boolean freshstart) {
        // set animation
        currentAnimation = animationMap.get(direction[0] + " " + direction[1]);

        // temp variables
        Integer[] newDirection;
        String tempppppp;

        // check if can paint current tile or if pos is out of bounds
        try {
            if (arr[playerY][playerX].equals("empty")) {
                arr[playerY][playerX] = "paint";
            }
        } catch (Exception e) {
            return doMoveSub(false, direction);
        }

        // check if player has found a star; if so, remove star from map
        if (arr[playerY][playerX].equals("astar")) {
            arr[playerY][playerX] = "blank";
            score += 50;
        }

        // case: player lands on a half-wall that sends into different direction
        if (arr[playerY][playerX].charAt(2) == '-' && !(ladderFreshStart)) {
            newDirection = GameUtil.pairInt(turnTile.get(arr[playerY][playerX] + "," + direction[0] + " " + direction[1]));
            currentAnimation = animationMap.get(newDirection[0] + " " + newDirection[1]);

            hasDirectionChange = true;
            directionChange = newDirection;

            ladderFreshStart = true;
            return true;
        }
        
        // check if player is trying to go into a wall; prevents player from doing so or going out of bounds
        try {
            if (arr[playerY + direction[0]][playerX + direction[1]].equals("awall")) return doMoveSub(false, direction);
        } catch (Exception e) {
            return doMoveSub(false, direction);
        }

        // Check case: character is hitting a half-wall
        if (arr[playerY + direction[0]][playerX + direction[1]].charAt(2) == '-') {
            tempppppp = turnTile.get(arr[playerY + direction[0]][playerX + direction[1]] + "," + direction[0] + " " + direction[1]);
            
            // Check case: character is trying to go into a half-wall but on the non-diagonal side; acts as a wall 
            if (tempppppp.equals("0 0")) {
                return doMoveSub(false, direction);
            }
        }

        // Check case: player has fallen into lava; fulfill lose condition and break
        if (arr[playerY + direction[0]][playerX + direction[1]].equals("alava") && (!arr[playerY][playerX].equals("laddr"))) {
            hasLost = true;
            currentAnimation = "cookd";
            return false;
        }
        
        // Check case: player lands on a ladder, halting movement; freshstart prevents bug of staying on ladder forever
        if (arr[playerY][playerX].equals("laddr") && !freshstart) {
            currentAnimation = "climb";
            return false;
        }

        // move to the next square, continue with current animation
        playerY += direction[0];
        playerX += direction[1];

        ladderFreshStart = false;
        return true;
    }
    public int getStars(int lvl){
        int e= 3;
        for (String[] a : arr) {
            for (String i : a) {
                if (i.equals("astar")){
                    e--;
                }
            }
        }
        return e;
    }

    // sub method of doMove that sets animation as static before returning
    public boolean doMoveSub(boolean b, Integer[] direction) {
        currentAnimation = staticAnimationMap.get(direction[0] + " " + direction[1]);
        return b;
    }
    // checks if all tiles have been painted over (win condition)
    public boolean checkWin() {
        for (String[] a : arr) {
            for (String i : a) {
                if (i.equals("empty")){
                    return false;
                }
            }
        }
        return true;
    }

    // set initial moving animation given initial direction
    public void setPrelimAnimation(Integer[] direction) {
        currentAnimation = animationMap.get(direction[0] + " " + direction[1]);
    }   
}