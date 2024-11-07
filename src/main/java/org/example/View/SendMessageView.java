package org.example.View;

import org.example.Controller.UserController;
import org.example.Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendMessageView extends JDialog {
    private User user;
    public SendMessageView(JFrame parent, User user) {
        super(parent, "Send Message", true);
        this.user = user;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Padding for each component

        // Receiver User ID Label and Text Field
        JLabel receiverLabel = new JLabel("Receiver User ID:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(receiverLabel, gbc);

        JTextField receiverField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(receiverField, gbc);

        // Message Content Label and Text Area
        JLabel contentLabel = new JLabel("Message Content:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        add(contentLabel, gbc);

        JTextArea contentArea = new JTextArea(5, 15);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        JScrollPane contentScrollPane = new JScrollPane(contentArea);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(contentScrollPane, gbc);

        // Buttons Panel for Send and Cancel
        JPanel buttonPanel = new JPanel();
        JButton sendButton = new JButton("Send");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(sendButton);
        buttonPanel.add(cancelButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);

        // Action Listeners for Buttons
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String receiverId = receiverField.getText();
                String messageContent = contentArea.getText();

                if (receiverId.isEmpty() || messageContent.isEmpty()) {
                    JOptionPane.showMessageDialog(SendMessageView.this,
                            "Please fill in all fields.",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                } else if (receiverId.equals(String.valueOf(user.getId()))) {
                    JOptionPane.showMessageDialog(SendMessageView.this, "Cannot send message to yourself!!");
                } else {
                    UserController userController = new UserController();
                    String message = userController.sendMessage(user.getId(), Integer.parseInt(receiverId), messageContent);
                    if(message.equals("Message sent successfully.")){
                        JOptionPane.showMessageDialog(SendMessageView.this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(SendMessageView.this, message, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        cancelButton.addActionListener(e -> dispose());  // Close dialog on cancel

        // Set dialog properties
        setSize(400, 300);
        setLocationRelativeTo(parent);  // Center relative to parent
        setVisible(true);
    }

}

