import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class ButtonImpl {
    private JButton[] buttons;
    private JButton scoreButton;
    private Boolean[][] buttonSides;

    private int rows;
    private int cols;


    public ButtonImpl(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.buttons = new JButton[rows * cols];
        this.scoreButton = new JButton();
        this.buttonSides = new Boolean[rows * cols][4];
        for (Boolean[] arr : this.buttonSides) {
            Arrays.fill(arr, false);
        }

        ScoreButtonSettings();
    }

    public JButton[] getButtons() {
        return buttons;
    }

    public JButton getScoreButton() {
        return scoreButton;
    }

    public Boolean[][] getButtonSides() {
        return buttonSides;
    }

    private void ScoreButtonSettings() {
        this.scoreButton.setFocusable(false);
        this.scoreButton.setForeground(Color.white);
        this.scoreButton.setText("NEXT TURN");
        this.scoreButton.setFont(new Font("Calibre", Font.BOLD, 30));
    }
}
