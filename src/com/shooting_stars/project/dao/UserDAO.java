package com.shooting_stars.project.dao;

import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.entity.UserInfo;
import com.shooting_stars.project.entity.UserToBeRegistered;
import com.shooting_stars.project.exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO extends AbstractDAO {
    public static final String SQL_FIND_USER = "SELECT userId FROM user WHERE login = ? and password = ?";
    public static final String SQL_INSERT_USER = "INSERT INTO user (login, password) VALUES (?,?)";
    public static final String SQL_INSERT_USER_INFO =
            "INSERT INTO user_info (userId, email, user_name, surname, country, city, dateOfBirth, abilities) VALUES (?,?,?,?,?,?,?,?)";
    public static final String SQL_CHECK_LOGIN_EXISTENCE = "SELECT COUNT(login) FROM user WHERE login = ?";
    public static final  String SQL_UPDATE_PHOTO_URL = "UPDATE user_info SET photoURL = ? WHERE userId = ?";
    public static final String SQL_FIND_USER_BY_LOGIN = "SELECT userId, login FROM user WHERE login LIKE ?";
    public static final String SQL_SELECT_USER_INFO = "SELECT * FROM user_info WHERE userId = ?";
    public static final String SQL_GET_STATUS = "SELECT status FROM user_status JOIN user ON user.userStatusId = user_status.userStatusId WHERE user.userId = ?";
    public static final String SQL_SET_STATUS = "UPDATE user SET userStatusId = ? WHERE userId = ?";

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

    public void updatePhotoURL(int userId, String photoURL) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_UPDATE_PHOTO_URL);
            ps.setString(1, photoURL);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): ", e);
        }
        finally {
            close(ps);
        }
    }

    //Полное совпадение Логина
    public User findUserByLogin(String login) throws DAOException {
        User user = null;
        PreparedStatement ps = null;
        ResultSet rs;
        try {
            ps = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN);
            ps.setString(1, "%"+login+"%");
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

    //Находит пользователей с логином, часть которого - введенный
    public ArrayList<User> findUsersByLogin(String login) throws DAOException {
        User user = null;
        PreparedStatement ps = null;
        ResultSet rs;
        ArrayList<User> users = new ArrayList<User>();
        try {
            ps = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN);
            ps.setString(1, "%"+login+"%");
            rs = ps.executeQuery();
            int userId;
            while (rs.next()) {
                userId = rs.getInt(1);
                login = rs.getString(2);
                user = new User(userId, login);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): ", e);
        }
        finally {
            close(ps);
        }
        return users;
    }

    public UserInfo findUserInfoByUserId(int userId) throws DAOException {
        UserInfo userInfo = new UserInfo();
        PreparedStatement ps = null;
        ResultSet rs;
        try {
            ps = connection.prepareStatement(SQL_SELECT_USER_INFO);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if(rs.next()) {
                userInfo.setName(rs.getString("user_name"));
                userInfo.setSurname(rs.getString("surname"));
                userInfo.setEmail(rs.getString("email"));
                userInfo.setCountry(rs.getString("country"));
                userInfo.setCity(rs.getString("city"));
                userInfo.setDateOfBirth(rs.getDate("dateOfBirth"));
                userInfo.setAbilities(rs.getString("abilities"));
                userInfo.setPhotoName(rs.getString("photoURL"));
            }
        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): ", e);
        }
        finally {
            close(ps);
        }
        return userInfo;
    }

    public String getUserStatus(int userId) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs;
        String status = "";
        try {
            ps = connection.prepareStatement(SQL_GET_STATUS);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if(rs.next()) {
               status = rs.getString(1);
            }
        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): ", e);
        }
        finally {
            close(ps);
        }
        return status;
    }

    public void setUserStatus(int userId, int userStatusId) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_SET_STATUS);
            ps.setInt(1, userStatusId);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): ", e);
        }
        finally {
            close(ps);
        }
    }
}

