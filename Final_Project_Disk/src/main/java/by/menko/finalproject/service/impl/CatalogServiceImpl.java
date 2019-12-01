package by.menko.finalproject.service.impl;

import by.menko.finalproject.dao.CatalogDao;
import by.menko.finalproject.entity.Catalog;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.exception.PersonalException;
import by.menko.finalproject.service.CatalogService;

import java.util.List;


public class CatalogServiceImpl extends ServiceImpl implements CatalogService {
    @Override
    public List<Catalog> getCatalog() throws PersonalException {
        CatalogDao dao = transaction.createDao(TypeServiceAndDao.CATALOG);
        return dao.read();
    }
}
