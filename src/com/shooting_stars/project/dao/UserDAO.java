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
    public static final  String SQL_UPDATE_PHOTO_NAME = "UPDATE user_info SET photoName = ? WHERE userId = ?";
    public static final String SQL_SELECT_USER_INFO = "SELECT * FROM user_info WHERE userId = ?";
    public static final String SQL_GET_STATUS = "SELECT status FROM user_status JOIN user ON user.userStatusId = user_status.userStatusId WHERE user.userId = ?";
    public static final String SQL_SET_STATUS = "UPDATE user SET userStatusId = ? WHERE userId = ?";
    public static final String SQL_UPDATE_USER_INFO =
            "UPDATE user_info SET user_name = ?, surname = ?, country = ?, city = ?, dateOfBirth = ?, email = ? WHERE userId = ?";
    public static final String SQL_SELECT_ABILITIES = "SELECT abilities FROM user_info WHERE userId = ?";
    public static final String SQL_UPDATE_ABILITIES = "UPDATE user_info SET abilities = ? WHERE userId = ?";
    public static final String SQL_GET_PASSWORD_BY_USER_ID = "SELECT password FROM user WHERE userId = ?";
    public static final String SQL_UPDATE_PASSWORD = "UPDATE user SET password = ? WHERE userId = ?";
    public static final String SQL_UPDATE_LOGIN = "UPDATE user SET login = ? WHERE userId = ?";
    public static final String SQL_UPDATE_EMAIL = "UPDATE user_info SET email = ? WHERE userId = ?";
    public static final String SQL_SELECT_EMAIL = "SELECT email FROM user_info WHERE userId = ?";

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

    public void updatePhotoName(int userId, String photoName) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_UPDATE_PHOTO_NAME);
            ps.setString(1, photoName);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): ", e);
        }
        finally {
            close(ps);
        }
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
                userInfo.setPhotoName(rs.getString("photoName"));
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

    public void updateUserInfo(int userId, UserInfo userInfo) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_UPDATE_USER_INFO);
            ps.setString(1, userInfo.getName());
            ps.setString(2, userInfo.getSurname());
            ps.setString(3, userInfo.getCountry());
            ps.setString(4, userInfo.getCity());
            ps.setDate(5, userInfo.getDateOfBirth());
            ps.setString(6, userInfo.getEmail());
            ps.setInt(7, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): ", e);
        }
        finally {
            close(ps);
        }
    }

    public String findAbilitiesByUserId(int userId) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs;
        String abilities = "";
        try {
            ps = connection.prepareStatement(SQL_SELECT_ABILITIES);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if(rs.next()) {
                abilities = rs.getString(1);
            }
        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): ", e);
        }
        finally {
            close(ps);
        }
        return abilities;
    }

    public void updateUserAbilities(int userId, String abilities) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_UPDATE_ABILITIES);
            ps.setString(1, abilities);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): ", e);
        }
        finally {
            close(ps);
        }
    }

    public String getPasswordByUserId(int userId) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs;
        String password = "";
        try {
            ps = connection.prepareStatement(SQL_GET_PASSWORD_BY_USER_ID);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if(rs.next()) {
                password = rs.getString(1);
            }
        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): ", e);
        }
        finally {
            close(ps);
        }
        return password;
    }

    public void updatePassword(int userId, String password) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_UPDATE_PASSWORD);
            ps.setString(1, password);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): ", e);
        }
        finally {
            close(ps);
        }
    }

    public void updateUserEmail(int userId, String email) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_UPDATE_EMAIL);
            ps.setString(1, email);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): ", e);
        }
        finally {
            close(ps);
        }
    }
    public void updateUserLogin(int userId, String login) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_UPDATE_LOGIN);
            ps.setString(1, login);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): ", e);
        }
        finally {
            close(ps);
        }
    }
    public String findUserEmail(int userId) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String email = null;
        try {
            ps = connection.prepareStatement(SQL_SELECT_EMAIL);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if(rs.next()) {
                email = rs.getString(1);
            }
            return email;

        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): ", e);
        }
        finally {
            close(ps);
        }
    }
}

