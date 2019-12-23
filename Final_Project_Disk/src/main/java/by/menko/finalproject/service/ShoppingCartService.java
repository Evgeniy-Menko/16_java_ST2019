package by.menko.finalproject.service;

import by.menko.finalproject.entity.Disk;
import by.menko.finalproject.entity.ShoppingCart;
import by.menko.finalproject.dao.exception.PersonalException;

import java.util.List;
import java.util.Map;

public interface ShoppingCartService extends Service {
    /**
     * Add disk by id disk to shopping cart by id user.
     * @param idDisk id disk.
     * @param idUser id user.
     * @throws PersonalException sql exception or number format for id disk.
     */
    void addShoppingCart(final String idDisk, final Integer idUser) throws PersonalException;

    /**
     * Get map with disks and time added to shopping cart.
     * @param idUser id user.
     * @return map.
     * @throws PersonalException sql exception.
     */
    Map<ShoppingCart, Disk> getShoppingCart(final Integer idUser) throws PersonalException;

    /**
     * delete disk from shopping cart.
     * @param idDisk id user.
     * @param idUser id disk.
     * @throws PersonalException sql exception or number format for id disk.
     */
    void deleteDisk(final String idDisk, final Integer idUser) throws PersonalException;

    /**
     * clear all shopping cart  by id user.
     * @param idUser id user.
     * @throws PersonalException sql exception.
     */
    void deleteAll(final Integer idUser) throws PersonalException;

    /**
     * get All from shoppping cart .
     * @param idUser id user.
     * @return list with disks that found.
     * @throws PersonalException sql exception.
     */
    List<ShoppingCart> getAllDiskFromShopCart(final Integer idUser) throws PersonalException;
}

