package com.shooting_stars.project.pool;

import com.shooting_stars.project.exception.PoolConnectionException;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class Pool {
    private static Logger logger = Logger.getLogger(Pool.class);
    private final int POOL_SIZE;
    private static Pool pool = null;
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean poolCreated = new AtomicBoolean(false);
    private BlockingQueue<Connection> resources;

    private Pool() throws SQLException, InterruptedException {
        ResourceBundle bundle = ResourceBundle.getBundle("resources.database", new Locale("en", "EN"));
        String dbPath = bundle.getString("db.path");
        String login = bundle.getString("db.login");
        String pass = bundle.getString("db.password");
        POOL_SIZE = Integer.parseInt(bundle.getString("pool.size"));
        resources = new ArrayBlockingQueue<Connection>(POOL_SIZE);
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        for (int i = 0; i < POOL_SIZE; i++) {
            Connection cn = DriverManager.getConnection(dbPath, login, pass);
            resources.put(cn);
        }
    }

    public void returnConnection(Connection cn) {
        try {
            if(cn != null) {
                resources.put(cn);
            }
        } catch (InterruptedException e) {
            logger.error("InterruptedException(problem with putting element in blocking queue)",e);
        }
    }

    public Connection getConnection() throws PoolConnectionException {
        Connection cn;
        try {
            cn = resources.take();
        } catch (InterruptedException e) {
            throw new PoolConnectionException("InterruptedException(problem with taking element from blocking queue)",e);
        }
        return cn;
    }

    public static Pool getPool() {
        if (!poolCreated.get()) {
            lock.lock();
            try {
                if (pool == null) {
                    pool = new Pool();
                    poolCreated.getAndSet(true);
                }
            } catch (SQLException e) {
                throw new ExceptionInInitializerError("SQlException(problem with getting connection for pool)");
            } catch (InterruptedException e) {
                throw new ExceptionInInitializerError("InterruptedException(problem with putting element in blocking queue for pool)");
            } finally {
                lock.unlock();
            }
        }
        return pool;
    }

    public void closePool() {
        Connection cn;
        try {
            for (int i = 0; i < POOL_SIZE; i++) {
                cn = resources.take();
                if (cn != null) {
                    cn.close();
                }
            }
        } catch (SQLException e) {
            logger.error("SQlException(problem with closing connection)", e);
        } catch (InterruptedException e) {
            logger.error("InterruptedException(problem with putting element in blocking queue)", e);
        }
    }
}
