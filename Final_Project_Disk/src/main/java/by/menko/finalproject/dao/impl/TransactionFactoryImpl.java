package by.menko.finalproject.dao.impl;

import by.menko.finalproject.dao.Transaction;
import by.menko.finalproject.dao.TransactionFactory;
import by.menko.finalproject.dao.pool.ConnectionPool;
import by.menko.finalproject.dao.exception.PersonalException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.Connection;
import java.sql.SQLException;



public class TransactionFactoryImpl implements TransactionFactory {
    private static Logger logger = LogManager.getLogger();
    private Connection connection;

    public TransactionFactoryImpl() throws PersonalException {
        connection = ConnectionPool.getInstance().takeConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
           		logger.error("It is impossible to turn off autocommiting for database connection", e);
            throw new PersonalException(e);
        }
    }

    @Override
    public Transaction createTransaction() {
        return new TransactionImpl(connection);
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException ignored) {
            logger.error("Error closed connection");
        }
    }
}
