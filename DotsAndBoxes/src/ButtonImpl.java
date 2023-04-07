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
    private JPanel buttonPanel;


    public ButtonImpl(){
        this.buttonPanel = new JPanel();
        this.buttons = new JButton[GRID_ROWS * GRID_COLS];
        this.scoreButton = new JButton();
        this.buttonSides = new Boolean[GRID_ROWS * GRID_COLS][4];
        for (Boolean[] arr : this.buttonSides) {
            Arrays.fill(arr, false);
        }

        this.ButtonSettings();
    }

    private void ButtonSettings() {
        this.buttonPanel.setLayout(new GridLayout(GRID_ROWS, GRID_COLS));

        for (int i = 0; i < this.buttons.length; i++) {
            this.buttons[i] = new JButton();
            this.buttons[i].setBorder(BorderFactory.createDashedBorder(null, 4, 4));

            this.buttons[i].setFocusable(false);
            this.buttons[i].setFont(new Font("Calibre", Font.BOLD, 120));
            this.buttons[i].addActionListener(this);
            this.buttonPanel.add(buttons[i]);
        }

        this.buttonPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        this.buttonPanel.setBackground(Color.lightGray);
    }

    private void ScoreButtonSettings(){
        this.scoreButton.setFocusable(false);
        this.scoreButton.setForeground(Color.white);
        this.scoreButton.setText("NEXT TURN");
        this.scoreButton.setFont(new Font("Calibre", Font.BOLD, 30));
        this.scoreButton.addActionListener(this);
    }
}
