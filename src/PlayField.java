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

    private static final int SCALE = 3;  //scale can be in range(1-3)
    private static final int BLOCK_SIZE = 8*SCALE;
    private static final int SCREEN_WIDTH = 65;
    private static final int SCREEN_HEIGHT = 45;
    private static final int GAME_UNITS = SCREEN_WIDTH * SCREEN_HEIGHT;

    private static final int MAX_FPS = 5;
    private static int DELAY = 1000 / MAX_FPS;
    private final int[] x = new int[GAME_UNITS];
    private final int[] y = new int[GAME_UNITS];
    private Coords[] border = new Coords[(SCREEN_WIDTH+SCREEN_HEIGHT-2)*2]; 

    private int snakeLength = 10;
    private Move move = Move.UP;
    private boolean running = false;
    private Timer timer;

    private GraphicObject scoreLabel;
    private GraphicObject levelLabel;
    private GraphicObject snakeLabel;
    private GraphicObject appleLabel;
    private GraphicObject[] digits = new GraphicObject[10];

    //private final Random random;

    public PlayField() {
        //random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH * BLOCK_SIZE + 15 * BLOCK_SIZE, SCREEN_HEIGHT * BLOCK_SIZE));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new SnakeControl());
        startGame();
    }

    private void startGame() {
        GraphicObject.setScale(SCALE);
        GraphicObject.setBlockSize(BLOCK_SIZE);
        //ArrayList<Coords> score = new ArrayList<Coords>();
        //score = Font8x8.charCoordArray('B');
        scoreLabel = new GraphicObject(Font8x8.stringCoordArray("SCORE: "));
        levelLabel = new GraphicObject(Font8x8.stringCoordArray("LEVEL: "));
        snakeLabel = new GraphicObject(Font8x8.stringCoordArray("SNAKES: "));
        appleLabel = new GraphicObject(Font8x8.stringCoordArray("APPLES: "));
        for (int i = 0; i < 10; i++) {
            digits[i] = new GraphicObject(Font8x8.charCoordArray((char)(i)));
        }
        running = true;
        timer = new Timer(DELAY, this);
        x[0] = SCREEN_WIDTH / 2 - 1;
        y[0] = SCREEN_HEIGHT - 2;
        for (int i = 1; i < snakeLength; i++) {
            x[i] = SCREEN_WIDTH / 2 - 1;
            y[i] = SCREEN_HEIGHT - 1;
        }
        int bi = 0;
        for (int i = 0; i < SCREEN_WIDTH; i++ ) {
            border[bi] = new Coords(i,0);
            bi++;
            border[bi] = new Coords(i, SCREEN_HEIGHT - 1);
            bi++;
        }
        for (int i = 1; i < SCREEN_HEIGHT - 1; i++ ) {
            border[bi] = new Coords(0,i);
            bi++;
            border[bi] = new Coords(SCREEN_WIDTH - 1,i);
            bi++;
        }

        System.out.println(bi);
        System.out.println(border[0].getX());

        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        if (running) {
            int b = BLOCK_SIZE;
            // draw Border
            for (int i = 0; i < border.length; i++) {
                if (border[i]!=null) {
                    g.setColor(Color.BLUE);
                    g.fillRect(border[i].getX()*b,border[i].getY()*b, b, b);
                }
            }
            // draw Snake
            for (int i = 0; i < snakeLength; i++) {

                if (i == 0) {
                    g.setColor(Color.getColor("", 0x007f00));
                    g.fillRect(x[i]*b, y[i]*b, b, b);
                } else {
                    g.setColor(Color.GREEN);
                    g.fillRect(x[i]*b, y[i]*b, b, b);
                }
            }

            scoreLabel.draw(g, 67, 8, Color.WHITE);
            levelLabel.draw(g, 67, 14, Color.ORANGE);
            snakeLabel.draw(g, 67, 20, Color.GREEN);
            appleLabel.draw(g, 67, 26, Color.RED);
    

        } else {
            gameOver(g);
        }
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
        if (x[0] < 1 || x[0] >= SCREEN_WIDTH - 1 || y[0] < 1 || y[0] >= SCREEN_HEIGHT - 1) {
            running = false;
        }
        if (!running) {
            timer.stop();
        }
    }

    private void gameOver(Graphics g) {

        GraphicObject GO;
        ArrayList<Coords> gameover = new ArrayList<Coords>();
        gameover = Font8x8.stringCoordArray("GAME OVER!");
        GO = new GraphicObject(gameover);
        GraphicObject.setScale(SCALE*3);
        GO.draw(g, 24, 20, Color.RED);

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