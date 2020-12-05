import java.util.ArrayList;
import java.awt.*;

public class GraphicObject {
    
    private ArrayList<Coords> cca = new ArrayList<Coords>();
    private static int blockSize = 24;
    private static int scale = 3;

    public GraphicObject(ArrayList<Coords> cca) {
        this.cca = cca;
    }

    public static void setScale(int s) {
        scale = s;
    }

    public static void setBlockSize(int b) {
        blockSize = b;
    }

    public static int getScale() {
        return scale;
    }

    public static int getBlockSize() {
        return blockSize;
    }

    public void draw(Graphics g, int x, int y, Color c) {

        Color d = c.darker();

        for (int i = 0; i < cca.size(); i++) {

            Coords xy = cca.get(i);

            if (scale == 1) {
                g.setColor(c);
                g.fillRect(x*getBlockSize()+xy.getX()*getScale(), y*getBlockSize()+xy.getY()*getScale(), 1, 1);
            } else {
                g.setColor(c);
                g.fillRect(x*getBlockSize()+xy.getX()*getScale(), y*getBlockSize()+xy.getY()*getScale(), getScale(), getScale()-1);
                g.setColor(d);
                g.fillRect(x*getBlockSize()+xy.getX()*getScale(), y*getBlockSize()+xy.getY()*getScale()+getScale()-1, getScale(), 1);
            }

        }

    }
}
