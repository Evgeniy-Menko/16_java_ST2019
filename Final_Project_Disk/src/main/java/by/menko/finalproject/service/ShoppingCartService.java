package by.menko.finalproject.service;

import by.menko.finalproject.entity.Disk;
import by.menko.finalproject.entity.ShoppingCart;
import by.menko.finalproject.dao.exception.PersonalException;

import java.util.List;
import java.util.Map;

public interface ShoppingCartService extends Service {
    void addShoppingCart(final String idDisk, final Integer idUser) throws PersonalException;

    Map<ShoppingCart, Disk> getShoppingCart(final Integer idUser) throws PersonalException;

    void deleteDisk(final String idDisk, final Integer idUser) throws PersonalException;

    void deleteAll(final Integer idUser) throws PersonalException;

    List<ShoppingCart> getAllDiskFromShopCart(final Integer idUser) throws PersonalException;
}

