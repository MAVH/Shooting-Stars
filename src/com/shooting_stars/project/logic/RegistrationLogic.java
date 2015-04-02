package com.shooting_stars.project.logic;

import com.shooting_stars.project.dao.UserDAO;
import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.entity.UserToBeRegistered;
import com.shooting_stars.project.exception.*;
import com.shooting_stars.project.pool.Pool;
import java.sql.Connection;

public class RegistrationLogic {
    public static User addUser(UserToBeRegistered user) throws RegistrationException {
        Connection connection = null;
        try {
            connection = Pool.getPool().getConnection();
            UserDAO userDAO = new UserDAO(connection);
            user.hashPassword();
            User newUser =  userDAO.registerUser(user);
            WishLogic.addWishes(newUser.getUserId(), user.getWishes());
            return newUser;
        }  catch(PoolConnectionException | HashingException | LogicException | DAOException e ) {
            throw new RegistrationException(e.getCause());
        }  finally {
            Pool.getPool().returnConnection(connection);
        }

    }
    public  static boolean userLoginExists(String login) throws RegistrationException{
        Connection connection = null;
        try {
            connection = Pool.getPool().getConnection();
            UserDAO userDAO = new UserDAO(connection);
            return userDAO.checkUserLoginExistence(login);
        }  catch(PoolConnectionException | DAOException e ) {
            throw new RegistrationException(e.getCause());
        }  finally {
            Pool.getPool().returnConnection(connection);
        }
    }
}
