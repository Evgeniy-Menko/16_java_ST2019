package by.menko.finalproject.dao;


import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.dao.exception.PersonalException;


import java.util.Optional;

public interface UserDao extends Dao<UserInfo> {
    Integer createUser(final UserInfo user) throws PersonalException;


    Boolean readByEmailAndNickname(final UserInfo user) throws PersonalException;

    Optional<UserInfo> getSaltAndPassword(final String email) throws PersonalException;

    Optional<UserInfo> readAllInfo(final Integer identity) throws PersonalException;


}
