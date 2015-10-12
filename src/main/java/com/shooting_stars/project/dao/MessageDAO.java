package com.shooting_stars.project.dao;

import com.shooting_stars.project.entity.Chat;
import com.shooting_stars.project.entity.Message;
import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.exception.DAOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO extends AbstractDAO {

    public static final String SQL_DELETE_CHAT_BY_CHAT_ID = "DELETE FROM chat WHERE chatId = ?";
    public static final String SQL_SELECT_CHATS_BY_USER_ID = "SELECT chatId, chat.user1Id, user_name, surname, photoName, " +
            "(SELECT  message.date FROM message WHERE message.chatId = chat.chatId ORDER BY message.date DESC LIMIT 1) AS chatDate, " +
            "(SELECT  message.time FROM message  WHERE message.chatId = chat.chatId AND chatDate = message.date ORDER BY message.time DESC LIMIT 1) AS chatTime " +
            "FROM user_info JOIN chat ON user_info.userId = chat.user1Id " +
            "WHERE user2Id = ? " +
            "UNION " +
            "SELECT chatId, chat.user2Id, user_name, surname, photoName, " +
            "(SELECT  message.date FROM message WHERE message.chatId = chat.chatId ORDER BY message.date DESC LIMIT 1) AS chatDate, " +
            "(SELECT  message.time FROM message  WHERE message.chatId = chat.chatId AND chatDate = message.date ORDER BY message.time DESC LIMIT 1) AS chatTime " +
            "FROM user_info JOIN chat ON user_info.userId = chat.user2Id " +
            "WHERE user1Id = ? " +
            "ORDER BY chatDate DESC, chatTime DESC " +
            "LIMIT ?,?";
    public static final String SQL_GET_AMOUNT_UNREAD_MESSAGES_BY_USER_ID = "SELECT COUNT(*) FROM message " +
            "WHERE chatId IN (SELECT chatId FROM  chat  WHERE user2Id = ? " +
            "UNION " +
            "SELECT chatId FROM chat WHERE user1Id = ?) " +
            "AND isRead = 0 AND NOT sender = ? ";
    public static final String SQL_GET_AMOUNT_UNREAD_MESSAGES_BY_CHAT_ID = "SELECT COUNT(*) " +
            "FROM message JOIN chat ON message.chatId = chat.chatId " +
            "WHERE chat.chatId = ? AND isRead = 0 AND NOT sender = ?";
    public static final String SQL_INSERT_CHAT = "INSERT INTO chat (user1Id,user2Id) VALUES (?,?)";
    public static final String SQL_SELECT_CHAT_ID = "SELECT chatId FROM chat WHERE (user1Id = ? AND user2Id = ?) " +
            "OR (user1Id = ? AND user2Id = ?)";
    public static final String SQL_SELECT_MESSAGES_BY_CHAT_ID = "SELECT sender, user_name, surname, photoName, message, message.date, message.time" +
            " FROM message JOIN user_info ON sender = userId WHERE chatId = ? ORDER BY message.date DESC, message.time DESC " +
            "LIMIT ?,?";
    public static final String SQL_INSERT_MESSAGE = "INSERT INTO message (chatId, message, date, time, sender)" +
            "VALUES (?,?,?,?,?) ";
    public static final String SQL_UPDATE_MESSAGE_STATUS = "UPDATE message SET isRead = 1 " +
            "WHERE chatId = ? AND NOT sender = ? AND isRead = 0";
    public static final String SQL_GET_CHATS_AMOUNT_BY_USERS_ID = "SELECT COUNT(*) FROM chat " +
            "WHERE (user1Id = ? AND user2Id = ?) OR (user1Id = ? AND user2Id = ?)";
    public static final String SQL_GET_GIVEN_USER_CHATS_AMOUNT = "SELECT COUNT(*) FROM chat " +
            "WHERE user1Id = ? OR user2Id = ?";
    public static final String SQL_COUNT_MESSAGES_BY_CHAT_ID = "SELECT COUNT(*) FROM message WHERE chatId = ?";
    public static final String SQL_GET_UNREAD_MESSAGES_BY_CHAT_ID_AND_USER_ID =
            "SELECT sender, user_name, surname, photoName, message, message.date, message.time " +
            "FROM message JOIN chat ON message.chatId = chat.chatId " +
            "JOIN user_info ON sender = userId " +
            "WHERE chat.chatId = ? AND isRead = 0 AND NOT sender = ? ORDER BY message.date DESC, message.time DESC " +
            "LIMIT ?, ?";
    public static final String SQL_COUNT_CHATS_BY_CHAT_ID_AND_USER_ID = "SELECT COUNT(*) FROM chat WHERE chatId = ? AND " +
            "(user1Id = ? OR user2Id = ?)";
    public MessageDAO(Connection connection) {
        super(connection);
    }

    public void deleteChat(int chatId) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_DELETE_CHAT_BY_CHAT_ID);
            ps.setInt(1,chatId);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): ", e);
        }
        finally {
            close(ps);
        }
    }
    public List<Chat> getChatsByUserId(int userId, int from, int num) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs;
        ArrayList<Chat> chats = new ArrayList<Chat>();
        try {
            ps = connection.prepareStatement(SQL_SELECT_CHATS_BY_USER_ID);
            ps.setInt(1, userId);
            ps.setInt(2, userId);
            ps.setInt(3, from);
            ps.setInt(4, num);
            rs = ps.executeQuery();
            int chatId;
            User user;
            int amount;
            int messagesAmount;
            while (rs.next()) {
                chatId = rs.getInt(1);
                user = new User(rs.getInt(2),rs.getString(3), rs.getString(4),rs.getString(5));
                amount = getAmountUnreadMessages(chatId,userId);
                messagesAmount = countMessages(chatId);
                if(messagesAmount != 0) {
                    chats.add(new Chat(chatId, user, amount));
                }
                else {
                    deleteChat(chatId);
                }
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
    public List<Message> getMessagesByChatId(int chatId,int from, int num) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs;
        ArrayList<Message> messages = new ArrayList<Message>();
        try {
            ps = connection.prepareStatement(SQL_SELECT_MESSAGES_BY_CHAT_ID);
            ps.setInt(1, chatId);
            ps.setInt(2,from);
            ps.setInt(3,num);
            rs = ps.executeQuery();
            User user;
            String message;
            Date date;
            Time time;
            while (rs.next()) {
                user = new User(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4));
                message = rs.getString(5);
                date = rs.getDate(6);
                time = rs.getTime(7);
                messages.add(new Message(message,user, date,time));
            }
        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): ", e);
        }
        finally {
            close(ps);
        }
        return messages;
    }
    public void insertMessage(int chatId, String message, int senderId, Date date, Time time) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_INSERT_MESSAGE);
            ps.setInt(1, chatId);
            ps.setString(2, message);
            ps.setDate(3,date);
            ps.setTime(4,time);
            ps.setInt(5, senderId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): ", e);
        }
        finally {
            close(ps);
        }
    }
    public void updateMessagesStatus(int chatId, int userId) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_UPDATE_MESSAGE_STATUS);
            ps.setInt(1, chatId);
            ps.setInt(2,userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): ", e);
        }
        finally {
            close(ps);
        }
    }
    public int getChatId(int user1Id, int user2Id)throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs;

        try {
            ps = connection.prepareStatement(SQL_SELECT_CHAT_ID);
            ps.setInt(1,user1Id);
            ps.setInt(2,user2Id);
            ps.setInt(3,user2Id);
            ps.setInt(4,user1Id);
            rs = ps.executeQuery();
            int chatId = 0;
            if(rs.next()) {
                chatId = rs.getInt(1);
            }
            return chatId;
        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): ", e);
        }
        finally {
            close(ps);
        }
    }
    public void insertChat(int user1Id, int user2Id) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_INSERT_CHAT);
            ps.setInt(1, user1Id);
            ps.setInt(2, user2Id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): ", e);
        }
        finally {
            close(ps);
        }
    }
    public int getChatsAmountByUsers(int user1Id, int user2Id) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs;
        int amount;
        try {
            ps = connection.prepareStatement(SQL_GET_CHATS_AMOUNT_BY_USERS_ID);
            ps.setInt(1,user1Id);
            ps.setInt(2,user2Id);
            ps.setInt(3,user2Id);
            ps.setInt(4,user1Id);
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
    public int countMessages(int chatId) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs;
        int amount;
        try {
            ps = connection.prepareStatement(SQL_COUNT_MESSAGES_BY_CHAT_ID);
            ps.setInt(1,chatId);
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

    public int getAmountUnreadMessages(int userId) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs;
        int amount;
        try {
            ps = connection.prepareStatement(SQL_GET_AMOUNT_UNREAD_MESSAGES_BY_USER_ID);
            ps.setInt(1, userId);
            ps.setInt(2, userId);
            ps.setInt(3, userId);
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

    public int getChatsAmountByUserId(int userId) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs;
        int amount;
        try {
            ps = connection.prepareStatement(SQL_GET_GIVEN_USER_CHATS_AMOUNT);
            ps.setInt(1,userId);
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

    public List<Message> getUnreadMessages(int chatId, int userId, int count) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs;
        ArrayList<Message> messages = new ArrayList<Message>();
        try {
            ps = connection.prepareStatement(SQL_GET_UNREAD_MESSAGES_BY_CHAT_ID_AND_USER_ID);
            ps.setInt(1, chatId);
            ps.setInt(2, userId);
            ps.setInt(3,0);
            ps.setInt(4,count);
            rs = ps.executeQuery();
            User user;
            String message;
            Date date;
            Time time;
            while (rs.next()) {
                user = new User(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4));
                message = rs.getString(5);
                date = rs.getDate(6);
                time = rs.getTime(7);
                messages.add(new Message(message,user, date,time));
            }
        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): ", e);
        }
        finally {
            close(ps);
        }
        return messages;
    }
    public int getChatsAmountByUserIdAndChatId(int userId, int chatId) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs;
        int amount;
        try {
            ps = connection.prepareStatement(SQL_COUNT_CHATS_BY_CHAT_ID_AND_USER_ID);
            ps.setInt(1,chatId);
            ps.setInt(2,userId);
            ps.setInt(3,userId);
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