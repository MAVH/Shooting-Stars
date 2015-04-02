package com.shooting_stars.project.entity;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by Пользователь on 02.04.2015.
 */
public class Message {
    private int messageId;
    private String message;
    private Date date;
    private Time time;
    private User sender;

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }
}
