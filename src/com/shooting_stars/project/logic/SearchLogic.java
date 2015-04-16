package com.shooting_stars.project.logic;

import com.shooting_stars.project.dao.SearchDAO;
import com.shooting_stars.project.dao.UserDAO;
import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.exception.DAOException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.exception.PoolConnectionException;
import com.shooting_stars.project.pool.Pool;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchLogic {
    public static List<User> findUsers(String name, String surname, String country, String city, String dateStringMin, String dateStringMax)
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
            if(noDates) {
                users = searchDAO.findUsers(name, surname, country, city);
            } else {
                users = searchDAO.findUsers(name, surname, country, city, dateMin, dateMax);
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
}
