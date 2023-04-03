package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {

    private static final int GAME_WIDTH = 1000;
    private static final int GAME_HEIGHT = GAME_WIDTH * (5 / 9);
    private static final Dimension DIMENSION_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    private static final int DIAMETER = 20;
    private static final int PADDLE_WIDTH = 25;
    private static final int PADDLE_HEIGHT = 100;

    private Thread thread;
    private Image image;
    private Graphics graphics;
    private Random random;

    private Paddle paddle1;
    private Paddle paddle2;
    private Score score;
    private Ball ball;

    public GamePanel() {
        this.newPaddles();
        this.newBall();
        this.score = new Score(GAME_WIDTH, GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
    }

    public void newBall() {

    }

    public void newPaddles() {

    }

    public void paint(Graphics g) {

    }

    public void draw(Graphics g) {

    }

    public void move() {

    }

    public void checkCollision() {

    }

    public void run() {

    }

    public class AL extends KeyAdapter {
        public void keyPressed(KeyEvent e) {

        }

        public void keyReleased(KeyEvent e) {

        }
    }
}
