package by.menko.finalproject.dao;

import by.menko.finalproject.entity.ShoppingCart;
import by.menko.finalproject.exception.PersonalException;

import java.util.List;

public interface ShoppingCartDao extends Dao<ShoppingCart> {
    List<ShoppingCart> readAll(Integer id) throws PersonalException;

    void deleteDisk(Integer idDisk, Integer idUser) throws PersonalException;
}
