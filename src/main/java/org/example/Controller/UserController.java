package org.example.Controller;

import org.example.ConnectionConfiguration;
import org.example.Model.Message;
import org.example.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserController {
    private final Connection connection;

    public UserController() {
        this.connection = ConnectionConfiguration.getConnection();
    }

    public String register(String username, String password) {
        String checkQuery = "SELECT COUNT(*) FROM users WHERE username = ?";
        String insertQuery = "INSERT INTO users (username, password) VALUES (?, ?)";

        try {
            PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
            checkStatement.setString(1, username);

            ResultSet resultSet = checkStatement.executeQuery();
            resultSet.next();

            int count = resultSet.getInt(1);

            if (count > 0) {
                return "User already exists!";
            }

            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setString(1, username);
            insertStatement.setString(2, password);

            int rowsAffected = insertStatement.executeUpdate();

            if (rowsAffected > 0) {
                return "User Successfully registered!";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return " ";
    }

    public User validateLogin(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        ResultSet rs;
        try{
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if(!rs.next()){
                return null;
            }

            int Id = rs.getInt("id");
            String _username = rs.getString("username");
            String _password = rs.getString("password");

            User user;
            user = new User(Id, _username, _password);
            return user;
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public String sendMessage(int sender, int receiver, String message) {
        String checkUserQuery = "SELECT COUNT(*) FROM users WHERE id = ?";
        String insertMessageQuery = "INSERT INTO messages (sender, recipient, message_content) VALUES (?, ?, ?)";

        try {
            PreparedStatement checkUserStmt = connection.prepareStatement(checkUserQuery);
            checkUserStmt.setInt(1, receiver);
            ResultSet rs = checkUserStmt.executeQuery();
            rs.next();

            int count = rs.getInt(1);
            if (count < 1) {
                return "A reciever with the given ID does not exist!!";
            }

            PreparedStatement insertMessageStmt = connection.prepareStatement(insertMessageQuery);

            insertMessageStmt.setInt(1, sender);
            insertMessageStmt.setInt(2, receiver);
            insertMessageStmt.setString(3, message);

            int rowsAffected = insertMessageStmt.executeUpdate();
            if (rowsAffected > 0) {
                return "Message sent successfully.";
            } else {
                return "Failed to send the message.";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return checkUserQuery;
    }

    public List<Message> getSentMessages(int userId) {
        List<Message> messages = new ArrayList<>();
        String query = "SELECT * FROM messages WHERE sender = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int senderId = rs.getInt("sender");
                int receiverId = rs.getInt("recipient");
                String content = rs.getString("message_content");
                messages.add(new Message(id, senderId, receiverId, content));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messages;
    }

    public List<Message> getInboxMessages(int userId) {
        List<Message> messages = new ArrayList<>();
        String query = "SELECT * FROM messages WHERE recipient = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int senderId = rs.getInt("sender");
                int receiverId = rs.getInt("recipient");
                String content = rs.getString("message_content");
                messages.add(new Message(id, senderId, receiverId, content));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messages;
    }
}

