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
        this.button_panel.setLayout(new GridLayout(3,3));
        this.button_panel.setBackground(new Color(150,0,150));

        for (int i = 0; i < 9; i++){
            this.buttons[i] = new JButton();

            this.buttons[i].setFont(new Font("Calibre", Font.BOLD, 120));
            this.buttons[i].setFocusable(false);
            this.buttons[i].setBackground(Color.ORANGE);

            this.buttons[i].addActionListener(this);

            this.button_panel.add(this.buttons[i]);
        }
    }

    private void TitlePanelSettings() {
        this.title_panel.setLayout(new BorderLayout());
        this.title_panel.setBounds(0,0,800,100);
        this.title_panel.add(this.text_field);
    }

    private void TextFieldSettings() {
        this.text_field.setBackground(new Color(25,25,25));
        this.text_field.setForeground(new Color(25,255,0));
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


    }

    public void firstTurn() {
        try{
            Thread.sleep(3000);
        }
        catch (Exception ex){

        }

        if (this.rnd.nextInt(2) == 0){
            this.player1Turn = true;
            this.text_field.setText("X turn");
        }
        else{
            this.player1Turn = false;
            this.text_field.setText("Y turn");
        }
    }

    public void checkWinningConditions() {

    }

    public void xWins(int a, int b, int c) {

    }

    public void oWins(int a, int b, int c) {

    }
}
