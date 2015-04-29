package com.shooting_stars.project.logic;

import com.shooting_stars.project.dao.WishDAO;
import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.entity.Wish;
import com.shooting_stars.project.exception.DAOException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.exception.PoolConnectionException;
import com.shooting_stars.project.pool.Pool;
import com.shooting_stars.project.validation.Validation;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;


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
    public static boolean deleteWish(int wishId) throws LogicException {
        Connection connection = null;
        try {
            connection = Pool.getPool().getConnection();
            WishDAO wishDAO = new WishDAO(connection);
            if(wishDAO.getMakingUser(wishId) == null) {
                wishDAO.deleteWish(wishId);
                return true;
            } else {
                return false;
            }
        } catch(PoolConnectionException | DAOException e ) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(connection);
        }

    }
    public static int cancelWishMaking(int wishId) throws LogicException {
        Connection connection = null;
        try {
            connection = Pool.getPool().getConnection();
            WishDAO wishDAO = new WishDAO(connection);
            int userId = wishDAO.getWishMakerUserIdByWishId(wishId);
            wishDAO.deleteUserWishMaker(wishId);
            return userId;
        } catch(PoolConnectionException | DAOException e ) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(connection);
        }
    }
    public static int cancelApplication(int wishId,int userId) throws LogicException {
        Connection connection = null;
        try {
            connection = Pool.getPool().getConnection();
            WishDAO wishDAO = new WishDAO(connection);
            wishDAO.deleteUserConsidered(wishId,userId);
            return wishDAO.getWishOwnerUserIdByWishId(wishId);
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
            wishDAO.insertUserWishMaker(wishId, userId);
            wishDAO.deleteAllUsersConsidered(wishId);
        } catch(PoolConnectionException | DAOException e ) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(connection);
        }
    }
    public static int makeApplication(int wishId, int userId) throws LogicException {
        Connection connection = null;
        try {
            connection = Pool.getPool().getConnection();
            WishDAO wishDAO = new WishDAO(connection);
            wishDAO.insertUserConsidered(wishId,userId);
            return wishDAO.getWishOwnerUserIdByWishId(wishId);
        } catch(PoolConnectionException | DAOException e ) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(connection);
        }
    }
    public static int markWishMade(int wishId) throws LogicException {
        Connection connection = null;
        try {
            connection = Pool.getPool().getConnection();
            WishDAO wishDAO = new WishDAO(connection);
            int userId = wishDAO.getWishMakerUserIdByWishId(wishId);
            wishDAO.changeFulfilledWishStatus(wishId, Date.valueOf(LocalDate.now()));
            return userId;
        } catch(PoolConnectionException | DAOException e ) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(connection);
        }
    }
    public static ArrayList<Wish> getFulfilledWishesByOwnerId(int userId) throws LogicException {
        Connection connection = null;
        try {
            connection = Pool.getPool().getConnection();
            WishDAO wishDAO = new WishDAO(connection);
            ArrayList<Wish> wishes = wishDAO.getFulfilledWishesByOwnerUserId(userId);
            return wishes;
        } catch(PoolConnectionException | DAOException e ) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(connection);
        }
    }
    public static ArrayList<Wish> getWishesByMakerId(int userId) throws LogicException {
        Connection connection = null;
        try {
            connection = Pool.getPool().getConnection();
            WishDAO wishDAO = new WishDAO(connection);
            ArrayList<Wish> wishes = wishDAO.getWishesByMakerUserId(userId);
            return wishes;
        } catch(PoolConnectionException | DAOException e ) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(connection);
        }
    }
    public static int getCurrentWishesAmount(int userId) throws LogicException {
        Connection connection = null;
        try {
            connection = Pool.getPool().getConnection();
            WishDAO wishDAO = new WishDAO(connection);
            int amount = wishDAO.countCurrentWishesByUserId(userId);
            return amount;
        } catch(PoolConnectionException | DAOException e ) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(connection);
        }
    }
}
