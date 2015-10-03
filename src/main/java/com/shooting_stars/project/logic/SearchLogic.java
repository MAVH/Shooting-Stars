package com.shooting_stars.project.logic;

import com.shooting_stars.project.dao.SearchDAO;
import com.shooting_stars.project.dao.UserDAO;
import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.entity.WishesOwner;
import com.shooting_stars.project.exception.DAOException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.exception.PoolConnectionException;
import com.shooting_stars.project.pool.Pool;
import org.apache.commons.lang3.StringUtils;

import javax.jws.soap.SOAPBinding;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchLogic {
    public static final int  WISHES_AMOUNT_ON_ONE_PAGE = 10;
    public static final int  USERS_AMOUNT_ON_ONE_PAGE = 10;
    public static int countUsers(String name, String surname, String country, String city, String dateStringMin, String dateStringMax)
            throws LogicException {
        Connection connection = null;
        try {
            connection = Pool.getPool().getConnection();
            SearchDAO searchDAO = new SearchDAO(connection);
            Date dateMin = null;
            Date dateMax = null;
            boolean noDates = true;
            if(StringUtils.isNotEmpty(dateStringMax)) {
                noDates = false;
                dateMax = Date.valueOf(dateStringMax);
            } else {
                dateMax = null;
            }
            if(StringUtils.isNotEmpty(dateStringMin)) {
                noDates = false;
                dateMin = Date.valueOf(dateStringMin);
            } else {
                dateMin = null;
            }
            int count;
            if(noDates) {
                count = searchDAO.countUsers(name, surname, country, city);
            } else {
                count = searchDAO.countUsers(name, surname, country, city, dateMin, dateMax);
            }
            return count;
        } catch(PoolConnectionException | DAOException e ) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(connection);
        }
    }

    public static List<User> findUsers(String name, String surname, String country, String city, String dateStringMin, String dateStringMax, int page)
            throws LogicException {
        Connection connection = null;
        try {
            connection = Pool.getPool().getConnection();
            SearchDAO searchDAO = new SearchDAO(connection);
            List<User> users;
            Date dateMin = null;
            Date dateMax = null;
            boolean noDates = true;
            if(StringUtils.isNotEmpty(dateStringMax)) {
                noDates = false;
                dateMax = Date.valueOf(dateStringMax);
            } else {
                dateMax = null;
            }
            if(StringUtils.isNotEmpty(dateStringMin)) {
                noDates = false;
                dateMin = Date.valueOf(dateStringMin);
            } else {
                dateMin = null;
            }
            int from = USERS_AMOUNT_ON_ONE_PAGE * (page - 1);
            if(noDates) {
                users = searchDAO.findUsers(name, surname, country, city, from, USERS_AMOUNT_ON_ONE_PAGE);
            } else {
                users = searchDAO.findUsers(name, surname, country, city, dateMin, dateMax, from, USERS_AMOUNT_ON_ONE_PAGE);
            }
            /*
            if(StringUtils.isEmpty(country) && StringUtils.isEmpty(city) && StringUtils.isEmpty(dateStringMin) && StringUtils.isEmpty(dateStringMax)) {
               users = searchDAO.findUsersByName(name,surname);
            } else {
                users = null;
            }   */
            return users;
        } catch(PoolConnectionException | DAOException e ) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(connection);
        }
    }

    public static List<WishesOwner> findWishes(String wishesInput, int page)
            throws LogicException {
        Connection connection = null;
        try {
            connection = Pool.getPool().getConnection();
            SearchDAO searchDAO = new SearchDAO(connection);
            List<WishesOwner> users;
            String[] wishes = wishesInput.split("[\\p{Blank}]");
            int from = WISHES_AMOUNT_ON_ONE_PAGE * (page - 1);
            users = searchDAO.findWishes(wishes,from,WISHES_AMOUNT_ON_ONE_PAGE);
            return users;
        } catch(PoolConnectionException | DAOException e ) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(connection);
        }
    }
    public static int countWishes(String wishesInput)
            throws LogicException {
        Connection connection = null;
        try {
            connection = Pool.getPool().getConnection();
            SearchDAO searchDAO = new SearchDAO(connection);
            String[] wishes = wishesInput.split("[\\p{Blank}]");
            return searchDAO.countWishes(wishes);
        } catch(PoolConnectionException | DAOException e ) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(connection);
        }
    }

    public static int countBirthdayMen() throws LogicException {
        Connection connection = null;
        try {
            connection = Pool.getPool().getConnection();
            SearchDAO searchDAO = new SearchDAO(connection);
            int count = searchDAO.countUsersByBirthDate(Date.valueOf(LocalDate.now()));
            return count;
        } catch(PoolConnectionException | DAOException e ) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(connection);
        }
    }
    public static List<User> findBirthdayMen(int page)
            throws LogicException {
        Connection connection = null;
        try {
            connection = Pool.getPool().getConnection();
            SearchDAO searchDAO = new SearchDAO(connection);
            List<User> users;
            int from = USERS_AMOUNT_ON_ONE_PAGE * (page - 1);
            users = searchDAO.findUsersByBirthDate(Date.valueOf(LocalDate.now()),from,USERS_AMOUNT_ON_ONE_PAGE);
            return users;
        } catch(PoolConnectionException | DAOException e ) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(connection);
        }
    }
}
