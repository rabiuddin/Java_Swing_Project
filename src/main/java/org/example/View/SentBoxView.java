package org.example.View;

import org.example.Model.Message;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SentBoxView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public SentBoxView(List<Message> messages) {
        setLayout(new BorderLayout());
        setTitle("Messages Sent");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        // Define column names
        String[] columnNames = {"ID", "Recipient ID", "Message"};

        // Initialize table model
        tableModel = new DefaultTableModel(columnNames, 0);

        // Fill the table model with the message data
        for (Message message : messages) {
            Object[] row = {
                    message.getId(),
                    message.getRecipientId(),
                    message.getMessageContent()
            };
            tableModel.addRow(row);
        }

        // Initialize JTable with the table model
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public void updateMessages(List<Message> messages) {
        // Clear the existing rows
        tableModel.setRowCount(0);

        // Add new rows for the updated messages
        for (Message message : messages) {
            Object[] row = {
                    message.getId(),
                    message.getRecipientId(),
                    message.getMessageContent()
            };
            tableModel.addRow(row);
        }
    }

}
