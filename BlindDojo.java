/*
 * Andrew Hu & Ritvik Goyal
 * H. Strelkovska
 * ICS3U7
 * 2023-12-30
 * BlindDojo: Class representation of a single BLIND level type. Interacts with GUI through its various functions.
 * Contains various useful maps for internal and external use
 * Graph theory/searching done internally, and outputs position, animation, need of direction change, and change in game display
 * Only displays the 3x3 area around the player
 */

// TODO: find a 300x300 image to mask over pretending its blind

public class BlindDojo extends Dojo {
    // constructor
    public BlindDojo(int levelSelected) {
        super(levelSelected);
    }

    // outputs 3x3 around player as String[][]
    public String[][] get9() {
        String[][] out = new String[3][3];
        out[0][0] = getArr()[getPlayerY()-1][getPlayerX()-1];
        out[0][1] = getArr()[getPlayerY()-1][getPlayerX()];
        out[0][2] = getArr()[getPlayerY()-1][getPlayerX()+1];
        out[1][0] = getArr()[getPlayerY()][getPlayerX()-1];
        out[1][1] = getArr()[getPlayerY()][getPlayerX()];
        out[1][2] = getArr()[getPlayerY()][getPlayerX()+1];
        out[2][0] = getArr()[getPlayerY()+1][getPlayerX()-1];
        out[2][1] = getArr()[getPlayerY()+1][getPlayerX()];
        out[2][2] = getArr()[getPlayerY()+1][getPlayerX()+1];
        return out;
    } // Assumption: player will never touch edge of map because of outside wall border
}
