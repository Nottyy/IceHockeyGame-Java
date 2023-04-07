import java.awt.*;

public class Player {
    private int score;
    private Color color;


    public Player(java.awt.Color color){
        this.color = color;
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public Color getColor() {
        return color;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
