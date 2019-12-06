package by.menko.finalproject.service;

import by.menko.finalproject.entity.Disk;
import by.menko.finalproject.entity.ShoppingCart;
import by.menko.finalproject.exception.PersonalException;

import java.util.List;

public interface ShoppingCartService extends Service {
    void addShoppingCart(String idDisk, Integer idUser) throws PersonalException;

    List<Disk> getShoppingCart(Integer idUser) throws PersonalException;

    void deleteDisk(String idDisk, Integer idUser) throws PersonalException;

    void deleteAll(Integer idUser) throws PersonalException;

    List<ShoppingCart> getAllDiskFromShopCart(Integer idUser) throws PersonalException;
}

