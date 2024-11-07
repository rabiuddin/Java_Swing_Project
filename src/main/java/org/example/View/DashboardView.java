package org.example.View;

import org.example.Controller.UserController;
import org.example.Model.Message;
import org.example.Model.User;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DashboardView extends JFrame {
    private User user;
    public DashboardView(User user) {
        this.user = user;
        setTitle("Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLayout(new GridBagLayout());

        // Create GridBagConstraints for layout configuration
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Padding around components

        // Add label at the top with the user's name
        JLabel userLabel = new JLabel("Welcome, " + user.getUsername() + "!");
        userLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;  // Span across two columns
        gbc.anchor = GridBagConstraints.CENTER;
        add(userLabel, gbc);

        // Button dimensions
        Dimension buttonSize = new Dimension(200, 100);

        // Create and add buttons
        JButton sendMessageButton = new JButton("Send Message");
        sendMessageButton.setPreferredSize(buttonSize);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(sendMessageButton, gbc);

        JButton sentMessagesButton = new JButton("Sent Messages");
        sentMessagesButton.setPreferredSize(buttonSize);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(sentMessagesButton, gbc);

        JButton inboxButton = new JButton("Inbox");
        inboxButton.setPreferredSize(buttonSize);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(inboxButton, gbc);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(buttonSize);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(logoutButton, gbc);

        // Set frame to center of screen and make it visible
        setLocationRelativeTo(null);
        setVisible(true);

        logoutButton.addActionListener(e -> {
            dispose();
            new LoginView();
        });

        sendMessageButton.addActionListener(e -> {
            new SendMessageView(this, user);
        });

        sentMessagesButton.addActionListener(e -> {
            UserController userController = new UserController();
            List<Message> messages = userController.getSentMessages(user.getId());
            new SentBoxView(messages);
        });

        inboxButton.addActionListener(e -> {
            UserController userController = new UserController();
            List<Message> messages = userController.getInboxMessages(user.getId());
            new InboxView(messages);
        });
    }

}

