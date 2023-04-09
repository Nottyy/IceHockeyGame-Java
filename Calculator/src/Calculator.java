import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Calculator implements ActionListener {
    private JFrame frame;
    private JTextField textField;
    private JButton[] numberButtons;
    private JButton[] functionButtons;
    private JButton addB, subB, divB, multB;
    private JButton decB, equB, clrB, delB;
    private JPanel panel;

    private Font myFont = new Font("Ink free", Font.BOLD, 30);

    private double num1 = 0;
    private double num2 = 0;
    private double result = 0;
    private char operator;
    private boolean num1Turn = true;


    public Calculator() {
        this.frame = new JFrame("Calculator");
        this.numberButtons = new JButton[10];
        this.functionButtons = new JButton[8];
        this.panel = new JPanel(new GridLayout(4, 4, 10, 10));

        FrameSettings();
        TextFieldSettings();
        FunctionButtonsSettings();
        PanelSettings();

        this.frame.add(this.panel);
        this.frame.add(this.textField);
        this.frame.add(this.delB);
        this.frame.add(this.clrB);

        this.frame.setVisible(true);
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
        this.delB = new JButton("Delete");
        this.clrB = new JButton("Clear");

        this.functionButtons[0] = this.addB;
        this.functionButtons[1] = this.subB;
        this.functionButtons[2] = this.multB;
        this.functionButtons[3] = this.divB;
        this.functionButtons[4] = this.decB;
        this.functionButtons[5] = this.equB;
        this.functionButtons[6] = this.delB;
        this.functionButtons[7] = this.clrB;

        for (int i = 0; i < this.functionButtons.length; i++) {
            this.functionButtons[i].addActionListener(this);
            this.functionButtons[i].setFont(this.myFont);
            this.functionButtons[i].setFocusable(false);
        }

        for (int i = 0; i < 10; i++) {
            this.numberButtons[i] = new JButton(String.valueOf(i));
            this.numberButtons[i].addActionListener(this);
            this.numberButtons[i].setFont(this.myFont);
        }

        this.delB.setBounds(50, 430, 145, 50);
        this.clrB.setBounds(205, 430, 145, 50);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       for (int i = 0; i < this.numberButtons.length;i++){
           if (e.getSource() == this.numberButtons[i]){
               this.textField.setText(this.textField.getText().concat(String.valueOf(i)));
           }
       }

       if (e.getSource() == this.addB){
           this.num1 = Double.parseDouble(this.textField.getText());
           this.textField.setText("");
           this.operator = '+';
       }

       if (e.getSource() == this.subB){
           this.num1 = Double.parseDouble(this.textField.getText());
           this.textField.setText("");
           this.operator = '-';
       }

        if (e.getSource() == this.multB){
            this.num1 = Double.parseDouble(this.textField.getText());
            this.textField.setText("");
            this.operator = '*';
        }

        if (e.getSource() == this.divB){
            this.num1 = Double.parseDouble(this.textField.getText());
            this.textField.setText("");
            this.operator = '/';
        }

        if (e.getSource() == this.decB){
            if (this.textField.getText().isEmpty() || this.textField.getText() == null){
                this.textField.setText(this.textField.getText().concat(String.valueOf("0.")));
            }
            else{
                this.textField.setText(this.textField.getText().concat(String.valueOf('.')));
            }
        }

        if (e.getSource() == this.equB){
            this.num2 = Double.parseDouble(this.textField.getText());

            switch (this.operator){
                case '+':
                    this.result = num1 + num2;
                    break;
                case '-':
                    this.result = num1 - num2;
                    break;
                case '*':
                    this.result = num1 * num2;
                    break;
                case '/':
                    this.result = num1 / num2;
                    break;
            }
            this.result = Double.parseDouble(new DecimalFormat("##.##").format(this.result));
            this.textField.setText(String.valueOf(this.result));
            this.num1 = result;
        }

        if (e.getSource() == this.clrB){
            this.textField.setText("");
            this.num1 = 0;
            this.num2 = 0;
            this.result = 0;
        }

        if (e.getSource() == this.delB){
            String txt = this.textField.getText();
            if(txt.isEmpty() == false || txt != null){
                this.textField.setText(txt.substring(0, txt.length() - 1));
            }
        }
    }


    private void Calculate(double num1, double num2, double result, char operator) {
    }
}
