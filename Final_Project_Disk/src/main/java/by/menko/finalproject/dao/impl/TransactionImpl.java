package by.menko.finalproject.dao.impl;

import by.menko.finalproject.dao.Dao;
import by.menko.finalproject.dao.Transaction;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.exception.PersonalException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

public class TransactionImpl implements Transaction {
    //  private static Logger logger = Logger.getLogger(String.valueOf(TransactionImpl.class));


    private Connection connection;

    TransactionImpl(Connection connection) {
        this.connection = connection;
    }


    private BaseDao getDao(TypeServiceAndDao key) throws PersonalException {
        BaseDao dao ;
        switch (key) {
            case USER:
                dao = new UserDaoImpl();
                break;
            case SHOPPING_CART:
                dao = new ShoppingCartDaoImpl();
                break;
            case DISK:
                dao = new DiskDaoImpl();
                break;
            case COMPLAINT:
                dao = new ComplaintDaoImpl();
                break;
            case COMMENT:
                dao = new CommentDaoImpl();
                break;
            case CATALOG:
                dao = new CatalogDaoImpl();
                break;
            default:
                throw new PersonalException();
        }
        return dao;
    }

    @Override
    public <T extends Dao<?>> T createDao(TypeServiceAndDao key) throws PersonalException {
        BaseDao dao = getDao(key);
        dao.setConnection(connection);
        return (T) dao;

    }


    @Override
    public void commit() throws PersonalException {
        try {
            connection.commit();
        } catch (SQLException e) {
            //logger.error("It is impossible to commit transaction", e);
            throw new PersonalException(e);
        }
    }

    @Override
    public void rollback() throws PersonalException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            //	logger.error("It is impossible to rollback transaction", e);
             throw new PersonalException(e);
        }
    }
}
