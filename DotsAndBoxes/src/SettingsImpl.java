import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

public class SettingsImpl implements ActionListener {

    private Random rnd;
    private JFrame frame;
    private JPanel titlePanel;
    private JLabel[] textFields;
    private boolean player1Turn;
    private boolean madeAMove;

    private int idLastButton = -1;
    private int idLastButtonN = -1;
    private int lastSide = -1;
    private int lastSideN = -1;

    public SettingsImpl() {
        this.rnd = new Random();
        this.frame = new JFrame();
        this.titlePanel = new JPanel();
        this.textFields = new JLabel[2];
        this.player1Score = 0;
        this.player2Score = 0;
        this.madeAMove = false;


        this.JFrameSettings();
        this.TitlePanelSettings();

        this.frame.add(this.titlePanel, BorderLayout.NORTH);
        this.frame.add(this.buttonPanel);

        this.firstTurn();
    }

    private void TitlePanelSettings() {
        this.titlePanel.setLayout(new GridLayout(1, 3));
        this.titlePanel.setBounds(0, 0, 80, 100);
        this.textFields[0] = new JLabel();
        this.titlePanel.add(this.TextFieldSettings(this.textFields[0], Font.BOLD, 30, "Score BLUE: 0", Color.BLUE));

        this.titlePanel.add(this.scoreButton);

        this.textFields[1] = new JLabel();
        this.titlePanel.add(this.TextFieldSettings(this.textFields[1], Font.BOLD, 30, "Score RED: 0", Color.RED));
    }

    private JLabel TextFieldSettings(JLabel textField, int font, int size, String text, Color color) {
        textField.setBackground(new Color(230, 230, 230));
        textField.setForeground(color);
        textField.setFont(new Font("Helvetica", font, size));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText(text);
        textField.setOpaque(true);

        return textField;
    }

    private void JFrameSettings() {
        this.frame.setLayout(new BorderLayout());
        this.frame.setBackground(Color.DARK_GRAY);
        this.frame.setSize(800, 800);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
    }

    private void firstTurn() {
        if (this.rnd.nextInt(2) == 0) {
            this.player1Turn = true;
            this.scoreButton.setBackground(Color.BLUE);
        } else {
            this.player1Turn = false;
            this.scoreButton.setBackground(Color.RED);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.scoreButton) {
            NextTurn();
        } else {
            for (int i = 0; i < this.buttons.length; i++) {
                if (this.buttons[i] == e.getSource()) {
                    long count = Arrays.stream(buttonSides[i]).filter(x -> x).count();

                    if (count == 3) {
                        WonPoint(i);

                    } else if (count < 3) {
                        ModifyButton(i);
                    }
                    break;
                }
            }
        }
    }

    private void WonPoint(int i) {
        //set background
        this.buttons[i].setBackground(this.player1Turn ? Color.BLUE : Color.RED);

        //update player score
        if (this.player1Turn) {
            this.player1Score++;
            this.textFields[0].setText(String.format("Score BLUE: %d", this.player1Score));
        } else {
            this.player2Score++;
            this.textFields[1].setText(String.format("Score RED: %d", this.player2Score));
        }

        //setBorder
        int j;
        for (j = 0; j < this.buttonSides[i].length; j++) {
            if (!buttonSides[i][j]) {
                break;
            }
        }
        ClearLastClickedButton();

        UpdateButtonSide(j, i);
        ModifyNeighbourButton(i, j);
        if (this.idLastButtonN != -1) {
            this.buttonSides[this.idLastButtonN][this.lastSideN] = true;
        }

        //disable button
        this.buttons[i].setEnabled(false);

        if (this.idLastButtonN != -1) {
            if (Arrays.stream(this.buttonSides[this.idLastButtonN]).filter(x -> x).count() >= 3) {
                WonPoint(this.idLastButtonN);
            }
        }
    }

    private void ModifyButton(int i) {
        int sideToModify = -1;

        if (i == this.idLastButton) {
            for (int j = 1; j < this.buttonSides[i].length; j++) {
                sideToModify = (j + this.lastSide) % 4;
                if (!buttonSides[i][sideToModify]) {
                    break;
                }
            }
        } else if (i != this.idLastButton) {
            for (int j = 0; j < 4; j++) {
                if (!this.buttonSides[i][j]) {
                    sideToModify = j;
                    break;
                }
            }
        }

        // clear lastClicked button
        ClearLastClickedButton();

        //set the border of the clicked button
        var arr = this.buttonSides[i].clone();
        arr[sideToModify] = true;
        this.SetBorders(i, arr);

        //set the border of the neighbour button
        ModifyNeighbourButton(i, sideToModify);

        this.idLastButton = i;
        this.lastSide = sideToModify;
        this.madeAMove = true;
    }

    private void ClearLastClickedButton() {
        if (this.lastSide != -1) {
            var arr = this.buttonSides[this.idLastButton].clone();
            this.SetBorders(this.idLastButton, arr);

            // clear neighbour of lastClicked button
            if (this.lastSideN != -1) {
                arr = this.buttonSides[this.idLastButtonN].clone();
                this.SetBorders(this.idLastButtonN, arr);
            }
        }
    }

    private void ModifyNeighbourButton(int i, int sideToModify) {

        switch (sideToModify) {
            case 0:
                this.idLastButtonN = i - GRID_COLS;
                if (this.idLastButtonN >= 0) {
                    UpdateButtonSide(2, this.idLastButtonN);
                    this.lastSideN = 2;
                } else {
                    this.idLastButtonN = -1;
                    this.lastSideN = -1;
                }
                break;
            case 1:
                this.idLastButtonN = i - 1;
                if (this.idLastButtonN >= 0 && this.idLastButtonN > (GRID_COLS * (i / GRID_COLS)) - 1) {
                    UpdateButtonSide(3, this.idLastButtonN);
                    this.lastSideN = 3;
                } else {
                    this.idLastButtonN = -1;
                    this.lastSideN = -1;
                }
                break;
            case 2:
                this.idLastButtonN = i + GRID_COLS;
                if (this.idLastButtonN < GRID_COLS * GRID_ROWS) {
                    UpdateButtonSide(0, this.idLastButtonN);
                    this.lastSideN = 0;
                } else {
                    this.idLastButtonN = -1;
                    this.lastSideN = -1;
                }
                break;
            case 3:
                this.idLastButtonN = i + 1;
                if (this.idLastButtonN % GRID_COLS != 0) {
                    UpdateButtonSide(1, this.idLastButtonN);
                    this.lastSideN = 1;
                } else {
                    this.idLastButtonN = -1;
                    this.lastSideN = -1;
                }
                break;
        }

        if (this.idLastButtonN != -1) {
            if (Arrays.stream(this.buttonSides[this.idLastButtonN]).filter(x -> x).count() >= 3) {
                WonPoint(this.idLastButtonN);
            }
        }
    }

    private void UpdateButtonSide(int side, int idButton) {
        Boolean[] arr = this.buttonSides[idButton].clone();
        arr[side] = true;
        this.SetBorders(idButton, arr);
    }

    private void SetBorders(int i, Boolean[] values) {
        this.buttons[i].setBorder(BorderFactory.createMatteBorder(
                values[0] == true ? 4 : 0,
                values[1] == true ? 4 : 0,
                values[2] == true ? 4 : 0,
                values[3] == true ? 4 : 0, Color.BLACK));
    }

    private void NextTurn() {
        if (this.madeAMove) {
            if (this.player1Turn) {
                this.scoreButton.setBackground(Color.RED);
                this.player1Turn = false;
            } else {
                this.scoreButton.setBackground(Color.BLUE);
                this.player1Turn = true;
            }

            this.buttonSides[this.idLastButton][this.lastSide] = true;
            if (this.idLastButtonN != -1) {
                this.buttonSides[this.idLastButtonN][this.lastSideN] = true;
            }
            this.madeAMove = false;
        }
    }

    // this.buttons[i].setBorder(BorderFactory.createMatteBorder(0, 4, 0, 0, Color.RED));
}
