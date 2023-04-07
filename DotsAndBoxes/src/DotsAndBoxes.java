import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

public class DotsAndBoxes implements ActionListener {
    private static final int GRID_ROWS = 6;
    private static final int GRID_COLS = 6;
    private Random rnd;
    private JFrame frame;
    private JPanel titlePanel;
    private JPanel buttonPanel;
    private JLabel[] textFields;
    private JButton[] buttons;
    private JButton scoreButton;
    private Boolean[][] buttonSides;
    private boolean player1Turn;
    private boolean madeAMove;
    private int player1Score;
    private int player2Score;
    private int idLastButton = -1;
    private int idLastButtonN = -1;
    private int lastSide = -1;
    private int lastSideN = -1;

    public DotsAndBoxes() {
        this.rnd = new Random();
        this.frame = new JFrame();
        this.titlePanel = new JPanel();
        this.buttonPanel = new JPanel();
        this.textFields = new JLabel[2];
        this.buttons = new JButton[GRID_ROWS * GRID_COLS];
        this.scoreButton = new JButton();
        this.player1Score = 0;
        this.player2Score = 0;
        this.madeAMove = false;
        this.buttonSides = new Boolean[GRID_ROWS * GRID_COLS][4];
        for (Boolean[] arr : this.buttonSides) {
            Arrays.fill(arr, false);
        }

        this.JFrameSettings();
        this.TitlePanelSettings();
        this.ButtonSettings();

        this.frame.add(this.titlePanel, BorderLayout.NORTH);
        this.frame.add(this.buttonPanel);

        this.firstTurn();
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

    private void TitlePanelSettings() {
        this.titlePanel.setLayout(new GridLayout(1, 3));
        this.titlePanel.setBounds(0, 0, 80, 100);
        this.textFields[0] = new JLabel();
        this.titlePanel.add(this.TextFieldSettings(this.textFields[0], Font.BOLD, 30, "Score BLUE: 0", Color.BLUE));

        this.scoreButton.setFocusable(false);
        this.scoreButton.setForeground(Color.white);
        this.scoreButton.setText("NEXT TURN");
        this.scoreButton.setFont(new Font("Calibre", Font.BOLD, 30));
        this.scoreButton.addActionListener(this);
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
                if (buttons[i] == e.getSource()) {
                    long count = Arrays.stream(buttonSides[i]).filter(x -> x).count();

                    if (count == 3) {
                        // update player score, color button, setBorder, disablebutton
                    } else if (count < 3) {
                        ChangeButtonSide(i);
                    }
                    break;
                }
            }
        }
    }

    private void ChangeButtonSide(int i) {
        int sideToSet = -1;

        if (i == this.idLastButton) {
            for (int j = 1; j < this.buttonSides[i].length; j++) {
                sideToSet = (j + this.lastSide) % 4;
                if (!buttonSides[i][sideToSet]) {
                    break;
                }
            }
        } else if (i != this.idLastButton) {
            for (int j = 0; j < 4; j++) {
                if (!this.buttonSides[i][j]) {
                    sideToSet = j;
                    break;
                }
            }
        }

        // clear lastClicked button and its neighbour
        if (this.lastSide != -1) {
            var arr = this.buttonSides[this.idLastButton].clone();
            this.SetBorders(this.idLastButton, arr);

            if (this.lastSideN != -1){
                arr = this.buttonSides[this.idLastButtonN].clone();
                this.SetBorders(this.idLastButtonN, arr);
            }
        }

        //set the border of the clicked button
        var arr = this.buttonSides[i].clone();
        arr[sideToSet] = true;
        this.SetBorders(i, arr);

        //set the border of the neighbour button
        SetNeighbourButton(i, sideToSet);

        this.idLastButton = i;
        this.lastSide = sideToSet;
        this.madeAMove = true;
    }

    private void SetNeighbourButton(int i, int sideToSet) {
        Boolean[] arr;

        switch (sideToSet) {
            case 0:
                this.idLastButtonN = i - GRID_COLS;
                if (this.idLastButtonN > 0) {
                    arr = this.buttonSides[this.idLastButtonN].clone();
                    arr[2] = true;
                    this.SetBorders(this.idLastButtonN, arr);
                    this.lastSideN = 2;
                } else {
                    this.idLastButtonN = -1;
                    this.lastSideN = -1;
                }
                break;
            case 1:
                this.idLastButtonN = i - 1;
                if (this.idLastButtonN > 0 && this.idLastButtonN % (GRID_COLS - 1) != 0) {
                    arr = this.buttonSides[this.idLastButtonN].clone();
                    arr[3] = true;
                    this.SetBorders(this.idLastButtonN, arr);
                    this.lastSideN = 3;
                } else {
                    this.idLastButtonN = -1;
                    this.lastSideN = -1;
                }
                break;
            case 2:
                this.idLastButtonN = i + GRID_COLS;
                if (this.idLastButtonN < GRID_COLS * GRID_ROWS) {
                    arr = this.buttonSides[this.idLastButtonN].clone();
                    arr[0] = true;
                    this.SetBorders(this.idLastButtonN, arr);
                    this.lastSideN = 0;
                } else {
                    this.idLastButtonN = -1;
                    this.lastSideN = -1;
                }
                break;
            case 3:
                this.idLastButtonN = i + 1;
                if (this.idLastButtonN % GRID_COLS != 0) {
                    arr = this.buttonSides[this.idLastButtonN].clone();
                    arr[1] = true;
                    this.SetBorders(this.idLastButtonN, arr);
                    this.lastSideN = 1;
                } else {
                    this.idLastButtonN = -1;
                    this.lastSideN = -1;
                }
                break;
        }
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
    //String.format("Score BLUE: %d", this.player1Score)
}
