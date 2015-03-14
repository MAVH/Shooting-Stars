package com.shooting_stars.project.dao;

import com.shooting_stars.project.entity.Wish;
import com.shooting_stars.project.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Пользователь on 14.03.2015.
 */
public class WishDAO extends AbstractDAO {
    public static final String SQL_INSERT_WISH = "INSERT INTO wish (userId, wish) VALUES (?,?)";
    public static final String SQL_SELECT_WISHES_BY_ID = "SELECT wishId, wish FROM wish WHERE userId = ?";
    public static final String SQL_DELETE_WISH = "DELETE FROM wish WHERE wishId = ?";
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
}
