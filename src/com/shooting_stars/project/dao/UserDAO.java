package com.shooting_stars.project.dao;

import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.entity.UserToBeRegistered;
import com.shooting_stars.project.exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends AbstractDAO {
    public static final String SQL_FIND_USER = "SELECT userId FROM user WHERE login = ? and password = ?";
    public static final String SQL_INSERT_USER = "INSERT INTO user (login, password) VALUES (?,?)";
    public static final String SQL_INSERT_USER_INFO =
            "INSERT INTO user_info (userId, email, user_name, surname, country, city, dateOfBirth, abilities) VALUES (?,?,?,?,?,?,?,?)";

    public UserDAO(Connection connection) {
        super(connection);
    }

    public User findUserByLoginAndPassword(String login, String password) throws DAOException {
        User user = null;
        PreparedStatement ps = null;
        ResultSet rs;
        try {
            ps = connection.prepareStatement(SQL_FIND_USER);
            ps.setString(1, login);
            ps.setString(2, password);
            rs = ps.executeQuery();
            int userId;
            if(rs.next()) {
                userId = rs.getInt(1);
                user = new User(userId, login);
            }
        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): ", e);
        }
        finally {
            close(ps);
        }
        return user;
    }

    public User registerUser(UserToBeRegistered user) throws DAOException {
        User new_user = null;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_INSERT_USER);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.executeQuery();
            //change next line
            new_user = findUserByLoginAndPassword(user.getLogin(),user.getPassword());
            ps = connection.prepareStatement(SQL_INSERT_USER_INFO);
            ps.setInt(1, new_user.getUserId());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getName());
            ps.setString(4, user.getSurname());
            ps.setString(5, user.getCountry());
            ps.setString(6, user.getCity());
            ps.setDate(7, user.getDateOfBirth());
            ps.setString(8, user.getAbilities());
            ps.executeQuery();
        }
        catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): ", e);
        }
        finally {
            close(ps);
        }
        return new_user;
    }
}
