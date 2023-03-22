
package database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BMICalculatorFrame extends JFrame {
    
    private JLabel weightLabel, heightLabel;
    private JTextField weightField, heightField;
    private JButton calculateButton;
    
    public BMICalculatorFrame() {
        
        super("BMI Calculator");
        
        weightLabel = new JLabel("Enter your weight in kg:");
        heightLabel = new JLabel("Enter your height in meters:");
        weightField = new JTextField(10);
        heightField = new JTextField(10);
        calculateButton = new JButton("Calculate BMI");
        
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(weightLabel);
        panel.add(weightField);
        panel.add(heightLabel);
        panel.add(heightField);
        panel.add(calculateButton);
        
        add(panel);
        
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                double weight = 0, height = 0, bmi = 0;
                
                try {
                    weight = Double.parseDouble(weightField.getText());
                    height = Double.parseDouble(heightField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid numbers for weight and height.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (height == 0) {
                    JOptionPane.showMessageDialog(null, "Height cannot be zero.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                bmi = weight / (height * height);
                
                JOptionPane.showMessageDialog(null, "Your BMI is " + String.format("%.2f", bmi), "BMI", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    public static void main(String[] args) {
        new BMICalculatorFrame();
    }
    
}
