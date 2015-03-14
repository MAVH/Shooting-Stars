package com.shooting_stars.project.logic;

import com.shooting_stars.project.dao.UserDAO;
import com.shooting_stars.project.dao.WishDAO;
import com.shooting_stars.project.entity.Wish;
import com.shooting_stars.project.exception.DAOException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.exception.PoolConnectionException;
import com.shooting_stars.project.pool.Pool;
import com.shooting_stars.project.validation.Validation;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * Created by Пользователь on 14.03.2015.
 */
public class WishLogic {
    public static void addWishes(int userId,String[] wishes) throws LogicException {
        Connection connection = null;
        try {
            connection = Pool.getPool().getConnection();
            WishDAO wishDAO = new WishDAO(connection);
            wishDAO.insertWishes(userId,deleteEmptyFields(wishes));
        } catch(PoolConnectionException | DAOException e ) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(connection);
        }

    }
    public static ArrayList<String> deleteEmptyFields(String[] values) {
        ArrayList<String> newArray = new ArrayList<String>();
        for(String i: values) {
            if(!Validation.isEmpty(i)) {
                 newArray.add(i);
            }
        }
        return newArray;
    }
    public static ArrayList<Wish> getAllWishes(int userId) throws LogicException {
        Connection connection = null;
        try {
            connection = Pool.getPool().getConnection();
            WishDAO wishDAO = new WishDAO(connection);
            ArrayList<Wish> wishes = wishDAO.getWishes(userId);
            //get candidates
            return wishes;
        } catch(PoolConnectionException | DAOException e ) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(connection);
        }

    }
    public static void deleteWish(int wishId) throws LogicException {
        Connection connection = null;
        try {
            connection = Pool.getPool().getConnection();
            WishDAO wishDAO = new WishDAO(connection);
            wishDAO.deleteWish(wishId);
        } catch(PoolConnectionException | DAOException e ) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(connection);
        }

    }
}
