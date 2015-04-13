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
    private static final int MESSAGES_AMOUNT_ON_ONE_PAGE = 10;
    public static void sendMessage(int userFromId, int userToId, String message) throws LogicException {
            int chatId = MessageLogic.getChatId(userFromId,userToId);
            MessageLogic.sendMessage(chatId,message,userFromId);
    }

    public static int getChatId(int user1Id, int user2Id) throws LogicException {
        Connection connection = null;
        try {
            connection = Pool.getPool().getConnection();
            MessageDAO messageDAO = new MessageDAO(connection);
            if(messageDAO.getChatsAmountByUsers(user1Id,user2Id) == 0) {
                messageDAO.insertChat(user1Id,user2Id);
            }
            int chatId = messageDAO.getChatId(user1Id,user2Id);
            return chatId;
        } catch(PoolConnectionException | DAOException e ) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(connection);
        }
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
    public static List<Message> getMessages(int chatId,int userId, int page) throws LogicException {
        Connection connection = null;
        try {
            connection = Pool.getPool().getConnection();
            MessageDAO messageDAO = new MessageDAO(connection);
            int from = MESSAGES_AMOUNT_ON_ONE_PAGE * (page - 1);
            int to = MESSAGES_AMOUNT_ON_ONE_PAGE * page;
            List<Message> messages = messageDAO.getMessagesByChatId(chatId,from,to);
            for(Message message : messages) {
                 if(userId == message.getSender().getUserId()) {
                     message.setLoggedInUser(true);
                 } else {
                     message.setLoggedInUser(false);
                 }
            }
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
