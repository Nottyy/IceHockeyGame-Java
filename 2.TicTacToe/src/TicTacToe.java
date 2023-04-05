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

        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(800, 800);
        this.frame.setBackground(new Color(50, 50, 50));
        this.frame.setLayout(new BorderLayout());
        this.frame.setVisible(true);

        this.text_field.setBackground(new Color(25,25,25));
        this.text_field.setForeground(new Color(25,255,0));
        this.text_field.setFont(new Font("Ink Free", Font.BOLD, 75));
        this.text_field.setHorizontalAlignment(JLabel.CENTER);
        this.text_field.setText("Tic-Tac-Toe");
        this.text_field.setOpaque(true);

        this.title_panel.setLayout(new BorderLayout());

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void firstTurn() {

    }

    public void checkWinningConditions() {

    }

    public void xWins(int a, int b, int c) {

    }

    public void oWins(int a, int b, int c) {

    }
}
