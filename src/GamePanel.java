package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {

    private static final int GAME_WIDTH = 1000;
    private static final int GAME_HEIGHT = (int) (GAME_WIDTH * (0.555));
    private static final Dimension DIMENSION_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    private static final int DIAMETER = 20;
    private static final int PADDLE_WIDTH = 25;
    private static final int PADDLE_HEIGHT = 100;
    private static final int BALL_WIDTH = 15;

    private Thread thread;
    private Image image;
    private Graphics graphics;
    private Random random;

    private Paddle paddle1;
    private Paddle paddle2;
    private Score score;
    private Ball ball;

    public GamePanel() {
        this.random = new Random();
        this.newPaddles();
        this.newBall();
        this.score = new Score(GAME_WIDTH, GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(this.DIMENSION_SIZE);
        this.thread = new Thread(this);
        this.thread.start();
    }

    public void newBall() {
        int rand = this.random.nextInt(GAME_HEIGHT - BALL_WIDTH);
        this.ball = new Ball(GAME_WIDTH / 2 - BALL_WIDTH, rand, BALL_WIDTH, BALL_WIDTH);
    }

    public void newPaddles() {
        this.paddle1 = new Paddle(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2),
                PADDLE_WIDTH, PADDLE_HEIGHT, 1);
        this.paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2),
                PADDLE_WIDTH, PADDLE_HEIGHT, 2);
    }

    public void paint(Graphics g) {
        this.image = this.createImage(this.getWidth(), this.getHeight());
        this.graphics = this.image.getGraphics();
        this.draw(this.graphics);
        g.drawImage(this.image, 0, 0, this);
    }

    public void draw(Graphics g) {
        this.paddle1.draw(g);
        this.paddle2.draw(g);
        this.ball.draw(g);
        this.score.draw(g);
    }

    public void move() {
        this.paddle1.move();
        this.paddle2.move();
        this.ball.move();
    }

    public void checkCollision() {
        // check if paddles move outside window edges
        if (this.paddle1.y <= 0) {
            this.paddle1.y = 0;
        }

        if (this.paddle1.y >= GAME_HEIGHT - PADDLE_HEIGHT) {
            this.paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;
        }

        if (this.paddle2.y <= 0) {
            this.paddle2.y = 0;
        }

        if (this.paddle2.y >= GAME_HEIGHT - PADDLE_HEIGHT) {
            this.paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;
        }

        // changes ball direction if there is a collision with paddles
        if (this.ball.intersects(this.paddle1) || this.ball.intersects(this.paddle2)) {
            this.ball.changeXDirection();

            int xVelocity = this.ball.getXVelocity();
            if (xVelocity > 0) {
                this.ball.setXVelocity(xVelocity + random.nextInt(2));
            } else {
                this.ball.setXVelocity(xVelocity - random.nextInt(2));
            }

            int yVelocity = this.ball.getYVelocity();
            if (yVelocity > 0){
                this.ball.setYVelocity(yVelocity + random.nextInt(2));
            }else{
                this.ball.setYVelocity(yVelocity - random.nextInt(2));
            }
        }

        if (this.ball.y <= 0) {
            this.ball.changeYDirection();
        }

        if (this.ball.y >= GAME_HEIGHT - BALL_WIDTH) {
            this.ball.changeYDirection();
        }

        //gives one point and creates new paddles
        if (this.ball.x <= 0){
            this.score.player2++;
            this.newPaddles();
            this.newBall();
        }

        if (this.ball.x >= GAME_WIDTH){
            this.score.player1++;
            this.newPaddles();
            this.newBall();
        }
    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;

        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                move();
                checkCollision();
                this.repaint();
                delta--;
            }
        }
    }

    public class AL extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }

        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}
