
package database;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AgeCalculatorGUI extends JFrame implements ActionListener {

    private JLabel birthdateLabel, ageLabel;
    private JTextField birthdateField, ageField;
    private JButton calculateButton;
    
    public AgeCalculatorGUI() {
        super("Age Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        birthdateLabel = new JLabel("Enter your birthdate (mm/dd/yyyy):");
        ageLabel = new JLabel("Your age is:");
        birthdateField = new JTextField(10);
        ageField = new JTextField(10);
        ageField.setEditable(false);
        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(this);
        
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(birthdateLabel);
        panel.add(birthdateField);
        panel.add(ageLabel);
        panel.add(ageField);
        panel.add(new JLabel());
        panel.add(calculateButton);
        
        setContentPane(panel);
        pack();
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent event) {
        try {
            String birthdate = birthdateField.getText();
            int age = calculateAge(birthdate);
            ageField.setText(Integer.toString(age));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid birthdate entered.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private int calculateAge(String birthdate) throws Exception {
        String[] parts = birthdate.split("/");
        if (parts.length != 3) {
            throw new Exception("Invalid date format.");
        }
        int month = Integer.parseInt(parts[0]);
        int day = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        if (month < 1 || month > 12 || day < 1 || day > 31 || year < 1900) {
            throw new Exception("Invalid date.");
        }
        int age = java.time.LocalDate.now().getYear() - year;
        if (java.time.LocalDate.now().getMonthValue() < month || (java.time.LocalDate.now().getMonthValue() == month && java.time.LocalDate.now().getDayOfMonth() < day)) {
            age--;
        }
        return age;
    }
    
    public static void main(String[] args) {
        new AgeCalculatorGUI();
    }
}
