import java.util.ArrayList;
import java.awt.*;

public class Snake extends GameObject {

    private int length = 15;
    private Move move = Move.UP;

    private SpriteObject snakeUp;
    private SpriteObject snakeDown;
    private SpriteObject snakeLeft;
    private SpriteObject snakeRight;
    private SpriteObject snakeHorz;
    private SpriteObject snakeVert;
    private SpriteObject snakeUL;
    private SpriteObject snakeUR;
    private SpriteObject snakeDL;
    private SpriteObject snakeDR;
    private SpriteObject snakeTailUp;
    private SpriteObject snakeTailDown;
    private SpriteObject snakeTailRight;
    private SpriteObject snakeTailLeft;

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    @Override
    public void initialize() {

        this.setLength(20); //initial length of snake

        snakeUp = new SpriteObject(Sprites.snakeUpColorCoords());
        snakeDown = new SpriteObject(Sprites.snakeDownColorCoords());
        snakeLeft = new SpriteObject(Sprites.snakeLeftColorCoords());
        snakeRight = new SpriteObject(Sprites.snakeRightColorCoords());
        snakeHorz = new SpriteObject(Sprites.snakeHorzColorCoords());
        snakeVert = new SpriteObject(Sprites.snakeVertColorCoords());
        snakeDL = new SpriteObject(Sprites.snakeDLColorCoords());
        snakeDR = new SpriteObject(Sprites.snakeDRColorCoords());
        snakeUL = new SpriteObject(Sprites.snakeULColorCoords());
        snakeUR = new SpriteObject(Sprites.snakeURColorCoords());
        snakeTailUp = new SpriteObject(Sprites.snakeTailUpColorCoords());
        snakeTailDown = new SpriteObject(Sprites.snakeTailDownColorCoords());
        snakeTailLeft = new SpriteObject(Sprites.snakeTailLeftColorCoords());
        snakeTailRight = new SpriteObject(Sprites.snakeTailRightColorCoords());

        ArrayList<Coords> alsc = this.getGameCoords();
        alsc.clear();

        // first two snake segments
        alsc.add(new Coords(GameObject.getGameWidth() / 2 - 1, GameObject.getGameHeight() - 2));
        alsc.add(new Coords(GameObject.getGameWidth() / 2 - 1, GameObject.getGameHeight() - 1));

        for (int i = 2; i < this.getLength(); i++) {
            alsc.add(new Coords(GameObject.getGameWidth() / 2 - 1, GameObject.getGameHeight()));
        }

        //System.out.println(this.toString());
        super.initialize();

    }

    @Override
    public String toString() {

        String s = "";
        ArrayList<Coords> alsc = this.getGameCoords();

        for (int i = 0; i < alsc.size(); i++) {
            s += alsc.get(i).toString();
            if (i != alsc.size() - 1)
                s += ",";
        }
        return "Snake [" + s + "]";
    }

    public void draw(Graphics g) {

        int size = this.getGameCoords().size();
        if (size < 3)
            return; // snake is too short to draw

        for (int i = 0; i < size; i++) {

            Coords gc = this.getGameCoords().get(i);

            int x = gc.getX();
            int y = gc.getY();

            if (i == 0) {

                if (move == Move.UP) {
                    snakeUp.draw(g, x, y);
                }
                if (move == Move.DOWN) {
                    snakeDown.draw(g, x, y);
                }
                if (move == Move.LEFT) {
                    snakeLeft.draw(g, x, y);
                }
                if (move == Move.RIGHT) {
                    snakeRight.draw(g, x, y);
                }
            } else {

                Coords lastGC = this.getGameCoords().get(i - 1);
                int lastX = lastGC.getX();
                int lastY = lastGC.getY();

                if (i < size - 1) {

                    Coords nextGC = this.getGameCoords().get(i + 1);
                    int nextX = nextGC.getX();
                    int nextY = nextGC.getY();

                    if (lastY == nextY)
                        snakeHorz.draw(g, x, y);
                    if (lastX == nextX)
                        snakeVert.draw(g, x, y);
                    if ((lastY < y) && (x < nextX))
                        snakeUR.draw(g, x, y);
                    if ((lastX > x) && (y < nextY))
                        snakeDR.draw(g, x, y);
                    if ((lastY > y) && (x > nextX))
                        snakeDL.draw(g, x, y);
                    if ((lastX < x) && (y > nextY))
                        snakeUL.draw(g, x, y);
                    if ((y < nextY) && (lastX < x))
                        snakeDL.draw(g, x, y);
                    if ((x > nextX) && (lastY < y))
                        snakeUL.draw(g, x, y);
                    if ((y > nextY) && (lastX > x))
                        snakeUR.draw(g, x, y);
                    if ((x < nextX) && (lastY > y))
                        snakeDR.draw(g, x, y);

                } else {
                    if (lastY > y)
                        snakeTailDown.draw(g, x, y);
                    if (lastX > x)
                        snakeTailRight.draw(g, x, y);
                    if (lastY < y)
                        snakeTailUp.draw(g, x, y);
                    if (lastX < x)
                        snakeTailLeft.draw(g, x, y);
                }
            }
        }

    }

    public void move() {

        int x = this.getGameCoords().get(0).getX();
        int y = this.getGameCoords().get(0).getY();

        if (this.getLength() <= this.getGameCoords().size()) {
            this.getGameCoords().remove(this.getGameCoords().size() - 1);
            //System.out.println(this.getGameCoords().size());
        }

        switch (this.move) {
            case UP:
                this.getGameCoords().add(0,new Coords(x,y-1));
                return;
            case DOWN:
                this.getGameCoords().add(0,new Coords(x,y+1));
                return;
            case LEFT:
                this.getGameCoords().add(0,new Coords(x-1,y));
                return;
            case RIGHT:
                this.getGameCoords().add(0,new Coords(x+1,y));
                return;
        }


/*        for (int i = this.getGameCoords().size() - 1; i > 0; i--) {
            this.getGameCoords().get(i).setX(this.getGameCoords().get(i - 1).getX());
            this.getGameCoords().get(i).setY(this.getGameCoords().get(i - 1).getY());
        }
        switch (this.move) {
            case UP:
                this.getGameCoords().get(0).setY(this.getGameCoords().get(0).getY() - 1);
                return;
            case DOWN:
                this.getGameCoords().get(0).setY(this.getGameCoords().get(0).getY() + 1);
                return;
            case LEFT:
                this.getGameCoords().get(0).setX(this.getGameCoords().get(0).getX() - 1);
                return;
            case RIGHT:
                this.getGameCoords().get(0).setX(this.getGameCoords().get(0).getX() + 1);
                return;
        } */

    }

    @Override
    public boolean collision(Coords xy) {
        
        // check for matching Coords and collision return true
        int size = this.getGameCoords().size();

        // skip checking the head
        for (int i = 1; i < size ; i++) {
            if ( (xy.getX() == this.getGameCoords().get(i).getX()) && ( xy.getY() == getGameCoords().get(i).getY()) ) {
                return true;
            }
        }
        return false;

    }

    

}
