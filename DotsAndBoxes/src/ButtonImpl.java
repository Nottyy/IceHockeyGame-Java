import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class ButtonImpl {
    private static final int GRID_ROWS = 5;
    private static final int GRID_COLS = 5;
    private JButton[] buttons;
    private JButton scoreButton;
    private Boolean[][] buttonSides;


    public ButtonImpl(){
        this.buttons = new JButton[GRID_ROWS * GRID_COLS];
        this.scoreButton = new JButton();
        this.buttonSides = new Boolean[GRID_ROWS * GRID_COLS][4];
        for (Boolean[] arr : this.buttonSides) {
            Arrays.fill(arr, false);
        }
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

    private void ScoreButtonSettings(){
        this.scoreButton.setFocusable(false);
        this.scoreButton.setForeground(Color.white);
        this.scoreButton.setText("NEXT TURN");
        this.scoreButton.setFont(new Font("Calibre", Font.BOLD, 30));
        this.scoreButton.addActionListener(this);
    }
}
