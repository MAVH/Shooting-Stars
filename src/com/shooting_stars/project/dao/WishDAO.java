package com.shooting_stars.project.dao;

import com.shooting_stars.project.entity.Wish;
import com.shooting_stars.project.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Пользователь on 14.03.2015.
 */
public class WishDAO extends AbstractDAO {
    public static final String SQL_INSERT_WISH = "INSERT INTO wish (userId, wish) VALUES (?,?)";
    public static final String SQL_SELECT_WISHES_BY_ID = "SELECT wishId, wish FROM wish WHERE userId = ?";
    public static final String SQL_DELETE_WISH = "DELETE FROM wish WHERE wishId = ?";
    public static final String SQL_DELETE_MAKING_USER = "DELETE from fulfiled_wish WHERE wishId = ?";
    public static final String SQL_DELETE_ALL_USERS_CONSIDERED = "DELETE from considered_wish WHERE wishId = ?";
    public static final String SQL_DELETE_USER_CONSIDERED = "DELETE from considered_wish WHERE wishId = ? AND userId = ?";
    public static final String SQL_CHANGE_FULFILED_WISH_STATUS = "UPDATE fulfiled_wish " +
            "SET fulfiled_wish.wishStatusId = (SELECT wish_status.wishStatusId FROM wish_status WHERE wish_status.wishStatus LIKE ?), date = ? WHERE wishId = ?";
    public static final String SQL_CHANGE_FULFILED_WISH_STATUS_TABLE_WISH = "UPDATE wish " +
            "SET wish.wishStatusId = (SELECT wish_status.wishStatusId FROM wish_status WHERE wish_status.wishStatus LIKE ?) WHERE wishId = ?";
    public static final String SQL_INSERT_MAKING_USER = "INSERT INTO fulfiled_wish (wishId,userId) VALUES (?,?)";
    public static final String SQL_INSERT_USER_CONSIDERED = "INSERT INTO considered_wish (wishId,userId) VALUES (?,?)";
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
        ArrayList<Wish> wishes = new ArrayList<>(5);
        PreparedStatement ps = null;
        ResultSet rs = null;
        Wish wish = null;
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
    public void deleteMakingUser(int wishId) throws DAOException {
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
    public void changeFulfiledWishStatus(int wishId, Date date) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_CHANGE_FULFILED_WISH_STATUS);
            ps.setString(1,"Выполнено");
            ps.setDate(2,date);
            ps.setInt(3,wishId);
            ps.executeUpdate();
            ps = connection.prepareStatement(SQL_CHANGE_FULFILED_WISH_STATUS_TABLE_WISH);
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
    public void insertMakingUser(int wishId, int userId) throws DAOException {
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
}
