package by.menko.finalproject.service.impl;

import by.menko.finalproject.dao.DiskDao;
import by.menko.finalproject.dao.ShoppingCartDao;
import by.menko.finalproject.entity.Disk;
import by.menko.finalproject.entity.ShoppingCart;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.exception.PersonalException;
import by.menko.finalproject.service.ShoppingCartService;

import java.util.*;

public class ShoppingCartServiceImpl extends ServiceImpl implements ShoppingCartService {
    @Override
    public void addShoppingCart(String idDisk, Integer idUser) throws PersonalException {
        ShoppingCartDao dao = transaction.createDao(TypeServiceAndDao.SHOPPING_CART);
        DiskDao diskDao = transaction.createDao(TypeServiceAndDao.DISK);
        ShoppingCart cart = new ShoppingCart();
        try {
            Integer id = Integer.parseInt(idDisk);
            Optional<Disk> disk = diskDao.readByIdDisk(id);
            if (disk.isPresent()) {
                cart.setDiskId(id);
                cart.setIdEntity(idUser);
                dao.create(cart);
                transaction.commit();
            } else {
                transaction.rollback();
                throw new PersonalException();
            }
        } catch (NumberFormatException | PersonalException e) {
            transaction.rollback();
            throw new PersonalException();
        }
    }

    @Override
    public List<ShoppingCart> getAllDiskFromShopCart(Integer idUser) throws PersonalException {
        ShoppingCartDao dao = transaction.createDao(TypeServiceAndDao.SHOPPING_CART);
        try {
            List<ShoppingCart> listCart = new ArrayList<>();
            listCart = dao.readAll(idUser);
            transaction.commit();
            return listCart;
        } catch (PersonalException e) {
            transaction.rollback();
            throw new PersonalException();
        }

    }

    @Override
    public Map<ShoppingCart, Disk> getShoppingCart(Integer idUser) throws PersonalException {
        DiskDao diskDao = transaction.createDao(TypeServiceAndDao.DISK);
        try {
            List<ShoppingCart> list = getAllDiskFromShopCart(idUser);
            Map<ShoppingCart, Disk> map = new HashMap<>();
            Optional<Disk> disk;

            for (ShoppingCart item : list) {
                disk = diskDao.readByIdDisk(item.getDiskId());
                if (disk.isPresent()) {
                    disk.get().setIdEntity(item.getDiskId());
                    map.put(item, disk.get());
                }
            }
            transaction.commit();
            return map;
        } catch (PersonalException e) {
            transaction.rollback();
            throw new PersonalException();
        }
    }

    @Override
    public void deleteAll(Integer idUser) throws PersonalException {
        ShoppingCartDao dao = transaction.createDao(TypeServiceAndDao.SHOPPING_CART);
        try {
            dao.deleteAll(idUser);
            transaction.commit();
        } catch (PersonalException e) {
            transaction.rollback();
            throw new PersonalException();
        }
    }

    @Override
    public void deleteDisk(String idDisk, Integer idUser) throws PersonalException {
        ShoppingCartDao dao = transaction.createDao(TypeServiceAndDao.SHOPPING_CART);
        try {
            Integer id = Integer.parseInt(idDisk);
            dao.delete(id, idUser);
            transaction.commit();
        } catch (NumberFormatException | PersonalException e) {
            transaction.rollback();
            throw new PersonalException();
        }
    }
}
