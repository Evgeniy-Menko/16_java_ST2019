package by.menko.finalproject.service;


import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.exception.PersonalException;
import by.menko.finalproject.exception.ServicePersonalException;



import java.io.IOException;

public interface UserService extends Service {
    UserInfo finUserByEmail(String email, String password) throws PersonalException, ServicePersonalException;

    UserInfo registrUser(UserInfo user) throws PersonalException, IOException, ServicePersonalException;

    UserInfo getUser(Integer identity) throws PersonalException;

    void updateUser(UserInfo newUser, Integer idEntity, String newPassword) throws PersonalException, ServicePersonalException;
}
