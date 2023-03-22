
package database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PercentageCalculatorGUI extends JFrame {
    
    private JLabel numberLabel, percentageLabel, resultLabel;
    private JTextField numberField, percentageField, resultField;
    private JButton calculateButton, clearButton;
    
    public PercentageCalculatorGUI() {
        super("Percentage Calculator");
        
        numberLabel = new JLabel("Number:");
        percentageLabel = new JLabel("Percentage:");
        resultLabel = new JLabel("Result:");
        numberField = new JTextField(10);
        percentageField = new JTextField(10);
        resultField = new JTextField(10);
        resultField.setEditable(false);
        calculateButton = new JButton("Calculate");
        clearButton = new JButton("Clear");
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        panel.add(numberLabel);
        panel.add(numberField);
        panel.add(percentageLabel);
        panel.add(percentageField);
        panel.add(resultLabel);
        panel.add(resultField);
        panel.add(calculateButton);
        panel.add(clearButton);
        
        add(panel);
        
        CalculatorListener listener = new CalculatorListener();
        calculateButton.addActionListener(listener);
        clearButton.addActionListener(listener);
        
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    private class CalculatorListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == calculateButton) {
                try {
                    double number = Double.parseDouble(numberField.getText());
                    double percentage = Double.parseDouble(percentageField.getText());
                    double result = number * percentage / 100;
                    resultField.setText(Double.toString(result));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(PercentageCalculatorGUI.this, "Please enter valid numbers.");
                }
            } else if (e.getSource() == clearButton) {
                numberField.setText("");
                percentageField.setText("");
                resultField.setText("");
            }
        }
    }
    
    public static void main(String[] args) {
        PercentageCalculatorGUI calculator = new PercentageCalculatorGUI();
    }
}
