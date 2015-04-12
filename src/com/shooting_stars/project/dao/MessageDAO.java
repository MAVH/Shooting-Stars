package com.shooting_stars.project.dao;

import com.shooting_stars.project.entity.Chat;
import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO extends AbstractDAO {
    public static final String SQL_SELECT_CHATS_BY_USER_ID = "SELECT chatId, chat.user1Id, user_name, surname " +
            "FROM user_info JOIN chat ON user_info.userId = chat.user1Id " +
            "WHERE user2Id = ? " +
            "UNION " +
            "SELECT chatId, chat.user2Id, user_name, surname " +
            "FROM user_info JOIN chat ON user_info.userId = chat.user2Id " +
            "WHERE user1Id = ?";
    public static final String SQL_GET_AMOUNT_UNREAD_MESSAGES_BY_CHAT_ID = "SELECT COUNT(*) " +
            "FROM message JOIN chat ON message.chatId = chat.chatId " +
            "WHERE chat.chatId = ? AND isRead = 0 AND NOT sender = ?";
    public static final String SQL_INSERT_CHAT = "INSERT INTO chat (userId1,userId2) VALUES (?,?)";
    public static final String SQL_SELECT_CHAT_ID = "SELECT chatId FROM chat WHERE (userId1 = ? AND userId2 = ?) " +
            "OR (userId1 = ? AND userId2 = ?)";
    public static final String SQL_SELECT_MESSAGES_BY_CHAT_ID = "SELECT messageId, message, date, time, sender, login" +
            " FROM user JOIN message ON user.userId = message.sender WHERE chatId = ?";
    public static final String SQL_INSERT_MESSAGE = "INSERT INTO message (chatId, message, date, time, sender)" +
            "VALUES (?,?,?,?,?) ";
    public MessageDAO(Connection connection) {
        super(connection);
    }

    public List<Chat> getChatsByUserId(int userId) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs;
        ArrayList<Chat> chats = new ArrayList<Chat>();
        try {
            ps = connection.prepareStatement(SQL_SELECT_CHATS_BY_USER_ID);
            ps.setInt(1, userId);
            ps.setInt(2,userId);
            rs = ps.executeQuery();
            int chatId;
            User user;
            int amount;
            while (rs.next()) {
                chatId = rs.getInt(1);
                user = new User(rs.getInt(2),rs.getString(3), rs.getString(4));
                amount = getAmountUnreadMessages(chatId,userId);
                chats.add(new Chat(chatId,user,amount));
            }
        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): ", e);
        }
        finally {
            close(ps);
        }
        return chats;
    }
    public int getAmountUnreadMessages(int chatId, int userId) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs;
        int amount;
        try {
            ps = connection.prepareStatement(SQL_GET_AMOUNT_UNREAD_MESSAGES_BY_CHAT_ID);
            ps.setInt(1,chatId);
            ps.setInt(2,userId);
            rs = ps.executeQuery();
            rs.next();
            amount = rs.getInt(1);
        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): ", e);
        }
        finally {
            close(ps);
        }
        return amount;
    }

}
