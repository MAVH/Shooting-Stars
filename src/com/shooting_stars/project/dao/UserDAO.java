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
    public static final String SQL_CHECK_LOGIN_EXISTENCE = "SELECT COUNT(login) FROM user WHERE login = ?";

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
        User newUser = null;
        PreparedStatement ps = null;
        try {
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(SQL_INSERT_USER);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
            //change next line
            newUser = findUserByLoginAndPassword(user.getLogin(),user.getPassword());
            ps = connection.prepareStatement(SQL_INSERT_USER_INFO);
            ps.setInt(1, newUser.getUserId());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getName());
            ps.setString(4, user.getSurname());
            ps.setString(5, user.getCountry());
            ps.setString(6, user.getCity());
            ps.setDate(7, user.getDateOfBirth());
            ps.setString(8, user.getAbilities());
            ps.executeUpdate();
            connection.commit();
        }
        catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DAOException("SQL exception (request or table failed): ", e1);
            }
            throw new DAOException("SQL exception (request or table failed): ", e);
        }
        finally {
            close(ps);
        }
        return newUser;
    }

    public boolean checkUserLoginExistence(String login) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs;
        boolean exist = false;
        try {
            ps = connection.prepareStatement(SQL_CHECK_LOGIN_EXISTENCE);
            ps.setString(1, login);
            rs = ps.executeQuery();
            if(rs.next()) {
                int count = rs.getInt(1);
                if(count > 0) {
                    exist =  true;
                }
            }
        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): ", e);
        }
        finally {
            close(ps);
        }
        return exist;
    }
}
