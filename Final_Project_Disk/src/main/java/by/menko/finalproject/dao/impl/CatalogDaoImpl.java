package by.menko.finalproject.dao.impl;

import by.menko.finalproject.dao.CatalogDao;
import by.menko.finalproject.dao.constantcolumn.ConstantColumn;
import by.menko.finalproject.entity.Catalog;


import by.menko.finalproject.exception.PersonalException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class CatalogDaoImpl extends BaseDao implements CatalogDao {
    private static final String READ_ALL = "SELECT  `genre`,`type`,`id_genre`,`id_type` FROM `genre` INNER JOIN `type` ON `type_id` = `id_type` ORDER BY `type`";
    private static final String GET_ID_GENRE = "SELECT `id_genre` FROM `genre` INNER JOIN `type` ON `type_id` = `id_type` WHERE `genre` = ? AND `type`=?";

    @Override
    public List<Catalog> read() throws PersonalException {
        try (PreparedStatement statement = connection.prepareStatement(READ_ALL)) {
            List<Catalog> result = new ArrayList<>();
            Catalog catalog;
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    catalog = new Catalog();
                    catalog.setIdGenre(resultSet.getInt(ConstantColumn.ID_GENRE));
                    catalog.setIdType(resultSet.getInt(ConstantColumn.ID_TYPE));
                    catalog.setGenre(resultSet.getString(ConstantColumn.GENRE));
                    catalog.setType(resultSet.getString(ConstantColumn.TYPE));
                    result.add(catalog);
                }
                return result;
            } catch (SQLException e) {
                throw new PersonalException(e);
            }
        } catch (SQLException e) {
            throw new PersonalException(e);
        }
    }


    @Override
    public Optional<Integer> read(String genre, String type) throws PersonalException {
        try (PreparedStatement statement = connection.prepareStatement(GET_ID_GENRE)) {
            statement.setString(1, genre);
            statement.setString(2, type);
            try (ResultSet resultSet = statement.executeQuery()) {
                Integer id = null;
                if (resultSet.next()) {
                    id = resultSet.getInt(ConstantColumn.ID_GENRE);
                }
                return Optional.ofNullable(id);
            } catch (SQLException e) {
                throw new PersonalException(e);
            }
        } catch (SQLException e) {
            throw new PersonalException(e);
        }
    }
}