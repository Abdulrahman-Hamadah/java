
package database;



import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class SignupPage extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    // GUI components
    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton submitButton;

    // MySQL database connection
    private Connection conn;

    public SignupPage() {
        // Initialize the GUI
        setTitle("Signup Page");
        setPreferredSize(new Dimension(300, 200));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel to hold the form components
        JPanel formPanel = new JPanel(new GridLayout(4, 2));

        // Add the username field
        formPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        formPanel.add(usernameField);

        // Add the email field
        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formPanel.add(emailField);

        // Add the password field
        formPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);

        // Add the submit button
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        formPanel.add(submitButton);

        // Add the form panel to the center of the frame
        add(formPanel, BorderLayout.CENTER);

        // Connect to the MySQL database
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/mydatabase";
            String user = "root";
            String password = "admin123";
            conn = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "admin123");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        // Show the GUI
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

public void actionPerformed(ActionEvent e) {
    // Get the username, email, and password from the GUI
    String username = usernameField.getText();
    String email = emailField.getText();
    String password = new String(passwordField.getPassword());

    // Save the user data to the MySQL database
    try {
        PreparedStatement statement = conn.prepareStatement("INSERT INTO users (username, email, password) VALUES (?, ?, ?)");
        statement.setString(1, username);
        statement.setString(2, email);
        statement.setString(3, password);
        statement.executeUpdate();
        JOptionPane.showMessageDialog(this, "User data saved successfully!");
        
        // Create a new Login page
        LoginPage loginPage = new LoginPage();
        
        // Close the Signup page
        dispose();
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error saving user data: " + ex.getMessage());
    }
}


    public static void main(String[] args) {
        new SignupPage();
    }

}
