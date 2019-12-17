package by.menko.finalproject.dao;

import by.menko.finalproject.entity.ShoppingCart;
import by.menko.finalproject.dao.exception.PersonalException;

import java.util.List;

public interface ShoppingCartDao extends Dao<ShoppingCart> {
    List<ShoppingCart> readAll(final Integer id) throws PersonalException;

    void deleteAll(final Integer idUser) throws PersonalException;
}
