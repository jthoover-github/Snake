import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
//import java.util.Random;

public class PlayField extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;

    private static final int PIXEL_SIZE = 15;
    private static final int SCREEN_WIDTH = 40;
    private static final int SCREEN_HEIGHT = 27;
    private static final int GAME_UNITS = SCREEN_WIDTH * SCREEN_HEIGHT;

    private static final int MAX_FPS = 7;
    private static int DELAY = 1000 / MAX_FPS;
    private final int[] x = new int[GAME_UNITS];
    private final int[] y = new int[GAME_UNITS];

    private int snakeLength = 15;
    private Move move = Move.RIGHT;
    private boolean running = false;
    private Timer timer;
    //private final Random random;

    public PlayField() {
        //random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH * PIXEL_SIZE, SCREEN_HEIGHT * PIXEL_SIZE));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new SnakeControl());
        startGame();
    }

    private void startGame() {
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
            // draw Snake
            int b = PIXEL_SIZE;
            for (int i = 0; i < snakeLength; i++) {

                if (i == 0) {
                    g.setColor(Color.getColor("", 0x007f00));
                    g.fillRect(x[i]*b, y[i]*b, b, b);
                } else {
                    g.setColor(Color.GREEN);
                    g.fillRect(x[i]*b, y[i]*b, b, b);
                }
            }
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
        if (x[0] < 0 || x[0] >= SCREEN_WIDTH || y[0] < 0 || y[0] >= SCREEN_HEIGHT) {
            running = false;
        }
        if (!running) {
            timer.stop();
        }
    }

    private void gameOver(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 60));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        String gameOver = "GAME OVER";
        g.drawString(gameOver, (SCREEN_WIDTH * PIXEL_SIZE - metrics2.stringWidth(gameOver)) / 2, SCREEN_HEIGHT * PIXEL_SIZE / 2);
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