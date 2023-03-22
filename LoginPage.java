package database;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPage extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    // GUI components
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    // MySQL database connection
    private Connection conn;

    public LoginPage() {
        // Initialize the GUI
        setTitle("Login Page");
        setPreferredSize(new Dimension(300, 150));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel to hold the form components
        JPanel formPanel = new JPanel(new GridLayout(3, 2));

        // Add the username field
        formPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        formPanel.add(usernameField);

        // Add the password field
        formPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);

        // Add the login button
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        formPanel.add(loginButton);

        // Add the form panel to the center of the frame
        add(formPanel, BorderLayout.CENTER);

        // Connect to the MySQL database
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/mydatabase";
            String user = "root";
            String password = "admin123";
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        // Show the GUI
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = getUsername();
        String password = getPassword();

        if (authenticateUser(username, password)) {
            showSuccessMessage();
            openbuttons();
            dispose();
        } else {
            showErrorMessage();
        }
    }

    private String getUsername() {
        return usernameField.getText();
    }

    private String getPassword() {
        return new String(passwordField.getPassword());
    }

    private boolean authenticateUser(String username, String password) {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            return result.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            showErrorMessage("Error checking user credentials: " + ex.getMessage());
        }
        return false;
    }

    private void showSuccessMessage() {
        JOptionPane.showMessageDialog(this, "Login successful!");
    }
    

    private void showErrorMessage() {
        showErrorMessage("Invalid username or password!");
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

private void openbuttons() {
    new buttons().setVisible(true);
}


    public static void main(String[] args) {
        new LoginPage();
    }

}

class buttons extends JFrame {

    private static final long serialVersionUID = 1L;

    public buttons() {
        setTitle("Buttons Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(300, 150));

        // TODO: Add buttons to the GUI

        pack();
        setLocationRelativeTo(null);
    }

}