package com.shooting_stars.project.entity;

public class Chat {
    private int chatId;
    private User otherParticipant;
    private int amountOfUnreadMessages;

    public Chat(int chatId, User otherParticipant) {
        this.chatId = chatId;
        this.otherParticipant = otherParticipant;
    }

    public Chat(int chatId, User otherParticipant, int amountOfUnreadMessages) {
        this(chatId,otherParticipant);
        this.amountOfUnreadMessages = amountOfUnreadMessages;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public User getOtherParticipant() {
        return otherParticipant;
    }

    public void setOtherParticipant(User otherParticipant) {
        this.otherParticipant = otherParticipant;
    }

    public int getAmountOfUnreadMessages() {
        return amountOfUnreadMessages;
    }

    public void setAmountOfUnreadMessages(int amountOfUnreadMessages) {
        this.amountOfUnreadMessages = amountOfUnreadMessages;
    }
}
