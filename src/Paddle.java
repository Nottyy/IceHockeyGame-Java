package src;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle {
    private final static int SPEED = 10;
    private int id;
    private int yVelocity;

    public Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id) {
        super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        this.id = id;
    }

    public void keyPressed(KeyEvent e) {
        switch (this.id){
            case 1:
                this.fireKeys(e, KeyEvent.VK_W, KeyEvent.VK_S, SPEED);
                break;
            case 2:
                this.fireKeys(e, KeyEvent.VK_UP, KeyEvent.VK_DOWN, SPEED);
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (this.id){
            case 1:
                this.fireKeys(e, KeyEvent.VK_W, KeyEvent.VK_S, 0);
                break;
            case 2:
                this.fireKeys(e, KeyEvent.VK_UP, KeyEvent.VK_DOWN, 0);
                break;
        }
    }

    private void fireKeys(KeyEvent e, int vk1, int vk2, int speed) {
        if (e.getKeyCode() == vk1){
            this.setYDirection(-speed);
            this.move();
        }
        if (e.getKeyCode() == vk2){
            this.setYDirection(speed);
            this.move();
        }
    }

    public void setYDirection(int yDirection) {
        this.yVelocity = yDirection;
    }

    public void move() {
        this.y = this.y + this.yVelocity;
    }

    public void draw(Graphics g) {
        if (this.id == 1){
            g.setColor(Color.red);
        }
        else{
            g.setColor(Color.blue);
        }

        g.fillRect(this.x, this.y, this.width, this.height);
    }
}
