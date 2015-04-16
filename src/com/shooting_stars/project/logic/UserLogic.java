package com.shooting_stars.project.logic;

import com.shooting_stars.project.dao.UserDAO;
import com.shooting_stars.project.entity.UserInfo;
import com.shooting_stars.project.exception.DAOException;
import com.shooting_stars.project.exception.HashingException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.exception.PoolConnectionException;
import com.shooting_stars.project.hashing.MD5Hashing;
import com.shooting_stars.project.pool.Pool;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.sql.Connection;
import java.util.regex.Pattern;

public class UserLogic {
    public static UserInfo getUserInfo(int userId) throws LogicException {
        Connection connection = null;
        try {
            connection = Pool.getPool().getConnection();
            UserDAO userDAO = new UserDAO(connection);
            UserInfo newUserInfo =  userDAO.findUserInfoByUserId(userId);
            return newUserInfo;
        }  catch(PoolConnectionException | DAOException e ) {
            throw new LogicException(e.getCause());
        }  finally {
            Pool.getPool().returnConnection(connection);
        }
    }

    public static void changePhotoURL(int userId, String destPath,String fileName, File photo) throws LogicException {
        System.out.println("Src File name: " + photo);
        System.out.println("path:    " + destPath);
        String extension = FilenameUtils.getExtension(fileName);
        fileName = userId + "." + extension;
        System.out.println("Dst File name: " + fileName);
        File directory = new File(destPath);
        boolean nameEquals = false;
        for (File file : directory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                Pattern pattern = Pattern.compile(userId + "(\\.)\\p{Lower}+");
                return Pattern.matches(userId + "(\\.)\\p{Lower}+", pathname.getName());
            }
        })
                ) {
            if (file.getName().equals(fileName)) {
                nameEquals = true;
            }
            file.delete();
        }
        File destFile = new File(destPath, fileName);
        try {
            FileUtils.copyFile(photo, destFile);
            //чтобы не потерялось между redeploy
            //destFile = new File("E:/CourseProject/Shooting-Stars/web/img/userPhoto",fileName);
            //FileUtils.copyFile(photo,destFile);
        } catch (IOException e) {
            throw new LogicException("Problem with adding photo to folder");
        }
        if (!nameEquals) {

            Connection connection = null;
            try {
                connection = Pool.getPool().getConnection();
                UserDAO userDAO = new UserDAO(connection);
                userDAO.updatePhotoName(userId, fileName);
            } catch(PoolConnectionException | DAOException e ) {
                throw new LogicException(e.getCause());
            } finally {
                Pool.getPool().returnConnection(connection);
            }
        }
        System.out.println(fileName);
    }

    public static String getUserStatus(int userId) throws LogicException {
        Connection connection = null;
        try {
            connection = Pool.getPool().getConnection();
            UserDAO userDAO = new UserDAO(connection);
            String status = userDAO.getUserStatus(userId);
            return status;
        }  catch(PoolConnectionException | DAOException e ) {
            throw new LogicException(e.getCause());
        }  finally {
            Pool.getPool().returnConnection(connection);
        }
    }

    public static void setUserStatus(int userId, int userStatusId) throws LogicException {
        Connection connection = null;
        try {
            connection = Pool.getPool().getConnection();
            UserDAO userDAO = new UserDAO(connection);
            userDAO.setUserStatus(userId, userStatusId);
        }  catch(PoolConnectionException | DAOException e ) {
            throw new LogicException(e.getCause());
        }  finally {
            Pool.getPool().returnConnection(connection);
        }
    }

    public static void updateUserInfo(int userId, UserInfo userInfo) throws LogicException {
        Connection connection = null;
        try {
            connection = Pool.getPool().getConnection();
            UserDAO userDAO = new UserDAO(connection);
            userDAO.updateUserInfo(userId, userInfo);
        }  catch(PoolConnectionException | DAOException e ) {
            throw new LogicException(e.getCause());
        }  finally {
            Pool.getPool().returnConnection(connection);
        }
    }

    public static String getUserAbilities(int userId) throws LogicException {
        Connection connection = null;
        try {
            connection = Pool.getPool().getConnection();
            UserDAO userDAO = new UserDAO(connection);
            String abilities = userDAO.findAbilitiesByUserId(userId);
            return abilities;
        }  catch(PoolConnectionException | DAOException e ) {
            throw new LogicException(e.getCause());
        }  finally {
            Pool.getPool().returnConnection(connection);
        }
    }

    public static void updateUserAbilities(int userId, String abilities) throws LogicException {
        Connection connection = null;
        try {
            connection = Pool.getPool().getConnection();
            UserDAO userDAO = new UserDAO(connection);
            userDAO.updateUserAbilities(userId, abilities);
        }  catch(PoolConnectionException | DAOException e ) {
            throw new LogicException(e.getCause());
        }  finally {
            Pool.getPool().returnConnection(connection);
        }
    }

    public static boolean passwordsEqual(int userId, String password) throws LogicException {
        Connection connection = null;
        try {
            connection = Pool.getPool().getConnection();
            UserDAO userDAO = new UserDAO(connection);
            return MD5Hashing.hashingPassword(password).equals(userDAO.getPasswordByUserId(userId));
        }  catch(PoolConnectionException | DAOException | HashingException e ) {
            throw new LogicException(e.getCause());
        }  finally {
            Pool.getPool().returnConnection(connection);
        }
    }

    public static void changePassword(int userId, String password) throws LogicException {
        Connection connection = null;
        try {
            connection = Pool.getPool().getConnection();
            UserDAO userDAO = new UserDAO(connection);
            userDAO.updatePassword(userId, MD5Hashing.hashingPassword(password));
        }  catch(PoolConnectionException | DAOException | HashingException e ) {
            throw new LogicException(e.getCause());
        }  finally {
            Pool.getPool().returnConnection(connection);
        }
    }
}
