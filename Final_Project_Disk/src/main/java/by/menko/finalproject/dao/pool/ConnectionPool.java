package by.menko.finalproject.dao.pool;

import by.menko.finalproject.exception.PersonalException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

final public class ConnectionPool {

    private static Logger logger = LogManager.getLogger();

    private MySqlResources resources;
    private static ConnectionPool instance;
    private BlockingDeque<ProxyConnection> availableConnections;
    private BlockingDeque<ProxyConnection> usedConnections;
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean create = new AtomicBoolean(false);

    private ConnectionPool() throws PersonalException {
        resources = new MySqlResources();
        try {
            Class.forName(resources.getDriverClass());
        } catch (ClassNotFoundException e) {
          throw  new PersonalException(e);
        }
        init();
    }

    public void init() {
        try {

            availableConnections = new LinkedBlockingDeque<>(resources.getMaxSize());
            usedConnections = new LinkedBlockingDeque<>();
            for (int counter = 0; counter < resources.getStartSize(); counter++) {
                availableConnections.put(createConnection());
            }
        } catch (InterruptedException | SQLException e) {
            logger.debug("Error created  connection.");
        }

    }

    private ProxyConnection createConnection() throws SQLException {
        return new ProxyConnection(DriverManager.getConnection(resources.getUrl(), resources.getUser(), resources.getPassword()));
    }

    public static ConnectionPool getInstance() throws PersonalException {
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

    public Connection takeConnection() {
        ProxyConnection connection = null;

        while (connection == null) {
            try {
                if (!availableConnections.isEmpty()) {
                    connection = availableConnections.take();
                    if (!connection.isValid(resources.getCheckConnectionTimeout())) {
                        try {
                            connection.getConnection().close();
                        } catch (SQLException ignored) {
                        }
                        connection = null;
                    }
                } else if (usedConnections.size() < resources.getMaxSize()) {
                    connection = createConnection();
                } else {
                    logger.error("The limit of number of database connections is exceeded");
                    // throw new PersistentException();
                }
            } catch (InterruptedException | SQLException e) {
                logger.error("It is impossible to connect to a database", e);
                // throw new PersistentException(e);
            }
        }
        try {
            usedConnections.put(connection);
        } catch (InterruptedException e) {
            //log
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public void releaseConnection(ProxyConnection connection) {
        try {
            if (connection.isValid(resources.getCheckConnectionTimeout())) {
                connection.clearWarnings();
                connection.setAutoCommit(true);
                usedConnections.remove(connection);
                availableConnections.offer(connection);

                logger.debug(String.format("Connection was returned into pool. Current pool size: %d used connections; %d free connection", usedConnections.size(), availableConnections.size()));
            }
        } catch (SQLException e1) {
            logger.warn("It is impossible to return database connection into pool", e1);
            try {
                connection.getConnection().close();
            } catch (SQLException ignored) {
            }
        }
    }


    public void closePool() {
        ProxyConnection connection = null;
        for (int i = 0; i < resources.getMaxSize(); i++) {
            try {
                connection = availableConnections.take();
                connection.realClose();
            } catch (InterruptedException | SQLException e) {
                logger.debug("Error closing connection.");

            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        closePool();
    }
}
