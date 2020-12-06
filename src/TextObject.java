import java.util.ArrayList;
import java.awt.*;

public class TextObject {
    
    private ArrayList<Coords> cca = new ArrayList<Coords>();
    private static final int blockSize = GameObject.getGameBlockSize();
    private static final int scale = GameObject.getGameScale();

    public TextObject(ArrayList<Coords> cca) {
        this.cca = cca;
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
                g.fillRect(x*getBlockSize()+xy.getX()*getScale(), y*getBlockSize()+(xy.getY()+1)*getScale() - 1, getScale(), 1);
            }

        }

    }

    public void draw(Graphics g, int x, int y, Color c, int size) {

        Color d = c.darker();

        for (int i = 0; i < cca.size(); i++) {

            Coords xy = cca.get(i);

            if (scale == 1) {
                g.setColor(c);
                g.fillRect(x*getBlockSize()+(xy.getX()*getScale()) * size, y*getBlockSize()+(xy.getY()*getScale()) * size, getScale()*size, getScale()*size);
            } else {
                g.setColor(c);
                g.fillRect(x*getBlockSize()+(xy.getX()*getScale()) * size, y*getBlockSize()+(xy.getY()*getScale()) * size, getScale()*size, (getScale()-1)*size);
                g.setColor(d);
                g.fillRect(x*getBlockSize()+(xy.getX()*getScale()) * size, y*getBlockSize()+((xy.getY()+1)*getScale() - 1) * size, getScale()*size, size);
            }

        }

    }
}
