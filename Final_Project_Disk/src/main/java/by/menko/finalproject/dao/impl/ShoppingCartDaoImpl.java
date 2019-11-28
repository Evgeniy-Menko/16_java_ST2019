package by.menko.finalproject.dao.impl;

import by.menko.finalproject.dao.ShoppingCartDao;
import by.menko.finalproject.entity.ShoppingCart;

import by.menko.finalproject.exception.PersonalException;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDaoImpl extends BaseDao implements ShoppingCartDao {

    private static final String DELETE_ALL = "DELETE FROM `shopping_cart` WHERE `user_id`=?";
    private static final String CREATE_SHOPPING_CART = "INSERT INTO `shopping_cart` (`user_id`, `disk_id`) VALUES (?, ?)";
    private static final String GET_ALL_BY_USER = "SELECT `disk_id`,`time_added` FROM `shopping_cart` WHERE `user_id`=?";
    private static final String DELETE_DISK = "DELETE FROM `shopping_cart` WHERE `user_id`=? AND `disk_id`=?";

    @Override
    public Integer create(ShoppingCart entity) throws PersonalException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(CREATE_SHOPPING_CART);
            statement.setInt(1, entity.getIdEntity());
            statement.setInt(2, entity.getDisk_id());
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersonalException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    @Override
    public void delete(Integer id) throws PersonalException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_ALL);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersonalException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }


    @Override
    public List<ShoppingCart> readAll(Integer id) throws PersonalException {
        List<ShoppingCart> list = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(GET_ALL_BY_USER);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            ShoppingCart shoppingCart = null;
            if (resultSet.next()) {
                shoppingCart = new ShoppingCart();
                shoppingCart.setDisk_id(resultSet.getInt("disk_id"));
                shoppingCart.setTimeAdded(resultSet.getDate("time_added"));
                list.add(shoppingCart);
            }
            return list;
        } catch (SQLException e) {
            throw new PersonalException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    @Override
    public void deleteDisk(Integer idDisk, Integer idUser) throws PersonalException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_DISK);
            statement.setInt(1, idDisk);
            statement.setInt(2, idUser);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersonalException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }
}
