package by.menko.finalproject.dao;


import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.exception.PersonalException;


import java.util.Optional;

public interface UserDao extends Dao<UserInfo> {
    Integer createUser(UserInfo user) throws PersonalException;

    Integer createUserNoPhone(UserInfo user) throws PersonalException;

    Boolean readByEmailAndNickname(UserInfo user) throws PersonalException;

   Optional<UserInfo> getSaltAndPassword(String email) throws PersonalException;
}
