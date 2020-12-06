import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
//import java.util.Random;

public class PlayField extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;

    private static final int BLOCK_SIZE = GameObject.getGameBlockSize();
    private static final int GAME_WIDTH = GameObject.getGameWidth();
    private static final int GAME_HEIGHT = GameObject.getGameHeight();
    private static final int GAME_UNITS = GameObject.getGameUnits();

    private static final int MAX_FPS = 5; // larger number for faster game
    private static int DELAY = 1000 / MAX_FPS;
    private final int[] x = new int[GAME_UNITS];
    private final int[] y = new int[GAME_UNITS];

    private int snakeLength = 10;
    private Move move = Move.UP;
    private boolean running = false;
    private Timer timer;

    private TextObject scoreLabel;
    private TextObject levelLabel;
    private TextObject snakeLabel;
    private TextObject appleLabel;
    private TextObject spaceLabel;
    private TextObject[] digits = new TextObject[10];

    private SpriteObject appleSprite;
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
    private Border border = new Border();
    private CountDown counter = new CountDown();
    //private final Random random;

    public PlayField() {
        //random = new Random();
        this.setPreferredSize(new Dimension(GAME_WIDTH * BLOCK_SIZE + 9 * BLOCK_SIZE, GAME_HEIGHT * BLOCK_SIZE));
        //System.out.println("Width:" + (GAME_WIDTH * BLOCK_SIZE + 9 * BLOCK_SIZE + 1) + "Heigth: " + (GAME_HEIGHT * BLOCK_SIZE + 1) );
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new SnakeControl());
        startGame();
    }

    private void startGame() {

        //ArrayList<Coords> score = new ArrayList<Coords>();
        //score = Font8x8.charCoordArray('B');
        scoreLabel = new TextObject(Font8x8.stringCoordArray("SCORE: "));
        levelLabel = new TextObject(Font8x8.stringCoordArray("LEVEL: "));
        snakeLabel = new TextObject(Font8x8.stringCoordArray("SNAKES: "));
        appleLabel = new TextObject(Font8x8.stringCoordArray("APPLES: "));
        spaceLabel = new TextObject(Font8x8.charCoordArray((char)(32)));
        for (int i = 0; i < 10; i++) {
            digits[i] = new TextObject(Font8x8.charCoordArray((char)(i+48)));
        }
        appleSprite = new SpriteObject(Sprites.appleColorCoords());
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

        running = true;
        timer = new Timer(DELAY, this);
        x[0] = GAME_WIDTH / 2 - 1;
        y[0] = GAME_HEIGHT - 2;
        x[1] = GAME_WIDTH / 2 - 1;
        y[1] = GAME_HEIGHT - 1;
        for (int i = 2; i < snakeLength; i++) {
            x[i] = GAME_WIDTH / 2 - 1;
            y[i] = GAME_HEIGHT ;
        }

        //System.out.println(border);
        border.initialize();
        counter.initialize();
        //System.out.println(border);

        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        if (running) {

            //border.draw(g, Color.RED);
            border.draw(g);
            counter.draw(g);

            // draw Snake
            for (int i = 0; i < snakeLength; i++) {

                if (i == 0) {
                    //g.setColor(Color.getColor("", 0x007f00));
                    //g.fillRect(x[i]*b, y[i]*b, b, b);
                    if (move == Move.UP) {
                        snakeUp.draw(g, x[i], y[i]);
                    }
                    if (move == Move.DOWN) {
                        snakeDown.draw(g, x[i], y[i]);
                    }
                    if (move == Move.LEFT) {
                        snakeLeft.draw(g, x[i], y[i]);
                    }
                    if (move == Move.RIGHT) {
                        snakeRight.draw(g, x[i], y[i]);
                    }
                } else {
                    if (i < snakeLength - 1) {
                        if (y[i-1] == y[i+1]) snakeHorz.draw(g, x[i], y[i]);
                        if (x[i-1] == x[i+1]) snakeVert.draw(g, x[i], y[i]);
                        if ( (y[i-1] < y[i]) && (x[i] < x[i+1]) ) snakeUR.draw(g, x[i], y[i]);
                        if ( (x[i-1] > x[i]) && (y[i] < y[i+1]) ) snakeDR.draw(g, x[i], y[i]);
                        if ( (y[i-1] > y[i]) && (x[i] > x[i+1]) ) snakeDL.draw(g, x[i], y[i]);
                        if ( (x[i-1] < x[i]) && (y[i] > y[i+1]) ) snakeUL.draw(g, x[i], y[i]);
                        if ( (y[i] < y[i+1]) && (x[i-1] < x[i]) ) snakeDL.draw(g, x[i], y[i]);
                        if ( (x[i] > x[i+1]) && (y[i-1] < y[i]) ) snakeUL.draw(g, x[i], y[i]);
                        if ( (y[i] > y[i+1]) && (x[i-1] > x[i]) ) snakeUR.draw(g, x[i], y[i]);
                        if ( (x[i] < x[i+1]) && (y[i-1] > y[i]) ) snakeDR.draw(g, x[i], y[i]);

                    }
                    else {
                        if (y[i-1] > y[i]) snakeTailDown.draw(g, x[i], y[i]);
                        if (x[i-1] > x[i]) snakeTailRight.draw(g, x[i], y[i]);
                        if (y[i-1] < y[i]) snakeTailUp.draw(g, x[i], y[i]);
                        if (x[i-1] < x[i]) snakeTailLeft.draw(g, x[i], y[i]);
                        //g.setColor(Color.GREEN);
                        //g.fillRect(x[i]*b, y[i]*b, b, b);
                    }
                }
            }
            appleSprite.draw(g, 10, 10);

            drawScoreBoard(g);

        } else {
            gameOver(g);
        }
    }
    private void drawScoreBoard(Graphics g) {
        
        scoreLabel.draw(g, 69, 8, Color.WHITE);
        digits[1].draw(g, 72, 10, Color.WHITE);
        spaceLabel.draw(g, 73, 10, Color.WHITE);
        digits[3].draw(g, 74, 10, Color.WHITE);
        digits[4].draw(g, 75, 10, Color.WHITE);
        levelLabel.draw(g, 69, 14, Color.ORANGE);
        digits[0].draw(g, 73, 16, Color.ORANGE.darker());
        digits[8].draw(g, 74, 16, Color.ORANGE.darker());
        snakeLabel.draw(g, 69, 20, Color.GREEN);
        digits[5].draw(g, 73, 22, Color.GREEN.darker());
        digits[6].draw(g, 74, 22, Color.GREEN.darker());
        appleLabel.draw(g, 69, 26, Color.RED);
        digits[7].draw(g, 73, 28, Color.RED.darker());
        digits[9].draw(g, 74, 28, Color.RED.darker());

    }

    private void move() {
        for (int i = snakeLength; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        switch (move) {
            case UP :
                y[0] = y[0] - 1; 
                return;
            case DOWN : 
                y[0] = y[0] + 1;
                return;
            case LEFT : 
                x[0] = x[0] - 1;
                return;
            case RIGHT : 
                x[0] = x[0] + 1;
                return;
        }
    }

    private void checkCollision() {
        // Hitting a border
        if (x[0] < 1 || x[0] >= GAME_WIDTH - 1 || y[0] < 1 || y[0] >= GAME_HEIGHT - 1) {
            running = false;
        }
        if (!running) {
            timer.stop();
        }
    }

    private void gameOver(Graphics g) {

        TextObject GO;
        ArrayList<Coords> gameover = new ArrayList<Coords>();
        gameover = Font8x8.stringCoordArray("GAME OVER!");
        GO = new TextObject(gameover);
        GO.draw(g, 18, 16, Color.RED, 4);

        //g.setColor(Color.RED);
        //g.setFont(new Font("Arial", Font.BOLD, 60));
        //FontMetrics metrics2 = getFontMetrics(g.getFont());
        //String gameOver = "GAME OVER";
        //g.drawString(gameOver, (SCREEN_WIDTH * BLOCK_SIZE - metrics2.stringWidth(gameOver)) / 2, SCREEN_HEIGHT * BLOCK_SIZE / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkCollision();
        }
        repaint();
    }

    private class SnakeControl extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (move != Move.RIGHT) {
                        move = Move.LEFT;
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (move != Move.LEFT) {
                        move = Move.RIGHT;
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (move != Move.DOWN) {
                        move = Move.UP;
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (move != Move.UP) {
                        move = Move.DOWN;
                    }
                    break;
            }
        }
    }
}