import javax.swing.*;
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
    private boolean player1Turn;

    public DotsAndBoxes(){
        this.rnd = new Random();
        this.frame = new JFrame();
        this.titlePanel = new JPanel();
        this.buttonPanel = new JPanel();
        this.textFields = new JLabel[3];
        this.buttons = new JButton[9];


        this.JFrameSettings();
        this.TitlePanelSettings();
        this.ButtonSettings();

        this.frame.add(this.titlePanel, BorderLayout.NORTH);

        this.firstTurn();
    }

    private void ButtonSettings() {
        for (int i = 0; i < this.buttons.length; i++){

        }
    }

    private void TitlePanelSettings() {
        this.titlePanel.setLayout(new GridLayout(1,3));
        this.titlePanel.setBounds(0, 0, 80, 100);
        this.textFields[0] = new JLabel();
        this.titlePanel.add(this.TextFieldSettings(this.textFields[0],
                Font.BOLD, 30, "Score BLUE: 0", Color.BLUE));

        this.textFields[1] = new JLabel();
        this.titlePanel.add(this.TextFieldSettings(this.textFields[1],
                Font.BOLD, 20, "BLUE's Turn", Color.BLACK));

        this.textFields[2] = new JLabel();
        this.titlePanel.add(this.TextFieldSettings(this.textFields[2],
                Font.BOLD, 30, "Score RED: 0", Color.RED));
    }

    private JLabel TextFieldSettings(JLabel textField, int font, int size, String text, Color color) {
        textField.setBackground(new Color(150,75,0));
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
        this.frame.setSize(800,800);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
    }

    private void firstTurn() {
        if (this.rnd.nextInt(2) == 0){
            this.player1Turn = true;
        }
        else{
            this.player1Turn = false;
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
