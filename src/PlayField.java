import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class PlayField extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;

    private static final int BLOCK_SIZE = GameObject.getGameBlockSize();
    private static final int GAME_WIDTH = GameObject.getGameWidth();
    private static final int GAME_HEIGHT = GameObject.getGameHeight();

    private static final int MAX_FPS = 5; // larger number for faster game
    private static int DELAY = 1000 / MAX_FPS + 1;

    private boolean running = false;
    private Timer timer;
    private Random random = new Random();

    private TextObject scoreLabel;
    private TextObject levelLabel;
    //private TextObject snakeLabel;
    private TextObject appleLabel;
    //private TextObject spaceLabel;
    private TextObject[] digits = new TextObject[10];

    private Border border = new Border();
    private CountDown counter = new CountDown();
    private Snake snake = new Snake();
    private Apples apples = new Apples();
    private int score = 0;
    private int appleCount = 10;
    private int level = 1;

    public PlayField() {
        this.setPreferredSize(new Dimension(GAME_WIDTH * BLOCK_SIZE + 9 * BLOCK_SIZE, GAME_HEIGHT * BLOCK_SIZE));
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
        //snakeLabel = new TextObject(Font8x8.stringCoordArray("SNAKES: "));
        appleLabel = new TextObject(Font8x8.stringCoordArray("APPLES: "));
        //spaceLabel = new TextObject(Font8x8.charCoordArray((char)(32)));
        for (int i = 0; i < 10; i++) {
            digits[i] = new TextObject(Font8x8.charCoordArray((char)(i+48)));
        }

        snake.initialize();
        border.initialize();
        counter.initialize();
        apples.initialize();
        createApples(1);

        running = true;
        timer = new Timer(DELAY, this);
        timer.start();

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        draw(g);

    }

    private void draw(Graphics g) {
        if (running) {

            border.draw(g);
            apples.draw(g);
            counter.draw(g);
            snake.draw(g);

            drawScoreBoard(g);

        } else {

            gameOver(g);

        }
    }

    private void drawScoreBoard(Graphics g) {
        
        int d = 0;
        int a = 0;
        int l = 0;

        scoreLabel.draw(g, 69, 8, Color.WHITE);
        d = score / 1000;
        //System.out.println(d);
        digits[d].draw(g, 72, 10, Color.WHITE);
        d = (score % 1000) / 100;
        //System.out.println(d);
        digits[d].draw(g, 73, 10, Color.WHITE);
        d = (score % 100) / 10;
        //System.out.println(d);
        digits[d].draw(g, 74, 10, Color.WHITE);
        d = (score % 10) / 1;
        //System.out.println(d);
        digits[d].draw(g, 75, 10, Color.WHITE);
        levelLabel.draw(g, 69, 14, Color.ORANGE);
        l = (level) / 10;
        digits[l].draw(g, 73, 16, Color.ORANGE.darker());
        l = (level % 10 ) / 1;
        digits[l].draw(g, 74, 16, Color.ORANGE.darker());
        //snakeLabel.draw(g, 69, 20, Color.GREEN);
        //digits[5].draw(g, 73, 22, Color.GREEN.darker());
        //digits[6].draw(g, 74, 22, Color.GREEN.darker());
        appleLabel.draw(g, 69, 26, Color.RED);
        a = (appleCount) / 10;
        digits[a].draw(g, 73, 28, Color.RED.darker());
        a = (appleCount % 10) / 1;
        digits[a].draw(g, 74, 28, Color.RED.darker());

    }

    private void checkCollision() {

        if (border.collision(snake.getGameCoords().get(0))) running = false;
        if (snake.collision(snake.getGameCoords().get(0))) running = false;
        if (apples.collision(snake.getGameCoords().get(0))) {
            apples.remove(snake.getGameCoords().get(0));
            snake.setLength(snake.getLength() + 5);
            if (apples.getGameCoords().size() == 0) createApples(1);
            this.counter.refill();
            score = score + 10;
            appleCount = appleCount - 1;
            if (appleCount == 0) {
                level++;
                timer.stop();
                DELAY = 1000 / (MAX_FPS + level);
                timer = new Timer(DELAY, this);
                timer.start();
                appleCount = 10;
            }
        }
        if (this.counter.getFillLevel() == 0) {
            createApples(1);
            createApples(1);
            appleCount = appleCount + 2;
            this.counter.refill();
        }
        if (!running) {
            timer.stop();
            DELAY = 1000 / (MAX_FPS + level);
            timer = new Timer(DELAY, this);
        }
    }

    private void gameOver(Graphics g) {

        TextObject GO;
        ArrayList<Coords> gameover = new ArrayList<Coords>();
        gameover = Font8x8.stringCoordArray("GAME OVER!");
        GO = new TextObject(gameover);
        GO.draw(g, 18, 16, Color.RED, 4);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            snake.move();
            checkCollision();
        }
        repaint();
    }

    private class SnakeControl extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (snake.getMove() != Move.RIGHT) {
                        snake.setMove(Move.LEFT);
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (snake.getMove() != Move.LEFT) {
                        snake.setMove(Move.RIGHT);
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (snake.getMove() != Move.DOWN) {
                        snake.setMove(Move.UP);
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (snake.getMove() != Move.UP) {
                        snake.setMove(Move.DOWN);
                    }
                    break;
                case KeyEvent.VK_SPACE:
                    System.out.println("SPACE");
                    if (running == false) {
                        snake.initialize();
                        running = true;
                        timer.start();
                        snake.setMove(Move.UP);
                        score = 0;
                        level = 1;
                        appleCount = 10;
                    }
                    break;
            }
        }
    }

    private void createApples(int n) {

        boolean collision = true;
        int ax = 0;
        int ay = 0;

        while (collision) {

            ax = random.nextInt(GameObject.getGameWidth());
            ay = random.nextInt(GameObject.getGameHeight());
            
            if ( (!border.collision(new Coords(ax,ay))) && (!snake.collision(new Coords(ax,ay))) ){
                collision = false;
            }

        }

        apples.add(new Coords(ax,ay));

    }

}