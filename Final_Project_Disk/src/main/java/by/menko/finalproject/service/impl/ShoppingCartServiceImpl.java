package by.menko.finalproject.service.impl;

import by.menko.finalproject.dao.DiskDao;
import by.menko.finalproject.dao.ShoppingCartDao;
import by.menko.finalproject.entity.Disk;
import by.menko.finalproject.entity.ShoppingCart;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.exception.PersonalException;
import by.menko.finalproject.service.ShoppingCartService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShoppingCartServiceImpl extends ServiceImpl implements ShoppingCartService {
    @Override
    public void addShoppingCart(String idDisk, Integer idUser) throws PersonalException {
        ShoppingCartDao dao = transaction.createDao(TypeServiceAndDao.SHOPPING_CART);
        ShoppingCart cart = new ShoppingCart();
        try {
            Integer id = Integer.parseInt(idDisk);
            cart.setDiskId(id);
            cart.setIdEntity(idUser);
            dao.create(cart);
        } catch (NumberFormatException e) {
            throw new PersonalException();
        }
    }

    @Override
    public List<ShoppingCart> getAllDiskFromShopCart(Integer idUser) throws PersonalException {
        ShoppingCartDao dao = transaction.createDao(TypeServiceAndDao.SHOPPING_CART);
        return dao.readAll(idUser);

    }

    @Override
    public List<Disk> getShoppingCart(Integer idUser) throws PersonalException {
        DiskDao diskDao = transaction.createDao(TypeServiceAndDao.DISK);
        List<ShoppingCart> list = getAllDiskFromShopCart(idUser);
        List<Disk> listDisk = new ArrayList<>();
        Optional<Disk> disk;
        for (ShoppingCart item : list) {
            disk = diskDao.readByIdDisk(item.getDiskId());
            if (disk.isPresent()) {
                disk.get().setIdEntity(item.getDiskId());
                listDisk.add(disk.get());
            }
        }
        return listDisk;
    }

    @Override
    public void deleteAll(Integer idUser) throws PersonalException {
        ShoppingCartDao dao = transaction.createDao(TypeServiceAndDao.SHOPPING_CART);
        dao.deleteAll(idUser);
    }

    @Override
    public void deleteDisk(String idDisk, Integer idUser) throws PersonalException {
        ShoppingCartDao dao = transaction.createDao(TypeServiceAndDao.SHOPPING_CART);
        try {
            Integer id = Integer.parseInt(idDisk);
            dao.delete(id, idUser);
        } catch (NumberFormatException e) {
            throw new PersonalException();
        }
    }
}
