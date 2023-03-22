package database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

abstract class Calculation {
    abstract double calculate(double x, double y) throws Exception;
}

class Add extends Calculation {
    public double calculate(double x, double y) {
        return x + y;
    }
}

class Subtract extends Calculation {
    public double calculate(double x, double y) {
        return x - y;
    }
}

class Multiply extends Calculation {
    public double calculate(double x, double y) {
        return x * y;
    }
}

class Divide extends Calculation {
    public double calculate(double x, double y) throws Exception {
        if (y == 0) {
            throw new Exception("Cannot divide by zero");
        }
        return x / y;
    }
}

class CalculatorFrame extends JFrame {
    private JLabel label1, label2, label3;
    private JTextField textField1, textField2, textField3;
    private JButton addButton, subtractButton, multiplyButton, divideButton;

    public CalculatorFrame() {
        setLayout(new FlowLayout());

        initComponents();
        addListeners();
    }

    private void initComponents() {
        label1 = new JLabel("First number:");
        add(label1);

        textField1 = new JTextField("0", 10);
        add(textField1);

        label2 = new JLabel("Second number:");
        add(label2);

        textField2 = new JTextField("0", 10);
        add(textField2);

        label3 = new JLabel("Result:");
        add(label3);

        textField3 = new JTextField("0", 10);
        textField3.setEditable(false);
        add(textField3);

        addButton = new JButton("Add");
        add(addButton);

        subtractButton = new JButton("Subtract");
        add(subtractButton);

        multiplyButton = new JButton("Multiply");
        add(multiplyButton);

        divideButton = new JButton("Divide");
        add(divideButton);
    }

    private void addListeners() {
        addButton.addActionListener(e -> performCalculation(new Add()));
        subtractButton.addActionListener(e -> performCalculation(new Subtract()));
        multiplyButton.addActionListener(e -> performCalculation(new Multiply()));
        divideButton.addActionListener(e -> performCalculation(new Divide()));
    }

    private void performCalculation(Calculation calculation) {
        try {
            double result = calculation.calculate(Double.parseDouble(textField1.getText()), Double.parseDouble(textField2.getText()));
            textField3.setText(Double.toString(result));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

public class Calculator {
    public static void main(String[] args) {
        CalculatorFrame frame = new CalculatorFrame();
        frame.setTitle("Calculator");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
}