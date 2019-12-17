package by.menko.finalproject.service.impl;


import by.menko.finalproject.dao.Transaction;
import by.menko.finalproject.dao.TransactionFactory;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.dao.exception.PersonalException;
import by.menko.finalproject.service.Service;
import by.menko.finalproject.service.ServiceFactory;


public class ServiceFactoryImpl implements ServiceFactory {

    private TransactionFactory factory;

    public ServiceFactoryImpl( final TransactionFactory factory)  {
        this.factory = factory;
    }

    private ServiceImpl getService(final TypeServiceAndDao key) throws PersonalException {
        ServiceImpl service ;
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
            case FILE:
                service = new FileServiceImpl();
                break;
            default:
                throw new PersonalException(String.format("Incorrect type service %s",key));
        }
        return service;
    }

    @Override
    public <T extends Service> T createService(final TypeServiceAndDao key) throws PersonalException {
        ServiceImpl value = getService(key);
        Transaction transaction = factory.createTransaction();
        value.setTransaction(transaction);
        return (T) value;

    }

    @Override
    public void close() {
        factory.close();
    }
}
