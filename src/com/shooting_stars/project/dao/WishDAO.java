package com.shooting_stars.project.dao;

import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.entity.Wish;
import com.shooting_stars.project.exception.DAOException;
import java.sql.*;
import java.util.ArrayList;

public class WishDAO extends AbstractDAO {
    public static final String SQL_INSERT_WISH = "INSERT INTO wish (userId, wish) VALUES (?,?)";
    public static final String SQL_SELECT_WISHES_BY_ID = "SELECT wishId, wish FROM wish WHERE userId = ? AND wishStatusId = 0";
    public static final String SQL_DELETE_WISH = "DELETE FROM wish WHERE wishId = ?";
    public static final String SQL_DELETE_MAKING_USER = "DELETE FROM fulfilled_wish WHERE wishId = ?";
    public static final String SQL_DELETE_ALL_USERS_CONSIDERED = "DELETE from considered_wish WHERE wishId = ?";
    public static final String SQL_DELETE_USER_CONSIDERED = "DELETE from considered_wish WHERE wishId = ? AND userId = ?";
    public static final String SQL_CHANGE_FULFILLED_WISH_STATUS = "UPDATE fulfilled_wish " +
            "SET fulfilled_wish.wishStatusId = (SELECT wish_status.wishStatusId FROM wish_status WHERE wish_status.wishStatus LIKE ?), date = ? WHERE wishId = ?";
    public static final String SQL_CHANGE_FULFILLED_WISH_STATUS_TABLE_WISH = "UPDATE wish " +
            "SET wish.wishStatusId = (SELECT wish_status.wishStatusId FROM wish_status WHERE wish_status.wishStatus LIKE ?) WHERE wishId = ?";
    public static final String SQL_INSERT_MAKING_USER = "INSERT INTO fulfilled_wish (wishId,userId) VALUES (?,?)";
    public static final String SQL_INSERT_USER_CONSIDERED = "INSERT INTO considered_wish (wishId,userId) VALUES (?,?)";
    public static final String SQL_SELECT_MAKING_USER_BY_WISH_ID = "SELECT fulfilled_wish.userId, user_name, surname" +
            "            FROM user JOIN fulfilled_wish ON fulfilled_wish.userId = user.userId " +
            " JOIN user_info ON user.userId = user_info.userId WHERE wishId = ?";
    public static final String SQL_SELECT_USERS_CONSIDERED_BY_WISH_ID = "SELECT considered_wish.userId, user_name, surname " +
            "FROM user  JOIN considered_wish ON considered_wish.userId = user.userId " +
            "JOIN user_info ON user.userId = user_info.userId WHERE wishId = ?";
    public static final String SQL_SELECT_MAKING_USER_ID_BY_WISH_ID = "SELECT userId FROM fulfilled_wish WHERE wishId = ?";
    public static final String SQL_SELECT_USER_ID_BY_WISH_ID = "SELECT userId FROM wish WHERE wishId = ?";
    public static final String SQL_SELECT_FULFILLED_WISH_BY_OWNER_ID = "SELECT wish.wishId, wish, fulfilled_wish.userId, user_name, surname, fulfilled_wish.date FROM wish JOIN fulfilled_wish" +
            " ON fulfilled_wish.wishId = wish.wishId JOIN user ON fulfilled_wish.userId = user.userId " +
            "JOIN user_info ON user.userId = user_info.userId " +
            "JOIN wish_status ON wish.wishStatusId = wish_status.wishStatusId WHERE wish.userId = ? AND wishStatus LIKE ?" +
            " ORDER BY fulfilled_wish.date";
    public static final String SQL_SELECT_FULFILLED_WISH_BY_MAKING_USER_ID = "SELECT wish.wishId, wish, wish.userId, user_name, surname,  fulfilled_wish.date, wishStatus " +
            "FROM user JOIN wish ON user.userId = wish.userId " +
            "JOIN user_info ON user.userId = user_info.userId " +
            "JOIN fulfilled_wish ON fulfilled_wish.wishId = wish.wishId " +
            "JOIN wish_status ON wish.wishStatusId = wish_status.wishStatusId " +
            "WHERE fulfilled_wish.userId = ? ";
    public static final String SQL_SELECT_WISH_CONSIDERED_BY_MAKING_USER_ID = "SELECT wish.wishId, wish, wish.userId, user_name, surname " +
            "FROM user JOIN wish ON user.userId = wish.userId " +
            " JOIN user_info ON user.userId = user_info.userId " +
            "JOIN considered_wish ON considered_wish.wishId = wish.wishId " +
            "WHERE considered_wish.userId = ? ";

    public WishDAO(Connection connection) {
        super(connection);
    }

    public void deleteWish(int wishId) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_DELETE_WISH);
            ps.setInt(1,wishId);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new DAOException("Problem with connection or statement", e);
        } finally {
            close(ps);
        }
    }

    public void insertWishes(int userId,ArrayList<String> wishes) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_INSERT_WISH);
            for (String wish : wishes) {
                ps.setInt(1,userId);
                ps.setString(2,wish);
                ps.addBatch();
            }
            ps.executeBatch();
        }
        catch (SQLException e) {
            throw new DAOException("Problem with connection or statement", e);
        } finally {
            close(ps);
        }
    }

    public ArrayList<Wish> getWishes(int userId) throws DAOException {
        ArrayList<Wish> wishes = new ArrayList<Wish>(5);
        PreparedStatement ps = null;
        ResultSet rs;
        Wish wish;
        try {
            ps = connection.prepareStatement(SQL_SELECT_WISHES_BY_ID);
            ps.setInt(1,userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                wish = new Wish(rs.getInt(1),rs.getString(2));
                wishes.add(wish);
            }
            return wishes;
        }
        catch (SQLException e) {
            throw new DAOException("Problem with connection or statement", e);
        } finally {
            close(ps);
        }
    }

    public void deleteUserWishMaker(int wishId) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_DELETE_MAKING_USER);
            ps.setInt(1,wishId);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new DAOException("Problem with connection or statement", e);
        } finally {
            close(ps);
        }
    }

    public void deleteAllUsersConsidered(int wishId) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_DELETE_ALL_USERS_CONSIDERED);
            ps.setInt(1,wishId);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new DAOException("Problem with connection or statement", e);
        } finally {
            close(ps);
        }
    }

    public void deleteUserConsidered(int wishId,int userId) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_DELETE_USER_CONSIDERED);
            ps.setInt(1,wishId);
            ps.setInt(2,userId);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new DAOException("Problem with connection or statement", e);
        } finally {
            close(ps);
        }
    }

    public void changeFulfilledWishStatus(int wishId, Date date) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_CHANGE_FULFILLED_WISH_STATUS);
            ps.setString(1,"Выполнено");
            ps.setDate(2,date);
            ps.setInt(3,wishId);
            ps.executeUpdate();
            ps = connection.prepareStatement(SQL_CHANGE_FULFILLED_WISH_STATUS_TABLE_WISH);
            ps.setString(1,"Выполнено");
            ps.setInt(2,wishId);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new DAOException("Problem with connection or statement", e);
        } finally {
            close(ps);
        }
    }

    public void insertUserWishMaker(int wishId, int userId) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_INSERT_MAKING_USER);
            ps.setInt(1,wishId);
            ps.setInt(2,userId);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new DAOException("Problem with connection or statement", e);
        } finally {
            close(ps);
        }
    }

    public void insertUserConsidered(int wishId, int userId) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_INSERT_USER_CONSIDERED);
            ps.setInt(1,wishId);
            ps.setInt(2,userId);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new DAOException("Problem with connection or statement", e);
        } finally {
            close(ps);
        }
    }

    public ArrayList<User> getUsersConsidered(int wishId) throws DAOException {
        ArrayList<User> users = new ArrayList<User>();
        PreparedStatement ps = null;
        ResultSet rs;
        User user;
        try {
            ps = connection.prepareStatement(SQL_SELECT_USERS_CONSIDERED_BY_WISH_ID);
            ps.setInt(1,wishId);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt(1),rs.getString(2),rs.getString(3));
                users.add(user);
            }
            return users;
        }
        catch (SQLException e) {
            throw new DAOException("Problem with connection or statement", e);
        } finally {
            close(ps);
        }
    }

    public User getMakingUser(int wishId) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs;
        User user = null;
        try {
            ps = connection.prepareStatement(SQL_SELECT_MAKING_USER_BY_WISH_ID);
            ps.setInt(1, wishId);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt(1),rs.getString(2), rs.getString(3));
            }
            return user;
        }
        catch (SQLException e) {
            throw new DAOException("Problem with connection or statement", e);
        } finally {
            close(ps);
        }
    }

    public int getWishMakerUserIdByWishId(int wishId) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs;
        int userId = 0;
        try {
            ps = connection.prepareStatement(SQL_SELECT_MAKING_USER_ID_BY_WISH_ID);
            ps.setInt(1,wishId);
            rs = ps.executeQuery();
            if (rs.next()) {
                userId = rs.getInt(1);
            }
            return userId;
        }
        catch (SQLException e) {
            throw new DAOException("Problem with connection or statement", e);
        } finally {
            close(ps);
        }
    }
    public int getWishOwnerUserIdByWishId(int wishId) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs;

        try {
            int userId = 0;
            ps = connection.prepareStatement(SQL_SELECT_USER_ID_BY_WISH_ID);
            ps.setInt(1,wishId);
            rs = ps.executeQuery();
            if (rs.next()) {
                userId = rs.getInt(1);
            }
            return userId;
        }
        catch (SQLException e) {
            throw new DAOException("Problem with connection or statement", e);
        } finally {
            close(ps);
        }
    }

    public ArrayList<Wish> getFulfilledWishesByOwnerUserId(int userId) throws DAOException {
        ArrayList<Wish> wishes = new ArrayList<Wish>();
        PreparedStatement ps = null;
        ResultSet rs;
        Wish wish;
        try {
            ps = connection.prepareStatement(SQL_SELECT_FULFILLED_WISH_BY_OWNER_ID);
            ps.setInt(1,userId);
            ps.setString(2,"Выполнено");
            rs = ps.executeQuery();
            while (rs.next()) {
                wish = new Wish(rs.getInt(1),rs.getString(2));
                wish.setCandidate(new User(rs.getInt(3), rs.getString(4),rs.getString(5)));
                wish.setDate(rs.getDate(6));
                wishes.add(wish);
            }
            return wishes;
        }
        catch (SQLException e) {
            throw new DAOException("Problem with connection or statement", e);
        } finally {
            close(ps);
        }
    }

    public ArrayList<Wish> getWishesByMakerUserId(int userId) throws DAOException {
        ArrayList<Wish> wishes = new ArrayList<Wish>();
        PreparedStatement ps = null;
        ResultSet rs;
        Wish wish;
        try {
            ps = connection.prepareStatement(SQL_SELECT_FULFILLED_WISH_BY_MAKING_USER_ID);
            ps.setInt(1,userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                wish = new Wish(rs.getInt(1),rs.getString(2));
                wish.setOwner(new User(rs.getInt(3),rs.getString(4),rs.getString(5)));
                wish.setDate(rs.getDate(6));
                wish.setFulfilled(true);
                wishes.add(wish);
            }
            ps = connection.prepareStatement(SQL_SELECT_WISH_CONSIDERED_BY_MAKING_USER_ID);
            ps.setInt(1,userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                wish = new Wish(rs.getInt(1),rs.getString(2));
                wish.setOwner(new User(rs.getInt(3),rs.getString(4)));
                wish.setFulfilled(false);
                wishes.add(wish);
            }
            return wishes;
        }
        catch (SQLException e) {
            throw new DAOException("Problem with connection or statement", e);
        } finally {
            close(ps);
        }
    }
}
