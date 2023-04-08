import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SettingsImpl {

    private JFrame frame;
    private JPanel titlePanel;
    private JLabel[] textFields;
    private JPanel buttonPanel;
    private int rows;
    private int cols;


    public SettingsImpl(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.frame = new JFrame();
        this.titlePanel = new JPanel();
        this.textFields = new JLabel[2];
        this.buttonPanel = new JPanel();

        this.JFrameSettings();
        this.TitlePanelSettings();
        this.ButtonSettings();

        this.frame.add(this.titlePanel, BorderLayout.NORTH);
        this.frame.add(this.buttonPanel);
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public JFrame getFrame() {
        return frame;
    }

    public JPanel getTitlePanel() {
        return titlePanel;
    }

    public JLabel[] getTextFields() {
        return textFields;
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    private void TitlePanelSettings() {
        this.titlePanel.setLayout(new GridLayout(1, 3));
        this.titlePanel.setBounds(0, 0, 80, 100);
        this.textFields[0] = new JLabel();
        this.titlePanel.add(this.TextFieldSettings(this.textFields[0], Font.BOLD, 30, "Score BLUE: 0", Color.BLUE));


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

    private void ButtonSettings() {
        this.buttonPanel.setLayout(new GridLayout(this.rows, this.cols));
        this.buttonPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        this.buttonPanel.setBackground(Color.lightGray);
    }
}
