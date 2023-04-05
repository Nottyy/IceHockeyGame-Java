import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TicTacToe implements ActionListener {
    private Random rnd;
    private JFrame frame;
    private JPanel title_panel;
    private JPanel button_panel;
    private JLabel text_field;
    private JButton[] buttons;
    private boolean player1Turn;

    public TicTacToe() {
        this.rnd = new Random();
        this.frame = new JFrame();
        this.title_panel = new JPanel();
        this.button_panel = new JPanel();
        this.text_field = new JLabel();
        this.buttons = new JButton[9];

        FrameSettings();
        TextFieldSettings();
        TitlePanelSettings();
        ButtonSettings();

        this.frame.add(this.title_panel, BorderLayout.NORTH);
        this.frame.add(this.button_panel);

        this.firstTurn();
    }

    private void ButtonSettings() {
        this.button_panel.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            this.buttons[i] = new JButton();

            this.buttons[i].setFont(new Font("Calibre", Font.BOLD, 120));
            this.buttons[i].setFocusable(false);

            this.buttons[i].addActionListener(this);

            this.button_panel.add(this.buttons[i]);
        }
    }

    private void TitlePanelSettings() {
        this.title_panel.setLayout(new BorderLayout());
        this.title_panel.setBounds(0, 0, 800, 100);
        this.title_panel.add(this.text_field);
    }

    private void TextFieldSettings() {
        this.text_field.setBackground(new Color(25, 25, 25));
        this.text_field.setForeground(new Color(25, 255, 0));
        this.text_field.setFont(new Font("Ink Free", Font.BOLD, 75));
        this.text_field.setHorizontalAlignment(JLabel.CENTER);
        this.text_field.setText("Tic-Tac-Toe");
        this.text_field.setOpaque(true);
    }

    private void FrameSettings() {
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(800, 800);
        this.frame.setBackground(new Color(50, 50, 50));
        this.frame.setLayout(new BorderLayout());
        this.frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (this.player1Turn) {
                    if (this.buttons[i].getText() == "") {
                        this.buttons[i].setForeground(new Color(255, 0, 0));
                        this.buttons[i].setText("X");
                        this.player1Turn = false;
                        this.text_field.setText("O turn");
                        this.checkWinningConditions();
                    }
                } else {
                    if (this.buttons[i].getText() == "") {

                        this.buttons[i].setForeground(new Color(0, 0, 255));
                        this.buttons[i].setText("O");
                        this.player1Turn = true;
                        this.text_field.setText("X turn");
                        this.checkWinningConditions();
                    }
                }
            }
        }
    }

    public void firstTurn() {
        this.Sleep(4000);

        if (this.rnd.nextInt(2) == 0) {
            this.player1Turn = true;
            this.text_field.setText("X turn");
        } else {
            this.player1Turn = false;
            this.text_field.setText("O turn");
        }
    }

    private void Sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (Exception ex) {

        }
    }

    public void checkWinningConditions() {
        CheckRows();
        CheckCols();
        CheckDiagonals();
    }

    private void CheckDiagonals() {
        if (this.buttons[0].getText() == "O"
                && this.buttons[4].getText() == "O"
                && this.buttons[8].getText() == "O"){
            this.oWins(0,4,8);
            return;
        }

        if (this.buttons[0].getText() == "X"
                && this.buttons[4].getText() == "X"
                && this.buttons[8].getText() == "X"){
            this.xWins(0,4,8);
            return;
        }

        if (this.buttons[2].getText() == "O"
                && this.buttons[4].getText() == "O"
                && this.buttons[6].getText() == "O"){
            this.oWins(2,4,6);
            return;
        }

        if (this.buttons[2].getText() == "X"
                && this.buttons[4].getText() == "X"
                && this.buttons[6].getText() == "X"){
            this.xWins(2,4,6);
            return;
        }
    }

    private void CheckCols() {
        for (int i = 0; i < 3; i++) {
            String str = this.buttons[i].getText();
            if (str == "") {
                continue;
            }
            boolean allMatching = true;
            for (int j = 3; j < 9; j+=3) {
                if (str != buttons[i + j].getText()) {
                    allMatching = false;
                    break;
                }
            }

            if (allMatching) {
                if (str == "O") {
                    this.oWins(i, i + 3, i + 6);
                } else {
                    this.xWins(i, i + 3, i + 6);
                }
            }
        }
    }

    private void CheckRows() {
        for (int i = 0; i < 9; i += 3) {
            String str = this.buttons[i].getText();
            if (str == "") {
                continue;
            }
            boolean allMatching = true;
            for (int j = 1; j < 3; j++) {
                if (str != buttons[i + j].getText()) {
                    allMatching = false;
                    break;
                }
            }

            if (allMatching) {
                if (str == "O") {
                    this.oWins(i, i + 1, i + 2);
                } else {
                    this.xWins(i, i + 1, i + 2);
                }
            }
        }
    }

    public void xWins(int a, int b, int c) {
        MakeButtonsGreen(a, b, c);
        DisableButtons();

        this.text_field.setText("X Wins");
    }

    public void oWins(int a, int b, int c) {
        MakeButtonsGreen(a, b, c);
        DisableButtons();

        this.text_field.setText("O Wins");
    }

    private void MakeButtonsGreen(int a, int b, int c) {
        this.buttons[a].setBackground(Color.GREEN);
        this.buttons[b].setBackground(Color.GREEN);
        this.buttons[c].setBackground(Color.GREEN);
    }

    private void DisableButtons() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
    }
}
