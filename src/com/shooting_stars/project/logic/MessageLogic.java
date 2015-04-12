package com.shooting_stars.project.logic;

import com.shooting_stars.project.dao.MessageDAO;
import com.shooting_stars.project.entity.Chat;
import com.shooting_stars.project.exception.DAOException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.exception.PoolConnectionException;
import com.shooting_stars.project.pool.Pool;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Пользователь on 17.03.2015.
 */
public class MessageLogic {
    public static void sendMessage(int userId1, int userId2, String message) {
         //if chat doesn't exist --> insert in table chat
    }
    public static void sendMessage(int chatId, String message) {

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
