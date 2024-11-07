package org.example.View;

import org.example.Controller.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterView extends JFrame {

    public RegisterView() {
        setTitle("Register Form");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding between components

        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(usernameLabel, gbc);

        JTextField usernameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(passwordField, gbc);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(confirmPasswordLabel, gbc);

        JPasswordField confirmPasswordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(confirmPasswordField, gbc);

        JButton registerButton = new JButton("Register");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(registerButton, gbc);

        JButton cancelButton = new JButton("Cancel");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(cancelButton, gbc);

        JButton loginButton = new JButton("Login");
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(loginButton, gbc);

        // Button Actions
        cancelButton.addActionListener(e -> System.exit(0));

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UserController userController = new UserController();
                // Registration logic here
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(RegisterView.this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(RegisterView.this, "Username and Password cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    String message = userController.register(username, password);
                    if(message.equals("User already exists!")){
                        JOptionPane.showMessageDialog(RegisterView.this, message, "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(RegisterView.this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        new LoginView();
                    }
                }
            }
        });

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginView();
            }
        });

        setVisible(true);
    }

}

