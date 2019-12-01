package by.menko.finalproject.dao.impl;

import by.menko.finalproject.dao.CatalogDao;
import by.menko.finalproject.entity.Catalog;

import by.menko.finalproject.exception.PersonalException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CatalogDaoImpl extends BaseDao implements CatalogDao {
    private static final String READ_ALL = "SELECT  `genre`,`type`,`id_genre`,`id_type` FROM `genre` INNER JOIN `type` ON `type_id` = `id_type` ORDER BY `type`";

    @Override
    public List<Catalog> read() throws PersonalException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(READ_ALL);
            resultSet = statement.executeQuery();
            List<Catalog> result = new ArrayList<>();
            Catalog catalog;
            while (resultSet.next()) {
                catalog = new Catalog();
                catalog.setIdGenre(resultSet.getInt("id_genre"));
                catalog.setIdType(resultSet.getInt("id_type"));
                catalog.setGenre(resultSet.getString("genre"));
                catalog.setType(resultSet.getString("type"));
                result.add(catalog);
            }
            return result;
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
}