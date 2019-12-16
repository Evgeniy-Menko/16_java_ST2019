package by.menko.finalproject.dao.impl;

import by.menko.finalproject.dao.ShoppingCartDao;
import by.menko.finalproject.dao.constantcolumn.ConstantColumn;
import by.menko.finalproject.entity.ShoppingCart;

import by.menko.finalproject.exception.PersonalException;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDaoImpl extends BaseDao implements ShoppingCartDao {

    private static final String DELETE_ALL = "DELETE FROM `shopping_cart` WHERE `user_id`=? ";
    private static final String CREATE_SHOPPING_CART = "INSERT INTO `shopping_cart` (`user_id`, `disk_id`) VALUES (?, ?)";
    private static final String GET_ALL_BY_USER = "SELECT `disk_id`,`time_added` FROM `shopping_cart` WHERE `user_id`=? ORDER BY `time_added` DESC";
    private static final String DELETE_DISK = "DELETE FROM `shopping_cart` WHERE `user_id`=? AND `disk_id`=?";

    @Override
    public Integer create(ShoppingCart entity) throws PersonalException {

        try (PreparedStatement statement = connection.prepareStatement(CREATE_SHOPPING_CART)) {
            statement.setInt(1, entity.getIdEntity());
            statement.setInt(2, entity.getDiskId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersonalException(e);
        }
    }

    @Override
    public void delete(Integer id, Integer idUser) throws PersonalException {

        try (PreparedStatement statement = connection.prepareStatement(DELETE_DISK)) {
            statement.setInt(1, idUser);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersonalException(e);
        }
    }


    @Override
    public List<ShoppingCart> readAll(Integer id) throws PersonalException {
        List<ShoppingCart> list = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_BY_USER)) {
            statement.setInt(1, id);
            ShoppingCart shoppingCart ;
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    shoppingCart = new ShoppingCart();
                    shoppingCart.setDiskId(resultSet.getInt(ConstantColumn.DISK_ID));
                    shoppingCart.setTimeAdded(resultSet.getDate(ConstantColumn.TIME_ADDED));
                    list.add(shoppingCart);
                }
                return list;
            } catch (SQLException e) {
                throw new PersonalException(e);
            }
        } catch (SQLException e) {
            throw new PersonalException(e);
        }
    }

    @Override
    public void deleteAll(Integer idUser) throws PersonalException {

        try (PreparedStatement statement = connection.prepareStatement(DELETE_ALL)) {
            statement.setInt(1, idUser);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersonalException(e);
        }
    }
}
