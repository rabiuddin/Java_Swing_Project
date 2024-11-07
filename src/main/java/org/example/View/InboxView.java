package org.example.View;

import org.example.Model.Message;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class InboxView extends JFrame {
    private JTable messageTable;
    private DefaultTableModel tableModel;

    public InboxView(List<Message> messages) {
        setTitle("Inbox View");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the table model with column names
        String[] columnNames = {"Message ID", "Sender ID", "Message"};
        tableModel = new DefaultTableModel(columnNames, 0);

        // Populate the table model with message data
        for (Message message : messages) {
            Object[] rowData = {
                    message.getId(),
                    message.getSenderId(),
                    message.getMessageContent()
            };
            tableModel.addRow(rowData);
        }

        // Create the table and set its model
        messageTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(messageTable);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }


}
