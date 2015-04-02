package com.shooting_stars.project.dao;

import java.sql.Connection;

public class MessageDAO extends AbstractDAO {
    public static final String SQL_INSERT_CHAT = "INSERT INTO chat (userId1,userId2) VALUES (?,?)";
    public static final String SQL_SELECT_CHAT_ID = "SELECT chatId FROM chat WHERE (userId1 = ? AND userId2 = ?) " +
            "OR (userId1 = ? AND userId2 = ?)";
    public static final String SQL_SELECT_MESSAGES_BY_CHAT_ID = "SELECT messageId, message, date, time, sender, login" +
            " FROM user JOIN message ON user.userId = message.sender WHERE chatId = ?";
    public static final String SQL_SELECT_CHAT_BY_USER_ID = "SELECT chatId, userId1, login " +
            "FROM user JOIN chat ON user.userId = chat.userId1 WHERE userId2 = ?" +
            " UNION SELECT chatId, userId2, login " +
            "FROM user JOIN chat ON user.userId = chat.userId2 WHERE userId1 = ?";
    public static final String SQL_INSERT_MESSAGE = "INSERT INTO message (chatId, message, date, time, sender)" +
            "VALUES (?,?,?,?,?) ";
    public MessageDAO(Connection connection) {
        super(connection);
    }
}
