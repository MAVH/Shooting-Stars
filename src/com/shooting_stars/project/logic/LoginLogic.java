package com.shooting_stars.project.logic;

import com.shooting_stars.project.dao.UserDAO;
import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.exception.DAOException;
import com.shooting_stars.project.exception.HashingException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.exception.PoolConnectionException;
import com.shooting_stars.project.hashing.MD5Hashing;
import com.shooting_stars.project.pool.Pool;
import org.apache.log4j.Logger;
import java.sql.Connection;

public class LoginLogic {
    static Logger logger = Logger.getLogger(LoginLogic.class);

    public static User checkUser(String login, String password) throws LogicException {
        Connection connection = null;
        try {
            connection = Pool.getPool().getConnection();
            UserDAO userDAO = new UserDAO(connection);
            return userDAO.findUserByLoginAndPassword(login, MD5Hashing.hashingPassword(password));
        } catch(PoolConnectionException | DAOException | HashingException e ) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(connection);
        }
    }
}
