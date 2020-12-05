import java.awt.*;

public class ColorCoords extends Coords {
    
    private Color color = Color.WHITE;

    public ColorCoords(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


}
