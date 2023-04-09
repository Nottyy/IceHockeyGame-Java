import javax.swing.*;
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
        for (int i = 0; i < this.settings.getNumberButtons().length; i++) {
            if (e.getSource() == this.settings.getNumberButtons()[i]) {
                this.settings.getTextField()
                        .setText(this.settings.getTextField()
                                .getText().concat(String.valueOf(i)));
            }
        }

        if (e.getSource() == this.settings.getAddB()) {
            if (!EmptyTextField(this.settings.getTextField()
            )) {
                this.num1 = Double.parseDouble(this.settings.getTextField()
                        .getText());
                this.settings.getTextField()
                        .setText("");
                this.operator = '+';
            }
        }

        if (e.getSource() == this.settings.getSubB()) {
            if (!EmptyTextField(this.settings.getTextField()
            )) {
                this.num1 = Double.parseDouble(this.settings.getTextField()
                        .getText());
                this.settings.getTextField()
                        .setText("");
                this.operator = '-';
            }
        }

        if (e.getSource() == this.settings.getMultB()) {
            if (!EmptyTextField(this.settings.getTextField()
            )) {
                this.num1 = Double.parseDouble(this.settings.getTextField()
                        .getText());
                this.settings.getTextField()
                        .setText("");
                this.operator = '*';
            }
        }

        if (e.getSource() == this.settings.getDivB()) {
            if (!EmptyTextField(this.settings.getTextField()
            )) {
                this.num1 = Double.parseDouble(this.settings.getTextField()
                        .getText());
                this.settings.getTextField()
                        .setText("");
                this.operator = '/';
            }
        }

        if (e.getSource() == this.settings.getDecB()) {
            if (this.settings.getTextField()
                    .getText().isEmpty() || this.settings.getTextField()
                    .getText() == null) {
                this.settings.getTextField()
                        .setText(this.settings.getTextField()
                                .getText().concat(String.valueOf("0.")));
            } else {
                this.settings.getTextField()
                        .setText(this.settings.getTextField()
                                .getText().concat(String.valueOf('.')));
            }
        }

        if (e.getSource() == this.settings.getEquB()) {
            this.num2 = this.settings.getTextField()
                    .getText().isEmpty() ? (this.num1 == 0 ? 0 : num1) : Double.parseDouble(this.settings.getTextField()
                    .getText());

            Calculate();
        }

        if (e.getSource() == this.settings.getClrB()) {
            this.settings.getTextField()
                    .setText("");
            this.num1 = 0;
            this.num2 = 0;
            this.result = 0;
        }

        if (e.getSource() == this.settings.getDelB()) {
            if (!EmptyTextField(this.settings.getTextField()
            )) {
                this.settings.getTextField()
                        .setText(this.settings.getTextField()
                                .getText().substring(0, this.settings.getTextField()
                                        .getText().length() - 1));
            }
        }

        if (e.getSource() == this.settings.getNegB()) {
            if (!EmptyTextField(this.settings.getTextField()
            )) {
                double oppNum = Double.parseDouble(this.settings.getTextField()
                        .getText()) * -1;
                this.settings.getTextField()
                        .setText(String.valueOf(oppNum));
            }
        }
    }

    private boolean EmptyTextField(JTextField textField) {
        return textField.getText().isEmpty() == true || textField.getText() == null;
    }

    private void Calculate() {
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
        this.settings.getTextField()
                .setText(String.valueOf(this.result));
        this.num1 = result;
    }
}
