package by.menko.finalproject.dao.impl;

import by.menko.finalproject.dao.UserDao;
import by.menko.finalproject.dao.constantcolumn.ConstantColumn;
import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.entity.enumtype.Role;
import by.menko.finalproject.dao.exception.PersonalException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import java.util.Optional;


public class UserDaoImpl extends BaseDao implements UserDao {
    private static Logger logger = LogManager.getLogger();
    private static final String CREATE_USER_WITH_PHONE = "INSERT INTO `users` (`mail`, `password`,`salt`,`role`,`first_name`,`last_name`,`nickname`,`phone`,`image`) VALUES (?, ?, ?,?, ?, ?,?, ?, ?)";
    private static final String GET_USER_INFO = "SELECT `id_user` FROM `users` WHERE `mail` = ? OR `nickname`=?";
    private static final String GET_SALT_PASSWORD = "SELECT `id_user`, `role`,`salt`,`password` FROM `users` WHERE `mail` = ?";
    private static final String GET_USER_BY_ID = "SELECT `mail`,`first_name`,`last_name`,`nickname`,`image`,`phone` FROM `users` WHERE `id_user` = ?";
    private static final String GET_ALL_INFO_BY_ID = "SELECT `id_user`,`first_name`,`last_name`,`nickname`,`image`,`salt`,`password` FROM `users` WHERE `id_user` = ?";
    private static final String UPDATE_USER_INFO = "UPDATE `users` SET `first_name` = ?, `last_name` = ?, `nickname` = ?, `password`=? ,`image`=?,`phone`=? WHERE `id_user` = ?";

    @Override
    public Integer createUser(final UserInfo user) throws PersonalException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_USER_WITH_PHONE,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getSalt());
            statement.setInt(4, user.getRole().getIdRole());
            statement.setString(5, user.getFirstName());
            statement.setString(6, user.getLastName());
            statement.setString(7, user.getNickname());
            statement.setString(8, user.getPhone());
            statement.setString(9, user.getImage());
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                } else {
                    logger.error("There is no autoincremented index after trying to add record into table `users`");
                    throw new PersonalException();
                }
            } catch (SQLException e) {
                throw new PersonalException(e);
            }
        } catch (SQLException e) {
            throw new PersonalException(e);
        }
    }


    public Boolean readByEmailAndNickname(final UserInfo user) throws PersonalException {
        try (PreparedStatement statement = connection.prepareStatement(GET_USER_INFO)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getNickname());

            try (ResultSet resultSet = statement.executeQuery()) {
                return !resultSet.next();
            } catch (SQLException e) {
                throw new PersonalException(e);
            }
        } catch (SQLException e) {
            throw new PersonalException(e);
        }
    }


    public Optional<UserInfo> getSaltAndPassword(final String email) throws PersonalException {
        try (PreparedStatement statement = connection.prepareStatement(GET_SALT_PASSWORD)) {
            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {
                UserInfo user = null;
                if (resultSet.next()) {
                    user = new UserInfo();
                    user.setIdEntity(resultSet.getInt(ConstantColumn.ID_USER));
                    user.setRole(Role.getByIdRole(resultSet.getInt(ConstantColumn.ROLE)));
                    user.setSalt(resultSet.getString(ConstantColumn.SALT));
                    user.setPassword(resultSet.getString(ConstantColumn.PASSWORD));
                }

                return Optional.ofNullable(user);
            } catch (SQLException e) {
                throw new PersonalException(e);
            }
        } catch (SQLException e) {
            throw new PersonalException(e);
        }
    }


    @Override
    public Optional<UserInfo> read(final Integer identity) throws PersonalException {
        try (PreparedStatement statement = connection.prepareStatement(GET_USER_BY_ID)) {
            statement.setInt(1, identity);

            try (ResultSet resultSet = statement.executeQuery()) {
                UserInfo user = null;
                if (resultSet.next()) {
                    user = new UserInfo();
                    user.setEmail(resultSet.getString(ConstantColumn.MAIL));
                    user.setFirstName(resultSet.getString(ConstantColumn.FIRST_NAME));
                    user.setLastName(resultSet.getString(ConstantColumn.LAST_NAME));
                    user.setNickname(resultSet.getString(ConstantColumn.NICKNAME));
                    user.setPhone(resultSet.getString(ConstantColumn.PHONE));
                    user.setImage(resultSet.getString(ConstantColumn.IMAGE));
                }
                return Optional.ofNullable(user);
            } catch (SQLException e) {
                throw new PersonalException(e);
            }
        } catch (SQLException e) {
            throw new PersonalException(e);
        }
    }


    @Override
    public Optional<UserInfo> readAllInfo(final Integer identity) throws PersonalException {
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_INFO_BY_ID)) {
            statement.setInt(1, identity);

            try (ResultSet resultSet = statement.executeQuery()) {
                UserInfo user = null;
                if (resultSet.next()) {
                    user = new UserInfo();
                    user.setIdEntity(resultSet.getInt(ConstantColumn.ID_USER));
                    user.setFirstName(resultSet.getString(ConstantColumn.FIRST_NAME));
                    user.setLastName(resultSet.getString(ConstantColumn.LAST_NAME));
                    user.setNickname(resultSet.getString(ConstantColumn.NICKNAME));
                    user.setImage(resultSet.getString(ConstantColumn.IMAGE));
                    user.setSalt(resultSet.getString(ConstantColumn.SALT));
                    user.setPassword(resultSet.getString(ConstantColumn.PASSWORD));
                }
                return Optional.ofNullable(user);
            } catch (SQLException e) {
                throw new PersonalException(e);
            }
        } catch (SQLException e) {
            throw new PersonalException(e);
        }
    }


    @Override
    public void update(final UserInfo user) throws PersonalException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER_INFO)) {

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getNickname());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getImage());
            statement.setString(6, user.getPhone());
            statement.setInt(7, user.getIdEntity());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersonalException(e);
        }
    }


}
