package src;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public GamePanel gamePanel;

    public GameFrame() {
        this.gamePanel = new GamePanel();
        this.add(gamePanel);
        this.setTitle("Ice Hockey Game");
        this.setResizable(false);
        this.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
