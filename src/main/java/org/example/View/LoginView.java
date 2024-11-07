package org.example.View;

import org.example.Controller.UserController;
import org.example.Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {


    public LoginView() {
        // Set up frame properties
        setTitle("Login Form");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set up layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding between components

        // Username Label
        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(usernameLabel, gbc);

        // Username Field
        JTextField usernameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(usernameField, gbc);

        // Password Label
        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(passwordLabel, gbc);

        // Password Field
        JPasswordField passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(passwordField, gbc);

        // Cancel Button
        JButton cancelButton = new JButton("Cancel");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(cancelButton, gbc);

        // Login Button
        JButton loginButton = new JButton("Login");
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(loginButton, gbc);

        // Signup Button
        JButton signupButton = new JButton("Signup");
        gbc.gridx = 2;
        gbc.gridy = 2;
        add(signupButton, gbc);

        // Button Actions
        cancelButton.addActionListener(e -> System.exit(0));

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if(username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginView.this, "Please enter a valid username/password");
                }
                else {
                    UserController userController = new UserController();
                    User user = userController.validateLogin(username, password);
                    if(user == null){
                        JOptionPane.showMessageDialog(LoginView.this, "The username or password is Invalid!");
                    }
                    else{
                        JOptionPane.showMessageDialog(LoginView.this, "User Successfully logged in.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        new DashboardView(user);
                    }
                }
            }
        });

        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new RegisterView();
            }
        });

        setVisible(true);
    }

}
