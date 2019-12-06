package by.menko.finalproject.service.impl;


import by.menko.finalproject.dao.Transaction;
import by.menko.finalproject.dao.TransactionFactory;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.exception.PersonalException;
import by.menko.finalproject.service.Service;
import by.menko.finalproject.service.ServiceFactory;


public class ServiceFactoryImpl implements ServiceFactory {

    private TransactionFactory factory;

    public ServiceFactoryImpl(TransactionFactory factory) throws PersonalException {
        this.factory = factory;
    }

    private ServiceImpl getService(TypeServiceAndDao key) {
        ServiceImpl service = null;
        switch (key) {
            case USER:
                service = new UserServiceImpl();
                break;
            case SHOPPING_CART:
                service = new ShoppingCartServiceImpl();
                break;
            case DISK:
                service = new DiskServiceImpl();
                break;
            case COMPLAINT:
                service = new ComplaintServiceImpl();
                break;
            case COMMENT:
                service = new CommentServiceImpl();
                break;
            case CATALOG:
                service = new CatalogServiceImpl();
                break;
            default:
                // return null;
        }
        return service;
    }

    @Override
    public <T extends Service> T createService(TypeServiceAndDao key) throws PersonalException {
        ServiceImpl value = getService(key);
        if (value != null) {
            Transaction transaction = factory.createTransaction();
            value.setTransaction(transaction);
            return (T) value;
        } else {
            throw new PersonalException();
        }
    }

    @Override
    public void close() {
        factory.close();
    }
}
