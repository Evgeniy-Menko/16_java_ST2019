package by.menko.finalproject.service;

import by.menko.finalproject.entity.Catalog;
import by.menko.finalproject.dao.exception.PersonalException;

import java.util.List;

public interface CatalogService extends Service {
    /**
     * Get catalog .
     * @return list with type and genre.
     * @throws PersonalException .
     */
    List<Catalog> getCatalog() throws PersonalException;
}