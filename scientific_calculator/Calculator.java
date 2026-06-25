import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {

    JTextField textField;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[8];

    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton;

    JPanel panel;

    Font myFont = new Font("Arial", Font.BOLD, 25);

    double num1 = 0, num2 = 0, result = 0;
    char operator;

    Calculator() {

        setTitle("Calculator");
        setSize(420, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        textField = new JTextField();
        textField.setBounds(30, 25, 340, 50);
        textField.setFont(myFont);
        textField.setEditable(false);

        add(textField);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("DEL");
        clrButton = new JButton("CLR");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;

        for (int i = 0; i < 8; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
        }

        delButton.setBounds(30, 90, 160, 50);
        clrButton.setBounds(210, 90, 160, 50);

        add(delButton);
        add(clrButton);

        panel = new JPanel();
        panel.setBounds(30, 160, 340, 320);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);

        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);

        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);

        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        add(panel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }

        if (e.getSource() == decButton) {
            textField.setText(textField.getText().concat("."));
        }

        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText("");
        }

        if (e.getSource() == subButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
        }

        if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '*';
            textField.setText("");
        }

        if (e.getSource() == divButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText("");
        }

        if (e.getSource() == equButton) {
            num2 = Double.parseDouble(textField.getText());

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0)
                        result = num1 / num2;
                    else {
                        JOptionPane.showMessageDialog(this,
                                "Cannot divide by zero!");
                        return;
                    }
                    break;
            }

            textField.setText(String.valueOf(result));
            num1 = result;
        }

        if (e.getSource() == clrButton) {
            textField.setText("");
        }

        if (e.getSource() == delButton) {
            String str = textField.getText();
            if (str.length() > 0) {
                textField.setText(str.substring(0, str.length() - 1));
            }
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}