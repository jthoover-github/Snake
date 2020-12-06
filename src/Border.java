import java.util.ArrayList;
import java.util.Arrays;
import java.awt.*;

public class Border extends GameObject{

    static ArrayList<Integer> evenGridData = new ArrayList<Integer>(Arrays.asList(
        0xAA, 0x55, 0xAA, 0x55, 0xAA, 0x55, 0xAA, 0x55 ));

    static ArrayList<Integer> oddGridData = new ArrayList<Integer>(Arrays.asList(
        0x55, 0xAA, 0x55, 0xAA, 0x55, 0xAA, 0x55, 0xAA ));
    
    @Override
    public void initialize() {
        
        ArrayList<Coords> albc = this.getGameCoords();
        albc.clear();

        for (int i = 0; i < GameObject.getGameWidth(); i++ ) {
            if (i != GameObject.getGameWidth()/2 - 1) {
                albc.add(new Coords(i,0));
                albc.add(new Coords(i, GameObject.getGameHeight() - 1));
            }
        }
        for (int i = 1; i < GameObject.getGameHeight() - 1; i++ ) {
            albc.add(new Coords(0,i));
            albc.add(new Coords(GameObject.getGameWidth() - 1,i));
        }

        super.initialize();

    }

    @Override
    public String toString() {

        String s = "";
        ArrayList<Coords> albc = this.getGameCoords();

        for (int i = 0; i < albc.size(); i++) {
            s += albc.get(i).toString() ;
            if (i != albc.size() -1) s += ",";
        }
        return "Border [" + s + "]";
    }

    public void draw(Graphics g) {

        ArrayList<ColorCoords> spriteCC;
        spriteCC = arrayListToCoords(Border.evenGridData, Color.GREEN);
        spriteCC.addAll(arrayListToCoords(Border.oddGridData, Color.RED));

        SpriteObject block = new SpriteObject(spriteCC);

        for (int i = 0; i < this.getGameCoords().size(); i++) {
            Coords gc = this.getGameCoords().get(i);
            block.draw(g, gc.getX(), gc.getY());
        }

    }

    static ArrayList<ColorCoords> arrayListToCoords(ArrayList<Integer> l, Color c) {

        ArrayList<ColorCoords> alcc = new ArrayList<ColorCoords>();

        for (int j = 0; j < l.size(); j++ ) {
            int p = l.get(j);
            for(int b = 0; b < 8; b++) {
                if((p & (128>>b)) != 0) {
                    alcc.add(new ColorCoords(b,j,c));
                }
            }
        }  

        return alcc;
    }
    
}
