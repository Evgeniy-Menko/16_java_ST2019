package by.menko.finalproject.dao.pool;

import by.menko.finalproject.dao.exception.FatalError;
import by.menko.finalproject.dao.exception.PersonalException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public final class ConnectionPool {

    private static Logger logger = LogManager.getLogger();

    private MySqlResources resources;
    private static ConnectionPool instance;
    private BlockingDeque<ProxyConnection> availableConnections;
    private BlockingDeque<ProxyConnection> usedConnections;
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean create = new AtomicBoolean(false);

    private ConnectionPool() {
        resources = new MySqlResources();
        try {
            Class.forName(resources.getDriverClass());
        } catch (ClassNotFoundException e) {
            throw new FatalError(e);
        }
        init();
    }

    private void init() {
        try {

            availableConnections = new LinkedBlockingDeque<>(resources.getMaxSize());
            usedConnections = new LinkedBlockingDeque<>();
            for (int counter = 0; counter < resources.getStartSize(); counter++) {
                availableConnections.put(createConnection());
            }
        } catch (InterruptedException | SQLException e) {
            logger.debug("Error created  pool.");
            Thread.currentThread().interrupt();
        }

    }

    private ProxyConnection createConnection() throws SQLException {
        return new ProxyConnection(DriverManager.getConnection(resources.getUrl(), resources.getUser(), resources.getPassword()));
    }

    public static ConnectionPool getInstance() {
        if (!create.get()) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new ConnectionPool();
                    create.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public Connection takeConnection() throws PersonalException {
        ProxyConnection connection = null;

        while (connection == null) {
            try {
                if (!availableConnections.isEmpty()) {
                    connection = availableConnections.take();
                    if (!connection.isValid(resources.getCheckConnectionTimeout())) {
                        connection.getConnection().close();
                        connection = null;
                    }
                } else if (usedConnections.size() < resources.getMaxSize()) {
                    connection = createConnection();
                } else {
                    logger.error("The limit of number of database connections is exceeded");
                    throw new PersonalException();
                }
            } catch (InterruptedException | SQLException e) {
                logger.error("It is impossible to connect to a database", e);
                Thread.currentThread().interrupt();
                throw new PersonalException(e);
            }
        }
        try {
            usedConnections.put(connection);
        } catch (InterruptedException e) {
            logger.debug(String.format("Connection was received from pool. Current pool size: %d used connections; %d free connection", usedConnections.size(), availableConnections.size()));
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    void releaseConnection(ProxyConnection connection) {
        try {
            if (connection.isValid(resources.getCheckConnectionTimeout())) {
                connection.clearWarnings();
                connection.setAutoCommit(true);
                usedConnections.remove(connection);
                availableConnections.offer(connection);

                String message = String.format("Connection was returned into pool. Current pool size: %d used connections; %d free connection", usedConnections.size(), availableConnections.size());
                logger.debug(message);
            }
        } catch (SQLException e1) {
            logger.warn("It is impossible to return database connection into pool", e1);
            try {
                connection.getConnection().close();
            } catch (SQLException e) {
                logger.warn("error closed connection.");
            }
        }
    }


    public void closePool() {
        ProxyConnection connection;
        for (int i = 0; i < availableConnections.size(); i++) {
            try {
                connection = availableConnections.take();
                connection.realClose();
            } catch (InterruptedException | SQLException e) {
                logger.debug("Error closed connection.");
                Thread.currentThread().interrupt();
            }
        }
        availableConnections.clear();
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.error("Cant deregister SQL driver");
            }
        }
    }
}
