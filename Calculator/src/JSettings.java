import javax.swing.*;
import java.awt.*;

public class JSettings {

    private JFrame frame;
    private JTextField textField;
    private JButton[] numberButtons;
    private JButton[] functionButtons;
    private JButton addB, subB, divB, multB;
    private JButton decB, equB, clrB, delB, negB;
    private JPanel panel;
    private Font myFont = new Font("Ink free", Font.BOLD, 30);

    public JSettings() {
        this.frame = new JFrame("Calculator");
        this.numberButtons = new JButton[10];
        this.functionButtons = new JButton[9];
        this.panel = new JPanel(new GridLayout(4, 4, 10, 10));

        FrameSettings();
        TextFieldSettings();
        FunctionButtonsSettings();
        PanelSettings();

        this.frame.add(this.panel);
        this.frame.add(this.textField);
        this.frame.add(this.negB);
        this.frame.add(this.delB);
        this.frame.add(this.clrB);

        this.frame.setVisible(true);
    }

    public JTextField getTextField() {
        return textField;
    }

    public JButton[] getNumberButtons() {
        return numberButtons;
    }

    public JButton[] getFunctionButtons() {
        return functionButtons;
    }

    public JButton getAddB() {
        return addB;
    }

    public JButton getSubB() {
        return subB;
    }

    public JButton getDivB() {
        return divB;
    }

    public JButton getMultB() {
        return multB;
    }

    public JButton getDecB() {
        return decB;
    }

    public JButton getEquB() {
        return equB;
    }

    public JButton getClrB() {
        return clrB;
    }

    public JButton getDelB() {
        return delB;
    }

    public JButton getNegB() {
        return negB;
    }

    private void PanelSettings() {
        this.panel.setBounds(50, 100, 300, 300);
        this.panel.add(this.numberButtons[1]);
        this.panel.add(this.numberButtons[2]);
        this.panel.add(this.numberButtons[3]);
        this.panel.add(this.addB);

        this.panel.add(this.numberButtons[4]);
        this.panel.add(this.numberButtons[5]);
        this.panel.add(this.numberButtons[6]);
        this.panel.add(this.subB);

        this.panel.add(this.numberButtons[7]);
        this.panel.add(this.numberButtons[8]);
        this.panel.add(this.numberButtons[9]);
        this.panel.add(this.multB);

        this.panel.add(this.decB);
        this.panel.add(this.numberButtons[0]);
        this.panel.add(this.equB);
        this.panel.add(this.divB);
    }

    private void FrameSettings() {
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(420, 550);
        this.frame.setLayout(null);
    }

    private void TextFieldSettings() {
        this.textField = new JTextField();
        this.textField.setBounds(50, 25, 300, 50);
        this.textField.setFont(this.myFont);
        this.textField.setEditable(false);
    }

    private void FunctionButtonsSettings() {
        this.addB = new JButton("+");
        this.subB = new JButton("-");
        this.multB = new JButton("*");
        this.divB = new JButton("/");
        this.decB = new JButton(".");
        this.equB = new JButton("=");
        this.delB = new JButton("Del.");
        this.clrB = new JButton("C");
        this.negB = new JButton("(-)");

        this.functionButtons[0] = this.addB;
        this.functionButtons[1] = this.subB;
        this.functionButtons[2] = this.multB;
        this.functionButtons[3] = this.divB;
        this.functionButtons[4] = this.decB;
        this.functionButtons[5] = this.equB;
        this.functionButtons[6] = this.delB;
        this.functionButtons[7] = this.clrB;
        this.functionButtons[8] = this.negB;

        for (int i = 0; i < this.functionButtons.length; i++) {
            this.functionButtons[i].setFont(this.myFont);
            this.functionButtons[i].setFocusable(false);
        }

        for (int i = 0; i < 10; i++) {
            this.numberButtons[i] = new JButton(String.valueOf(i));
            this.numberButtons[i].setFont(this.myFont);
        }
        this.negB.setBounds(50, 430, 100, 50);
        this.delB.setBounds(150, 430, 100, 50);
        this.clrB.setBounds(250, 430, 100, 50);
    }
}
