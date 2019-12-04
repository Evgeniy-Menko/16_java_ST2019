package by.menko.finalproject.dao.impl;

import by.menko.finalproject.dao.UserDao;
import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.entity.enumtype.Role;
import by.menko.finalproject.exception.PersonalException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UserDaoImpl extends BaseDao implements UserDao {
    /* private static final String CREATE_USER = "INSERT INTO `users` (`mail`, `password`, `role`) VALUES (?, ?, ?)";
     private static final String CREATE_USER_INFO = "INSERT INTO `user_info` (`id_user`, `first_name`, `last_name`, `nickname`, `phone`) VALUES (?, ?, ?, ?, ?)";
     private static final String GET_USER = "SELECT `mail`, `password`, `role`,`flag_blocked` FROM `users` WHERE `id` = ?";
     private static final String UPDATE_USER_INFO = "UPDATE `user_info` SET `first_name` = ?, `last_name` = ?, `nickname` = ?, `phone` = ? WHERE `id` = ?";
     private static final String DELETE_USER = "DELETE FROM `users` WHERE `id` = ?";
     private static final String GET_USER_BY_EMAIL = "SELECT `id_user`, `mail`, `password`, `role`,`flag_blocked` FROM `users` WHERE `mail` = ? AND `password` = ? AND `flag_blocked` = 0";
     private static final String GET_USER_INFO = "SELECT `id_user`, `first_name`, `last_name`, `nickname`, `phone` FROM `user_info` WHERE `id_user` = ?";
     private static final String DELETE_INFO = "DELETE FROM `user_info` WHERE `id_user` = ?";
     private static final String GET_ALL_USER = "SELECT `id_user`, `first_name`, `last_name`, `nickname`, `phone`,`time_registration` FROM `user_info`";
     private static final String UPDATE_USER = "UPDATE users SET `password`=? WHERE `id`=?";

     @Override
     public Integer create(User user) throws PersonalException {
         PreparedStatement statement = null;
         ResultSet resultSet = null;
         try {
             statement = connection.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS);
             statement.setString(1, user.getEmail());
             statement.setString(2, user.getPassword());
             statement.setInt(3, user.getRole().getIdRole());
             statement.executeUpdate();
             resultSet = statement.getGeneratedKeys();
             if (resultSet.next()) {
                 return resultSet.getInt(1);
             } else {
                 //		logger.error("There is no autoincremented index after trying to add record into table `users`");
                 throw new PersonalException();
             }
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
     public Optional<User> read(Integer identity) throws PersonalException {
         PreparedStatement statement = null;
         ResultSet resultSet = null;
         try {
             statement = connection.prepareStatement(GET_USER);
             statement.setInt(1, identity);
             resultSet = statement.executeQuery();
             User user = null;
             if (resultSet.next()) {
                 user = new User();
                 user.setIdEntity(identity);
                 user.setEmail(resultSet.getString("mail"));
                 user.setPassword(resultSet.getString("password"));
                 user.setRole(Role.getByIdRole(resultSet.getInt("role")));
                 user.setFlagBlocked(resultSet.getInt("flag_blocked"));
             }
             return Optional.of(
                     user);
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
     public void update(User entity) throws PersonalException {

     }

     @Override
     public void updateInfo(UserInfo user) throws PersonalException {
         PreparedStatement statement = null;
         try {
             statement = connection.prepareStatement(UPDATE_USER_INFO);
             statement.setString(1, user.getFirstName());
             statement.setString(2, user.getLastName());
             statement.setString(3, user.getNickname());
             statement.setString(4, user.getPhone());
             statement.setInt(5, user.getIdEntity());
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
     public void createInfo(UserInfo userInfo) throws PersonalException {
         PreparedStatement statement = null;
         try {
             statement = connection.prepareStatement(CREATE_USER_INFO);
             statement.setInt(1, userInfo.getIdEntity());
             statement.setString(2, userInfo.getFirstName());
             statement.setString(3, userInfo.getLastName());
             statement.setString(4, userInfo.getNickname());
             statement.setString(5, userInfo.getPhone());
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
     public Optional<User> readUserByEmail(String email, String password) throws PersonalException {
         PreparedStatement statement = null;
         ResultSet resultSet = null;
         try {
             statement = connection.prepareStatement(GET_USER_BY_EMAIL);
             statement.setString(1, email);
             statement.setString(2, password);
             resultSet = statement.executeQuery();
             User user = new User();
             if (resultSet.next()) {
                 user = new User();
                 user.setIdEntity(resultSet.getInt("id_user"));
                 user.setEmail(resultSet.getString("mail"));
                 user.setPassword(resultSet.getString("password"));
                 user.setRole(Role.getByIdRole(resultSet.getInt("role")));
                 user.setFlagBlocked(resultSet.getInt("flag_blocked"));
             }
             return Optional.of(
                     user);
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
     public Optional<UserInfo> readInfo(Integer id) throws PersonalException {
         PreparedStatement statement = null;
         ResultSet resultSet = null;
         try {
             statement = connection.prepareStatement(GET_USER_INFO);
             statement.setInt(1, id);
             resultSet = statement.executeQuery();
             UserInfo user = null;
             if (resultSet.next()) {
                 user = new UserInfo();
                 user.setIdEntity(resultSet.getInt("id"));
                 user.setFirstName(resultSet.getString("first_name"));
                 user.setLastName(resultSet.getString("last_name"));
                 user.setNickname(resultSet.getString("nickname"));
                 user.setPhone(resultSet.getString("phone"));
             }
             return Optional.of(
                     user);
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
     public void deleteInfo(Integer id) throws PersonalException {
         PreparedStatement statement = null;
         try {
             statement = connection.prepareStatement(DELETE_INFO);
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
     public List<UserInfo> read() throws PersonalException {
         List<UserInfo> listUser = new ArrayList<>();
         PreparedStatement statement = null;
         ResultSet resultSet = null;
         try {
             statement = connection.prepareStatement(GET_ALL_USER);
             resultSet = statement.executeQuery();
             UserInfo user = null;
             if (resultSet.next()) {
                 user = new UserInfo();
                 user.setIdEntity(resultSet.getInt("id_user"));
                 user.setFirstName(resultSet.getString("first_name"));
                 user.setLastName(resultSet.getString("last_name"));
                 user.setNickname(resultSet.getString("nickname"));
                 user.setPhone(resultSet.getString("phone"));
                 user.setDateRegistration(resultSet.getDate("time_registration"));
                 listUser.add(user);
             }
             return null;
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
     public void delete(Integer identity) throws PersonalException {
         PreparedStatement statement = null;
         try {
             statement = connection.prepareStatement(DELETE_USER);
             statement.setInt(1, identity);
             statement.executeUpdate();
         } catch (SQLException e) {
             throw new PersonalException(e);
         } finally {
             try {
                 statement.close();
             } catch (SQLException | NullPointerException e) {
             }
         }
     }*/
    private static final String CREATE_USER_WITH_PHONE = "INSERT INTO `users` (`mail`, `password`,`salt`,`role`,`first_name`,`last_name`,`nickname`,`phone`,`image`) VALUES (?, ?, ?,?, ?, ?,?, ?, ?)";

    @Override
    public Integer createUser(UserInfo user) throws PersonalException {


        try (PreparedStatement statement = connection.prepareStatement(CREATE_USER_WITH_PHONE, Statement.RETURN_GENERATED_KEYS)) {

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
                    //		logger.error("There is no autoincremented index after trying to add record into table `users`");
                    throw new PersonalException();
                }
            } catch (SQLException e) {
                throw new PersonalException(e);
            }
        } catch (SQLException e) {
            throw new PersonalException(e);
        }
    }



    private static final String GET_USER_INFO = "SELECT `id_user` FROM `users` WHERE `mail` = ? OR `nickname`=?";

    public Boolean readByEmailAndNickname(UserInfo user) throws PersonalException {
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

    private static final String GET_SALT_PASSWORD = "SELECT `id_user`, `role`,`salt`,`password` FROM `users` WHERE `mail` = ?";

    public Optional<UserInfo> getSaltAndPassword(String email) throws PersonalException {
        try (PreparedStatement statement = connection.prepareStatement(GET_SALT_PASSWORD)) {
            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {
                UserInfo user = null;
                if (resultSet.next()) {
                    user = new UserInfo();
                    user.setIdEntity(resultSet.getInt("id_user"));
                    user.setRole(Role.getByIdRole(resultSet.getInt("role")));
                    user.setSalt(resultSet.getString("salt"));
                    user.setPassword(resultSet.getString("password"));
                }

                return Optional.ofNullable(user);
            } catch (SQLException e) {
                throw new PersonalException(e);
            }
        } catch (SQLException e) {
            throw new PersonalException(e);
        }
    }

    private static final String GET_USER_BY_ID = "SELECT `mail`,`first_name`,`last_name`,`nickname`,`image`,`phone` FROM `users` WHERE `id_user` = ?";

    @Override
    public Optional<UserInfo> read(Integer identity) throws PersonalException {
        try (PreparedStatement statement = connection.prepareStatement(GET_USER_BY_ID)) {
            statement.setInt(1, identity);

            try (ResultSet resultSet = statement.executeQuery()) {
                UserInfo user = null;
                if (resultSet.next()) {
                    user = new UserInfo();
                    user.setEmail(resultSet.getString("mail"));
                    user.setFirstName(resultSet.getString("first_name"));
                    user.setLastName(resultSet.getString("last_name"));
                    user.setNickname(resultSet.getString("nickname"));
                    user.setPhone(resultSet.getString("phone"));
                    user.setImage(resultSet.getString("image"));
                }
                return Optional.ofNullable(user);
            } catch (SQLException e) {
                throw new PersonalException(e);
            }
        } catch (SQLException e) {
            throw new PersonalException(e);
        }
    }

    private static final String GET_ALL_INFO_BY_ID = "SELECT `id_user`,`first_name`,`last_name`,`nickname`,`image`,`salt`,`password` FROM `users` WHERE `id_user` = ?";

    @Override
    public Optional<UserInfo> readAllInfo(Integer identity) throws PersonalException {
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_INFO_BY_ID)) {
            statement.setInt(1, identity);

            try (ResultSet resultSet = statement.executeQuery()) {
                UserInfo user = null;
                if (resultSet.next()) {
                    user = new UserInfo();
                    user.setIdEntity(resultSet.getInt("id_user"));
                    user.setFirstName(resultSet.getString("first_name"));
                    user.setLastName(resultSet.getString("last_name"));
                    user.setNickname(resultSet.getString("nickname"));
                    user.setImage(resultSet.getString("image"));
                    user.setSalt(resultSet.getString("salt"));
                    user.setPassword(resultSet.getString("password"));
                }
                return Optional.ofNullable(user);
            } catch (SQLException e) {
                throw new PersonalException(e);
            }
        } catch (SQLException e) {
            throw new PersonalException(e);
        }
    }

    private static final String UPDATE_USER_INFO = "UPDATE `users` SET `first_name` = ?, `last_name` = ?, `nickname` = ?, `password`=? ,`image`=?,`phone`=? WHERE `id_user` = ?";

        @Override
        public void update(UserInfo user) throws PersonalException {
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
