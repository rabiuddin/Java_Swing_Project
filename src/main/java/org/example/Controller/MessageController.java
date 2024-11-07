package org.example.Controller;

import org.example.ConnectionConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MessageController {
    private final Connection connection;

    public MessageController() {
        this.connection = ConnectionConfiguration.getConnection();
    }

    public boolean sendMessage(int senderId, int recipientId, String messageContent) {
        String query = "INSERT INTO Messages (sender, recipient, message_content) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, senderId);
            statement.setInt(2, recipientId);
            statement.setString(3, messageContent);
            statement.executeUpdate();
            System.out.println("Message sent successfully.");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

