package by.menko.finalproject.dao.impl;

import by.menko.finalproject.dao.Dao;
import by.menko.finalproject.dao.Transaction;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.exception.PersonalException;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class TransactionImpl implements Transaction {
    //  private static Logger logger = Logger.getLogger(String.valueOf(TransactionImpl.class));

    /* private static Map<Class<? extends Dao<?>>, BaseDao> classes = new ConcurrentHashMap<>();

     static {
         classes.put(AuthorDao.class, AuthorDaoImpl.class);
         classes.put(BookDao.class, BookDaoImpl.class);
         classes.put(UserDao.class, new UserDaoImpl());
         classes.put(ReaderDao.class, ReaderDaoImpl.class);
         classes.put(UsageDao.class, UsageDaoImpl.class);
     }
 */
    private Connection connection;

    public TransactionImpl(Connection connection) {
        this.connection = connection;
    }


    private BaseDao getDao(TypeServiceAndDao key) {
        BaseDao dao = null;
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
                // return null;
        }
        return dao;
    }

    @Override
    public <T extends Dao<?>> T createDao(TypeServiceAndDao key) throws PersonalException {
        BaseDao dao = getDao(key);
        if (dao != null) {
            dao.setConnection(connection);
            return (T) dao;
        } else {
            String message = "";
            throw new PersonalException(message);
        }
    }


    @Override
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            //logger.error("It is impossible to commit transaction", e);
            //throw new PersistentException(e);
        }
    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            //	logger.error("It is impossible to rollback transaction", e);
            // throw new PersistentException(e);
        }
    }
}
