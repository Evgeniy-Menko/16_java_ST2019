package by.menko.finalproject.service.impl;

import by.menko.finalproject.dao.CatalogDao;
import by.menko.finalproject.entity.Catalog;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.exception.PersonalException;
import by.menko.finalproject.service.DiskService;

import java.util.List;

public class DiskServiceImpl extends ServiceImpl implements DiskService {
    @Override
    public List<Catalog> getCatalog() throws PersonalException {
        CatalogDao dao = transaction.createDao(TypeServiceAndDao.CATALOG);
        return dao.read();
    }
}
