package com.shooting_stars.project.logic;

import com.shooting_stars.project.dao.UserDAO;
import com.shooting_stars.project.dao.WishDAO;
import com.shooting_stars.project.entity.User;
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
            int wishId;
            User user = null;
            for (Wish wish : wishes) {
                 wishId = wish.getWishId();
                 user = wishDAO.getMakingUser(wishId);
                 if(user != null) {
                     wish.setCandidate(user);
                 } else {
                     wish.setCandidates(wishDAO.getUsersConsidered(wishId));
                 }
            }
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
    public static void cancelWishMaking(int wishId) throws LogicException {
        Connection connection = null;
        try {
            connection = Pool.getPool().getConnection();
            WishDAO wishDAO = new WishDAO(connection);
            wishDAO.deleteMakingUser(wishId);
        } catch(PoolConnectionException | DAOException e ) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(connection);
        }
    }
    public static void cancelApplication(int wishId,int userId) throws LogicException {
        Connection connection = null;
        try {
            connection = Pool.getPool().getConnection();
            WishDAO wishDAO = new WishDAO(connection);
            wishDAO.deleteUserConsidered(wishId,userId);
        } catch(PoolConnectionException | DAOException e ) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(connection);
        }
    }
    public static void cancelAllUsersConsidered(int wishId) throws LogicException {
        Connection connection = null;
        try {
            connection = Pool.getPool().getConnection();
            WishDAO wishDAO = new WishDAO(connection);
            wishDAO.deleteAllUsersConsidered(wishId);
        } catch(PoolConnectionException | DAOException e ) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(connection);
        }
    }
    public static void takeApplication(int wishId, int userId) throws LogicException {
        Connection connection = null;
        try {
            connection = Pool.getPool().getConnection();
            WishDAO wishDAO = new WishDAO(connection);
            wishDAO.insertMakingUser(wishId,userId);
            wishDAO.deleteAllUsersConsidered(wishId);
        } catch(PoolConnectionException | DAOException e ) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(connection);
        }
    }
}
