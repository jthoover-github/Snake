import java.util.ArrayList;
import java.awt.*;

public class Apples extends GameObject{

    private SpriteObject appleSprite;
    
    @Override
    public void initialize() {
        
        ArrayList<Coords> albc = this.getGameCoords();
        albc.clear();
        appleSprite = new SpriteObject(Sprites.appleColorCoords());

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
        return "Apples [" + s + "]";
    }

    public void draw(Graphics g) {

        for (int i = 0; i < this.getGameCoords().size(); i++) {
            Coords gc = this.getGameCoords().get(i);
            appleSprite.draw(g, gc.getX(), gc.getY());
        }

    }

    public void add(Coords ac) {
        this.getGameCoords().add(ac);
        System.out.println(this.toString());
    }

    public void remove(Coords ac) {
        for (int i = 0; i < this.getGameCoords().size(); i++) {
            if ((ac.getX() == this.getGameCoords().get(i).getX()) && (ac.getY() == this.getGameCoords().get(i).getY())) {
                this.getGameCoords().remove(i);
            }
        }
    }

}
