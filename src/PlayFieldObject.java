import java.util.ArrayList;
import java.awt.*;

public class PlayFieldObject {
    
    private ArrayList<ColorCoords> pfca = new ArrayList<ColorCoords>();
    private static int blockSize = 24;
    private static int scale = 3;

    public PlayFieldObject(){
        
    }

    public PlayFieldObject(ArrayList<ColorCoords> pfca) {
        this.pfca = pfca;
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

    public void draw(Graphics g, int x, int y) {

        for (int i = 0; i < pfca.size(); i++) {

            ColorCoords xyc = pfca.get(i);
            xyc.getColor();

            if (scale == 1) {
                g.setColor(xyc.getColor());
                g.fillRect(x*getBlockSize()+xyc.getX()*getScale(), y*getBlockSize()+xyc.getY()*getScale(), 1, 1);
            } else {
                g.setColor(xyc.getColor());
                g.fillRect(x*getBlockSize()+xyc.getX()*getScale(), y*getBlockSize()+xyc.getY()*getScale(), getScale(), getScale()-1);
                g.setColor(xyc.getColor().darker());
                g.fillRect(x*getBlockSize()+xyc.getX()*getScale(), y*getBlockSize()+(xyc.getY()+1)*getScale() - 1, getScale(), 1);
            }

        }

    }

}
