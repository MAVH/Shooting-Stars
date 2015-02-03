package com.shooting_stars.project.logic;

import com.shooting_stars.project.dao.UserDAO;
import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.entity.UserToBeRegistered;
import com.shooting_stars.project.exception.DAOException;
import com.shooting_stars.project.exception.HashingException;
import com.shooting_stars.project.exception.PoolConnectionException;
import com.shooting_stars.project.exception.RegistrationException;
import com.shooting_stars.project.pool.Pool;
import org.apache.log4j.Logger;
import java.sql.Connection;

public class RegistrationLogic {
    static Logger logger = Logger.getLogger(LoginLogic.class);

    public static User addUser(UserToBeRegistered user) throws RegistrationException {
        Connection connection = null;
        try {
            connection = Pool.getPool().getConnection();
            UserDAO userDAO = new UserDAO(connection);
            user.hashPassword();
            return userDAO.registerUser(user);
        }  catch(PoolConnectionException | HashingException | DAOException e ) {
            throw new RegistrationException(e.getCause());
        }  finally {
            Pool.getPool().returnConnection(connection);
        }
    }
}
