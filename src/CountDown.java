import java.util.ArrayList;
import java.util.Arrays;
import java.awt.*;

public class CountDown extends GameObject{
    
    static ArrayList<Integer> evenGridData = new ArrayList<Integer>(Arrays.asList(
        0xAA, 0x55, 0xAA, 0x55, 0xAA, 0x55, 0xAA, 0x55 ));

    static ArrayList<Integer> oddGridData = new ArrayList<Integer>(Arrays.asList(
        0x55, 0xAA, 0x55, 0xAA, 0x55, 0xAA, 0x55, 0xAA ));

    private int fillLevel = GameObject.getGameHeight() - 2;
    private int delay = 1;
    
    @Override
    public void initialize() {
        
        ArrayList<Coords> alcc = this.getGameCoords();
        alcc.clear();

        for (int i = 1; i < GameObject.getGameHeight() - 2; i++ ) {
            alcc.add(new Coords(0,i));
            alcc.add(new Coords(GameObject.getGameWidth() - 1, i));
        }

        super.initialize();

    }

    @Override
    public String toString() {

        String s = "";
        ArrayList<Coords> alcc = this.getGameCoords();

        for (int i = 0; i < alcc.size(); i++) {
            s += alcc.get(i).toString() ;
            if (i != alcc.size() -1) s += ",";
        }
        return "Timer [" + s + "]";
    }

    public void draw(Graphics g) {

        ArrayList<ColorCoords> spriteCC;
        spriteCC = arrayListToCoords(Border.evenGridData, Color.BLUE);
        spriteCC.addAll(arrayListToCoords(Border.oddGridData, Color.RED));

        if (this.delay != 0) {
            this.delay--;
        } 
        else {
            this.fillLevel--;
            this.delay = 1;
        }
        if (this.fillLevel < 0) this.fillLevel = 0;

        SpriteObject block = new SpriteObject(spriteCC);

        for (int i = 0; i < this.getGameCoords().size(); i++) {
            Coords gc = this.getGameCoords().get(i);
            if (gc.getY() < this.fillLevel) block.draw(g, gc.getX(), gc.getY());
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

    public int getFillLevel() {
        return fillLevel;
    }

    public void setFillLevel(int fillLevel) {
        this.fillLevel = fillLevel;
    }

    public void refill() {
        this.fillLevel = GameObject.getGameHeight() - 2;
    }
    
}
