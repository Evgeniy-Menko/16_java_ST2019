package by.menko.finalproject.dao.impl;

import by.menko.finalproject.dao.Transaction;
import by.menko.finalproject.dao.TransactionFactory;
import by.menko.finalproject.dao.pool.ConnectionPool;
import by.menko.finalproject.exception.PersonalException;


import java.sql.Connection;
import java.sql.SQLException;

//import org.apache.log4j.Logger;

public class TransactionFactoryImpl implements TransactionFactory {
    //private static Logger logger = Logger.getLogger(TransactionFactoryImpl.class);
    private Connection connection;

    public TransactionFactoryImpl() throws PersonalException {
        connection = ConnectionPool.getInstance().takeConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            //		logger.error("It is impossible to turn off autocommiting for database connection", e);
            //throw new PersistentException(e);
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
        }
    }
}
