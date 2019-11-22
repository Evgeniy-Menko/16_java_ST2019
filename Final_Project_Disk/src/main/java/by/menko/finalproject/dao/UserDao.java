package by.menko.finalproject.dao;

import by.menko.finalproject.entity.User;
import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.exception.PersonalException;

import java.util.List;
import java.util.Optional;

public interface UserDao extends Dao<User> {
    void updateInfo(UserInfo user) throws PersonalException;

    void createInfo(UserInfo user) throws PersonalException;

    Optional<User> readUserByEmail(String email, String password) throws PersonalException;

    Optional<UserInfo> readInfo(Integer id) throws PersonalException;

    void deleteInfo(Integer id) throws PersonalException;

    List<UserInfo> read() throws PersonalException;

}
