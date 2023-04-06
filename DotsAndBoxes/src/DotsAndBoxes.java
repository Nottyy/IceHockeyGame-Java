import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class DotsAndBoxes implements ActionListener {
    private Random rnd;
    private JFrame frame;
    private JPanel titlePanel;
    private JPanel buttonPanel;
    private JLabel[] textFields;
    private JButton[] buttons;
    private JButton scoreButton;
    private boolean player1Turn;

    public DotsAndBoxes() {
        this.rnd = new Random();
        this.frame = new JFrame();
        this.titlePanel = new JPanel();
        this.buttonPanel = new JPanel();
        this.textFields = new JLabel[2];
        this.buttons = new JButton[9];
        this.scoreButton = new JButton();


        this.JFrameSettings();
        this.TitlePanelSettings();
        this.ButtonSettings();

        this.frame.add(this.titlePanel, BorderLayout.NORTH);
        this.frame.add(this.buttonPanel);

        this.firstTurn();
    }

    private void ButtonSettings() {
        this.buttonPanel.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < this.buttons.length; i++) {
            this.buttons[i] = new JButton();
            this.buttons[i].setFocusable(false);
            this.buttons[i].setFont(new Font("Calibre", Font.BOLD, 120));
            this.buttons[i].addActionListener(this);
            this.buttons[i].setBorder(BorderFactory.createMatteBorder(0, 4, 0, 0, Color.RED));
            this.buttonPanel.add(buttons[i]);
        }

        this.buttonPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        this.buttonPanel.setBackground(Color.lightGray);
    }

    private void TitlePanelSettings() {
        this.titlePanel.setLayout(new GridLayout(1, 3));
        this.titlePanel.setBounds(0, 0, 80, 100);
        this.textFields[0] = new JLabel();
        this.titlePanel.add(this.TextFieldSettings(this.textFields[0],
                Font.BOLD, 30, "Score BLUE: 0", Color.BLUE));

        this.scoreButton.setFocusable(false);
        this.scoreButton.setBackground(Color.BLUE);
        this.scoreButton.setForeground(Color.white);
        this.scoreButton.setText("NEXT TURN");
        this.scoreButton.setFont(new Font("Calibre", Font.BOLD, 30));
        this.titlePanel.add(this.scoreButton);

        this.textFields[1] = new JLabel();
        this.titlePanel.add(this.TextFieldSettings(this.textFields[1],
                Font.BOLD, 30, "Score RED: 0", Color.RED));
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
        } else {
            this.player1Turn = false;
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
