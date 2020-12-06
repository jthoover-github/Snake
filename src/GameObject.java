import java.util.ArrayList;
import java.awt.*;

public class GameObject {
    
    private static final int SCALE = 2;  //scale can be in range(1-3)
    private static final int BLOCK_SIZE = 8*SCALE; //each game block is an 8x8 grid
    private static final int GAME_WIDTH = 68; // the width x height of the game grid
    private static final int GAME_HEIGHT = 41;
    private static final int GAME_UNITS = GAME_WIDTH * GAME_HEIGHT; // total blocks in game grid

    private ArrayList<Coords> gameCoords = null;

    public static int getGameScale() {
        return SCALE;
    }

    public static int getGameBlockSize() {
        return BLOCK_SIZE;
    }

    public static int getGameWidth() {
        return GAME_WIDTH;
    }

    public static int getGameHeight() {
        return GAME_HEIGHT;
    }

    public static int getGameUnits() {
        return GAME_UNITS;
    }

    public ArrayList<Coords> getGameCoords() {
        return gameCoords;
    }

    public void setGameCoords(ArrayList<Coords> gameCoords) {
        this.gameCoords = gameCoords;
    }

    public GameObject(ArrayList<Coords> gameCoords) {
        this.gameCoords = gameCoords;
    }

    public GameObject() {
        this.gameCoords = new ArrayList<Coords>();
    }

    public void initialize() {

    }

    public boolean collision(GameObject o2) {

        // is this object colliding with another game object (o2)

        ArrayList<Coords> o2Coords = o2.getGameCoords();

        for (int i = 0; i < o2Coords.size(); i++) {
            for (int j = 0; j < gameCoords.size(); j++) {
                if ( (o2Coords.get(i).getX() == gameCoords.get(j).getX()) && (o2Coords.get(i).getY() == gameCoords.get(j).getY()) ) {
                    return true;
                }
            }
        }
        return false;

    }

    public void draw(Graphics g, Color c) {

        int b = GameObject.getGameBlockSize();

        for (int i = 0; i < this.getGameCoords().size(); i++) {
            Coords gc = this.getGameCoords().get(i);
            g.setColor(c);
            g.fillRect(gc.getX()*b,gc.getY()*b, b, b);
        }

    }

}
