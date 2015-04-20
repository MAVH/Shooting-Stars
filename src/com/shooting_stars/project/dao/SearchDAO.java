package com.shooting_stars.project.dao;


import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SearchDAO extends AbstractDAO{
    private static final String SQL_FIND_USER_BY_NAME_AND_SURNAME = "SELECT userId, user_name, surname, photoName, " +
            "MATCH (user_name) AGAINST (?) AS REL1, " +
            "MATCH (surname) AGAINST (?) AS REL2 " +
            "FROM user_info " +
            "WHERE user_name LIKE ? AND surname LIKE ? AND country LIKE ? AND city LIKE ? ";
    private static final String SQL_ORDER_BY = "ORDER BY REL1 DESC, REL2 DESC";
    private static final String SQL_SEARCH_BY_NAME = "WHERE user_name LIKE ? ";
    private static final String SQL_SEARCH_BY_SURNAME = "WHERE surname LIKE ? ";
    private static final String SQL_SEARCH_BY_NAME_AND_SURNAME = "WHERE user_name LIKE ? AND surname LIKE ? ";
    private static final String SQL_SEARCH_FILTER_COUNTRY = "AND country LIKE ?";
    private static final String SQL_SEARCH_FILTER_CITY = "AND city LIKE ?";
    private static final String SQL_SEARCH_FILTER_DATE_OF_BIRTH_MIN = "AND dateOfBirth >= ?";
    private static final String SQL_SEARCH_FILTER_DATE_OF_BIRTH_MAX= "AND dateOfBirth <= ?";

    public SearchDAO(Connection connection) {
        super(connection);
    }

    public List<User> findUsersByName(String name, String surname) throws DAOException {
        User user = null;
        PreparedStatement ps = null;
        ResultSet rs;
        ArrayList<User> users = new ArrayList<User>();
        try {
            ps = connection.prepareStatement(SQL_FIND_USER_BY_NAME_AND_SURNAME);
            ps.setString(1,  name);
            ps.setString(2, surname);
            ps.setString(3, "%" + name + "%");
            ps.setString(4, "%" + surname + "%");
            rs = ps.executeQuery();
            int userId;
            while (rs.next()) {
                userId = rs.getInt(1);
                user = new User(userId, rs.getString(2),rs.getString(3), rs.getString(4));
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

    public List<User> findUsers(String name, String surname, String country, String city, Date dateMin, Date dateMax)
            throws DAOException {
        User user = null;
        PreparedStatement ps = null;
        ResultSet rs;
        ArrayList<User> users = new ArrayList<User>();
        String query = SQL_FIND_USER_BY_NAME_AND_SURNAME;
        if(dateMin != null) {
            query += SQL_SEARCH_FILTER_DATE_OF_BIRTH_MIN;
        }
        if(dateMax != null) {
            query += SQL_SEARCH_FILTER_DATE_OF_BIRTH_MAX;
        }
        query += SQL_ORDER_BY;
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2,  surname );
            ps.setString(3, "%" + name + "%");
            ps.setString(4, "%" + surname + "%");
            ps.setString(5, "%" + country + "%");
            ps.setString(6, "%" + city + "%");
            if(dateMax == null) {
                ps.setDate(7, dateMin);
            } else if(dateMin == null) {
                ps.setDate(7, dateMax);
            } else {
                ps.setDate(7, dateMin);
                ps.setDate(8, dateMax);
            }
            rs = ps.executeQuery();
            int userId;
            while (rs.next()) {
                userId = rs.getInt(1);
                user = new User(userId, rs.getString(2),rs.getString(3), rs.getString(4));
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
    public List<User> findUsers(String name, String surname, String country, String city)
            throws DAOException {
        User user = null;
        PreparedStatement ps = null;
        ResultSet rs;
        ArrayList<User> users = new ArrayList<User>();
        try {
            ps = connection.prepareStatement(SQL_FIND_USER_BY_NAME_AND_SURNAME + SQL_ORDER_BY);
            ps.setString(1, name);
            ps.setString(2,  surname );
            ps.setString(3, "%" + name + "%");
            ps.setString(4, "%" + surname + "%");
            ps.setString(5, "%" + country + "%");
            ps.setString(6, "%" + city + "%");
            rs = ps.executeQuery();
            int userId;
            while (rs.next()) {
                userId = rs.getInt(1);
                user = new User(userId, rs.getString(2),rs.getString(3), rs.getString(4));
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
}
