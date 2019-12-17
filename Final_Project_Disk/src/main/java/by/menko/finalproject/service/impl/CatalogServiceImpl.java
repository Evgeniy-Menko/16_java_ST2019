package by.menko.finalproject.service.impl;

import by.menko.finalproject.dao.CatalogDao;
import by.menko.finalproject.entity.Catalog;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.dao.exception.PersonalException;
import by.menko.finalproject.service.CatalogService;


import java.util.List;


public class CatalogServiceImpl extends ServiceImpl implements CatalogService {

    @Override
    public List<Catalog> getCatalog() throws PersonalException {
        try {
            CatalogDao dao = transaction.createDao(TypeServiceAndDao.CATALOG);
            transaction.commit();
            return dao.read();
        } catch (PersonalException e) {
            transaction.rollback();
            throw new PersonalException("Erorr CatalogDao getCatalog", e);
        }
    }
}
