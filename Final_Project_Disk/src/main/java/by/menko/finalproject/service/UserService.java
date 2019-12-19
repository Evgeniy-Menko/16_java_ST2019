package by.menko.finalproject.service;


import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.dao.exception.PersonalException;
import by.menko.finalproject.service.exception.ServicePersonalException;


import java.io.IOException;

public interface UserService extends Service {
    UserInfo finUserByEmail(final String email, final String password)
            throws PersonalException, ServicePersonalException;

    UserInfo registrUser(final UserInfo user,String repeatPassword)
            throws PersonalException, IOException, ServicePersonalException;

    UserInfo getUser(final Integer identity) throws PersonalException;

    void updateUser(final UserInfo newUser, final Integer idEntity,
                    final String newPassword,final String repeatPassword) throws PersonalException, ServicePersonalException;
}
