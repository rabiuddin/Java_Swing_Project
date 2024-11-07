package org.example.Model;

public class Message {
    private int id;
    private int senderId;
    private int recipientId;
    private String messageContent;

    public Message(){}

    public Message(int id, int senderId, int recipientId, String messageContent) {
        this.id = id;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.messageContent = messageContent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(int recipientId) {
        this.recipientId = recipientId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    @Override
    public String toString() {
        return "Message{id=" + id + ", senderId=" + senderId + ", recipientId=" + recipientId +
                ", messageContent='" + messageContent + "'}";
    }
}

