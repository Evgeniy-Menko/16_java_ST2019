package by.menko.finalproject.dao;

import by.menko.finalproject.entity.Catalog;
import by.menko.finalproject.exception.PersonalException;

import java.util.List;


public interface CatalogDao extends Dao{

   List<Catalog> read() throws PersonalException;

}
