package com.shooting_stars.project.dao;


import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.entity.WishesOwner;
import com.shooting_stars.project.entity.Wish;
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
    private static final String SQL_ORDER_BY = "ORDER BY REL1 DESC, REL2 DESC LIMIT ?, ?";
    private static final String SQL_SEARCH_BY_NAME = "WHERE user_name LIKE ? ";
    private static final String SQL_SEARCH_BY_SURNAME = "WHERE surname LIKE ? ";
    private static final String SQL_SEARCH_BY_NAME_AND_SURNAME = "WHERE user_name LIKE ? AND surname LIKE ? ";
    private static final String SQL_SEARCH_FILTER_COUNTRY = "AND country LIKE ?";
    private static final String SQL_SEARCH_FILTER_CITY = "AND city LIKE ? ";
    private static final String SQL_SEARCH_FILTER_DATE_OF_BIRTH_MIN = "AND dateOfBirth >= ?";
    private static final String SQL_SEARCH_FILTER_DATE_OF_BIRTH_MAX= "AND dateOfBirth <= ?";

    private static final String SQL_SEARCH_BY_WISH_CRITERIA_PART = "AND wish LIKE  ? ";
    private static final String SQL_SEARCH_BY_WISH = "SELECT wishId, wish, wish.userId, user_name, surname, photoName " +
            "FROM wish JOIN user_info ON wish.userId = user_info.userId WHERE wishStatusId = 0 ";
    private static final String SQL_ORDER_BY_DATE = "ORDER BY creationTime DESC LIMIT ?,? ";

    private static final String SQL_COUNT_WISHES = "SELECT COUNT(*) FROM wish WHERE wishStatusId = 0 ";

    private static final String SQL_SELECT_USERS_BY_BIRTH_DATE = "SELECT userId, user_name, surname, photoName FROM user_info " +
            "WHERE MONTH(dateOfBirth) = MONTH(?) AND DAY(dateOfBirth) = DAY(?) AND userId IN (SELECT userId FROM wish WHERE wishStatusId = 0) LIMIT ?, ? ";

    private static final String SQL_COUNT_USERS_BY_BIRTH_DATE = "SELECT COUNT(*) FROM user_info " +
            "WHERE MONTH(dateOfBirth) = MONTH(?) AND DAY(dateOfBirth) = DAY(?) AND userId IN (SELECT userId FROM wish WHERE wishStatusId = 0)";
    private static final String SQL_COUNT_USERS = "SELECT COUNT(*) FROM user_info WHERE user_name LIKE ? AND surname LIKE ? AND country LIKE ? AND city LIKE ? ";

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

    public List<User> findUsers(String name, String surname, String country, String city, Date dateMin, Date dateMax, int from, int num)
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
                ps.setInt(8,from);
                ps.setInt(9,num);
            } else {
                ps.setDate(7, dateMin);
                ps.setDate(8, dateMax);
                ps.setInt(9,from);
                ps.setInt(10,num);
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

    public int countUsers(String name, String surname, String country, String city)
            throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs;
        try {
            ps = connection.prepareStatement(SQL_COUNT_USERS);
            ps.setString(1, "%" + name + "%");
            ps.setString(2, "%" + surname + "%");
            ps.setString(3, "%" + country + "%");
            ps.setString(4, "%" + city + "%");
            rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): ", e);
        }
        finally {
            close(ps);
        }
    }
    public List<User> findUsers(String name, String surname, String country, String city,int from, int num)
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
            ps.setInt(7, from);
            ps.setInt(8, num);
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

    public int countUsers(String name, String surname, String country, String city, Date dateMin, Date dateMax) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs;
        String query = SQL_COUNT_USERS;
        if(dateMin != null) {
            query += SQL_SEARCH_FILTER_DATE_OF_BIRTH_MIN;
        }
        if(dateMax != null) {
            query += SQL_SEARCH_FILTER_DATE_OF_BIRTH_MAX;
        }
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, "%" + name + "%");
            ps.setString(2, "%" + surname + "%");
            ps.setString(3, "%" + country + "%");
            ps.setString(4, "%" + city + "%");
            if(dateMax == null) {
                ps.setDate(5, dateMin);
            } else if(dateMin == null) {
                ps.setDate(5, dateMax);
            } else {
                ps.setDate(5, dateMin);
                ps.setDate(6, dateMax);
            }
            rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): ", e);
        }
        finally {
            close(ps);
        }
    }

    public List<WishesOwner> findWishes(String[] wishes,int from, int num)
            throws DAOException {
        User user = null;
        PreparedStatement ps = null;
        ResultSet rs;
        ArrayList<WishesOwner> users = new ArrayList<WishesOwner>();
        try {
            String query = SQL_SEARCH_BY_WISH;

            int size = wishes.length;
            for(int i = 0; i < size; i++) {
                 query += SQL_SEARCH_BY_WISH_CRITERIA_PART;
            }
            query += SQL_ORDER_BY_DATE;
            ps = connection.prepareStatement(query);
            int i = 0;
            for(i = 0; i < size; i++) {
                ps.setString(i + 1, '%' + wishes[i] + '%');
            }
            ps.setInt(i + 1, from);
            ps.setInt(i + 2, num);
            rs = ps.executeQuery();
            int userId;
            Wish wish;
            WishesOwner owner;
            while (rs.next()) {
                wish = new Wish(rs.getInt(1), rs.getString(2));
                userId = rs.getInt(3);
                user = new User(userId, rs.getString(4), rs.getString(5), rs.getString(6));
                owner = new WishesOwner(user);
                if(users.contains(owner)) {
                    users.get(users.lastIndexOf(owner)).addWish(wish);
                } else {
                    owner.addWish(wish);
                    users.add(owner);
                }

            }
        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): ", e);
        }
        finally {
            close(ps);
        }
        return users;
    }

    public int countWishes(String[] wishes) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs;
        try {
            String query = SQL_COUNT_WISHES;
            int size = wishes.length;
            for(int i = 0; i < size; i++) {
                query += SQL_SEARCH_BY_WISH_CRITERIA_PART;
            }
            ps = connection.prepareStatement(query);
            for(int i = 0; i < size; i++) {
                ps.setString(i + 1, '%' + wishes[i] + '%');
            }
            rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): ", e);
        }
        finally {
            close(ps);
        }
    }
    public List<User> findUsersByBirthDate(Date date, int from, int num)
            throws DAOException {
        User user = null;
        PreparedStatement ps = null;
        ResultSet rs;
        ArrayList<User> users = new ArrayList<User>();
        try {
            ps = connection.prepareStatement(SQL_SELECT_USERS_BY_BIRTH_DATE);
            ps.setDate(1, date);
            ps.setDate(2,date);
            ps.setInt(3,from);
            ps.setInt(4, num);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4));
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

    public int countUsersByBirthDate(Date date) throws DAOException {

        PreparedStatement ps = null;
        ResultSet rs;
        try {
            ps = connection.prepareStatement(SQL_COUNT_USERS_BY_BIRTH_DATE);
            ps.setDate(1, date);
            ps.setDate(2,date);
            rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): ", e);
        }
        finally {
            close(ps);
        }
    }
}
