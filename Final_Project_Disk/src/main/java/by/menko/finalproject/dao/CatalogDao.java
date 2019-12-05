package by.menko.finalproject.dao;

import by.menko.finalproject.entity.Catalog;
import by.menko.finalproject.exception.PersonalException;

import java.util.List;
import java.util.Optional;


public interface CatalogDao extends Dao{

   List<Catalog> read() throws PersonalException;

   Optional<Integer> read(String genre, String type) throws PersonalException;

}
