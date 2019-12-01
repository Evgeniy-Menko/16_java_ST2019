package by.menko.finalproject.service;

import by.menko.finalproject.entity.Catalog;
import by.menko.finalproject.exception.PersonalException;

import java.util.List;

public interface CatalogService extends Service {
    List<Catalog> getCatalog() throws PersonalException;
}