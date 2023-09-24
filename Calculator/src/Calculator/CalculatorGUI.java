package Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame {
    private final JTextField textField;
    private double firstOperand;
    private String operator;

    public CalculatorGUI() {
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textField = new JTextField();
        textField.setEditable(false);
        add(textField, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(4, 4));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };

        for (String button : buttons) {
            JButton btn = new JButton(button);
            btn.addActionListener(new ButtonClickListener());
            panel.add(btn);
        }

        add(panel);

        setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            String buttonText = source.getText();

            if (buttonText.equals("=")) {
                double secondOperand = Double.parseDouble(textField.getText());
                double result = performOperation(firstOperand, secondOperand, operator);
                textField.setText(String.valueOf(result));
                operator = null;
            } else if (buttonText.matches("[0-9.]")) {
                textField.setText(textField.getText() + buttonText);
            } else {
                firstOperand = Double.parseDouble(textField.getText());
                operator = buttonText;
                textField.setText("");
            }
        }
    }

    private double performOperation(double num1, double num2, String operator) {
        switch (operator) {
            case "+" -> {
                return num1 + num2;
            }
            case "-" -> {
                return num1 - num2;
            }
            case "*" -> {
                return num1 * num2;
            }
            case "/" -> {
                if (num2 == 0) {
                    JOptionPane.showMessageDialog(this, "Cannot divide by zero");
                    return 0;
                }
                return num1 / num2;
            }
            default -> {
                return 0;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculatorGUI());
    }
}