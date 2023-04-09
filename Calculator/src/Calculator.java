import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Calculator implements ActionListener {
    private JSettings settings;
    private double num1 = 0;
    private double num2 = 0;
    private double result = 0;
    private char operator;
    private boolean num1Turn = true;


    public Calculator(JSettings settings) {
        this.settings = settings;
        AddActionListenerToButtons(this.settings.getFunctionButtons());
        AddActionListenerToButtons(this.settings.getNumberButtons());
    }

    private void AddActionListenerToButtons(JButton[] buttons) {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < this.numberButtons.length; i++) {
            if (e.getSource() == this.numberButtons[i]) {
                this.textField.setText(this.textField.getText().concat(String.valueOf(i)));
            }
        }

        if (e.getSource() == this.addB) {
            if (!EmptyTextField(this.textField)) {
                this.num1 = Double.parseDouble(this.textField.getText());
                this.textField.setText("");
                this.operator = '+';
            }
        }

        if (e.getSource() == this.subB) {
            if (!EmptyTextField(this.textField)) {
                this.num1 = Double.parseDouble(this.textField.getText());
                this.textField.setText("");
                this.operator = '-';
            }
        }

        if (e.getSource() == this.multB) {
            if (!EmptyTextField(this.textField)) {
                this.num1 = Double.parseDouble(this.textField.getText());
                this.textField.setText("");
                this.operator = '*';
            }
        }

        if (e.getSource() == this.divB) {
            if (!EmptyTextField(this.textField)) {
                this.num1 = Double.parseDouble(this.textField.getText());
                this.textField.setText("");
                this.operator = '/';
            }
        }

        if (e.getSource() == this.decB) {
            if (this.textField.getText().isEmpty() || this.textField.getText() == null) {
                this.textField.setText(this.textField.getText().concat(String.valueOf("0.")));
            } else {
                this.textField.setText(this.textField.getText().concat(String.valueOf('.')));
            }
        }

        if (e.getSource() == this.equB) {
            this.num2 = this.textField.getText().isEmpty() ? (this.num1 == 0 ? 0 : num1) : Double.parseDouble(this.textField.getText());
        }

        if (e.getSource() == this.clrB) {
            this.textField.setText("");
            this.num1 = 0;
            this.num2 = 0;
            this.result = 0;
        }

        if (e.getSource() == this.delB) {
            if (!EmptyTextField(this.textField)) {
                this.textField.setText(this.textField.getText().substring(0, this.textField.getText().length() - 1));
            }
        }

        if (e.getSource() == this.negB) {
            if (!EmptyTextField(this.textField)) {
                double oppNum = Double.parseDouble(this.textField.getText()) * -1;
                this.textField.setText(String.valueOf(oppNum));
            }
        }
    }

    private boolean EmptyTextField(JTextField textField) {
        return textField.getText().isEmpty() == true || textField.getText() == null;
    }

    private void Calculate(double num1, double num2, double result, char operator) {
        switch (this.operator) {
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
}
