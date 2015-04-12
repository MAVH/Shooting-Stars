package com.shooting_stars.project.entity;

import java.sql.Date;
import java.sql.Time;

public class Message {
    //private int messageId;
    private String message;
    private User sender;
    private Date date;
    private Time time;

    public Message(String message, User sender, Date date, Time time) {
        this.message = message;
        this.sender = sender;
        this.date = date;
        this.time = time;
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
