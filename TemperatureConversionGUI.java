
package database;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TemperatureConversionGUI extends JFrame {
    private JLabel titleLabel;
    private JLabel fahrenheitLabel;
    private JLabel celsiusLabel;
    private JTextField fahrenheitTextField;
    private JTextField celsiusTextField;
    private JButton convertButton;

    public TemperatureConversionGUI() {
        setTitle("Temperature Conversion");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null); // Center the frame on the screen
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        titleLabel = new JLabel("Convert Fahrenheit to Celsius");
        fahrenheitLabel = new JLabel("Fahrenheit:");
        celsiusLabel = new JLabel("Celsius:");
        fahrenheitTextField = new JTextField(10);
        celsiusTextField = new JTextField(10);
        celsiusTextField.setEditable(false);
        convertButton = new JButton("Convert");
        convertButton.addActionListener(new ConvertButtonListener());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weighty = 0.1;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.NORTH;
        add(titleLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.5;
        gbc.anchor = GridBagConstraints.EAST;
        add(fahrenheitLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.5;
        gbc.anchor = GridBagConstraints.WEST;
        add(fahrenheitTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.5;
        gbc.anchor = GridBagConstraints.EAST;
        add(celsiusLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.5;
        gbc.anchor = GridBagConstraints.WEST;
        add(celsiusTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(convertButton, gbc);

        setVisible(true);
    }

    private class ConvertButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double fahrenheit = Double.parseDouble(fahrenheitTextField.getText());
                double celsius = (fahrenheit - 32) * 5 / 9;
                celsiusTextField.setText(String.format("%.2f", celsius));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(TemperatureConversionGUI.this,
                        "Please enter a valid Fahrenheit temperature.",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                fahrenheitTextField.setText("");
                celsiusTextField.setText("");
            }
        }
    }

    public static void main(String[] args) {
        new TemperatureConversionGUI();
    }
}
