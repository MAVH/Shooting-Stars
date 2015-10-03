package com.shooting_stars.project.command;


import com.shooting_stars.project.entity.Message;
import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.MessageLogic;
import sun.java2d.pipe.SpanShapeRenderer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class GetUnreadMessagesCommand extends SessionAwareCommand {
    private int chatId;
    private List<Message> newMessages;
    int currentUserId;
    int maxAmount = MessageLogic.MESSAGES_AMOUNT_ON_ONE_PAGE;
    String[] dateValues;
    String[] timeValues;
    public void validate() {
        currentUserId = getCurrentUserId();
        //проверка, является ли текущий пользователь участником чата
    }
    @Override
    public String execute() {
        String result = SUCCESS;
        try {
            newMessages = MessageLogic.getUnreadMessages(chatId, currentUserId);
            dateValues = new String[newMessages.size()];
            timeValues = new String[newMessages.size()];
            Locale locale = (Locale)sessionAttributes.get("currentLocale");
            DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
            DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT,locale);
            int i = 0;
            for (Message message : newMessages) {
                dateValues[i] = dateFormat.format(message.getDate());
                timeValues[i] = timeFormat.format(message.getTime());
                i++;
            }

        } catch (LogicException e) {
            LOG.error(e.getMessage(),e.getCause());
            exception = new CommandException(e.getCause());
            result = ERROR;
        }
        return result;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public List<Message> getNewMessages() {
        return newMessages;
    }

    public void setNewMessages(List<Message> newMessages) {
        this.newMessages = newMessages;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
    }

    public String[] getDateValues() {
        return dateValues;
    }

    public void setDateValues(String[] dateValues) {
        this.dateValues = dateValues;
    }

    public String[] getTimeValues() {
        return timeValues;
    }

    public void setTimeValues(String[] timeValues) {
        this.timeValues = timeValues;
    }
}
