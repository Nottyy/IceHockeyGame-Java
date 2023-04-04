package src.Models;

import src.Interfaces.IScore;

import java.awt.*;

public class Score extends Rectangle implements IScore {
    private static int GAME_WIDTH;
    private static int GAME_HEIGHT;
    public int player1 = 0;
    public int player2 = 0;
    public Score(int width, int height){
        this.GAME_WIDTH = width;
        this.GAME_HEIGHT = height;
    }
    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.setFont(new Font("Calibri", Font.PLAIN, 20));
        g.drawString(String.valueOf(this.player1), this.GAME_WIDTH / 2 - 100,20);
        g.drawString(String.valueOf(this.player2), this.GAME_WIDTH / 2 + 100,20);
    }

    @Override
    public int getPlayer1() {
        return this.player1;
    }

    @Override
    public void setPlayer1(int num) {
        this.player1 = num;
    }

    @Override
    public int getPlayer2() {
        return this.player2;
    }

    @Override
    public void setPlayer2(int num) {
        this.player2 = num;
    }
}
