package com.shooting_stars.project.logic;

import com.shooting_stars.project.dao.MessageDAO;
import com.shooting_stars.project.entity.Chat;
import com.shooting_stars.project.entity.Message;
import com.shooting_stars.project.exception.DAOException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.exception.PoolConnectionException;
import com.shooting_stars.project.pool.Pool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public class MessageLogic {
    public static void sendMessage(int userId1, int userId2, String message) {
         //if chat doesn't exist --> insert in table chat
    }

    public static Date getCurrentDate() {
        return Date.valueOf(LocalDate.now());
    }
    public static Time getCurrentTime() {
        return Time.valueOf(LocalTime.now());
    }

    public static void sendMessage(int chatId, String message, int senderId) throws LogicException {
        Connection connection = null;
        try {
            connection = Pool.getPool().getConnection();
            MessageDAO messageDAO = new MessageDAO(connection);
            messageDAO.insertMessage(chatId,message, senderId, getCurrentDate(), getCurrentTime());
        } catch(PoolConnectionException | DAOException e ) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(connection);
        }
    }
    public static List<Message> getMessages(int chatId,int userId) throws LogicException {
        Connection connection = null;
        try {
            connection = Pool.getPool().getConnection();
            MessageDAO messageDAO = new MessageDAO(connection);

            List<Message> messages = messageDAO.getMessagesByChatId(chatId);
            messageDAO.updateMessagesStatus(chatId, userId);
            return messages;
        } catch(PoolConnectionException | DAOException e ) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(connection);
        }
    }

    public static List<Chat> getUserChats(int userId) throws LogicException {
        Connection connection = null;
        try {
            connection = Pool.getPool().getConnection();
            MessageDAO messageDAO = new MessageDAO(connection);
            return messageDAO.getChatsByUserId((userId));
        } catch(PoolConnectionException | DAOException e ) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(connection);
        }
    }
}
