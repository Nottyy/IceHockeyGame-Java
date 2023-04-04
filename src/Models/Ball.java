package src.Models;

import java.awt.*;
import java.util.Random;

public class Ball extends Rectangle {
    private Random random;
    private int xVelocity = -3;
    private int yVelocity = 3;

    public Ball(int x, int y, int BALL_WIDTH, int BALL_HEIGHT) {
        super(x, y, BALL_WIDTH, BALL_HEIGHT);
    }

    public int getXVelocity() {
        return this.xVelocity;
    }

    public void setXVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public int getYVelocity(){
        return this.yVelocity;
    }

    public void setYVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    public void changeXDirection() {
        this.xVelocity *= -1;
    }

    public void changeYDirection() {
        this.yVelocity *= -1;
    }

    public void move() {
        this.x = this.x + this.xVelocity;
        this.y = this.y + this.yVelocity;
    }

    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(this.x, this.y, this.width, this.height);
    }
}
