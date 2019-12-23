package by.menko.finalproject.service;


import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.dao.exception.PersonalException;
import by.menko.finalproject.service.exception.ServicePersonalException;


public interface UserService extends Service {
    /**
     * checking for existing user and password.
     *
     * @param email    email.
     * @param password password.
     *
     * @return user with id and role if found
     *
     * @throws PersonalException        sql exception.
     * @throws ServicePersonalException not found  or not equals passwords.
     */
    UserInfo finUserByEmail(final String email, final String password)
            throws PersonalException, ServicePersonalException;

    /**
     * Registration user.
     *
     * @param user           user with parameter.
     * @param repeatPassword repeat password.
     *
     * @return user with id and role.
     *
     * @throws PersonalException        sql exception.
     * @throws ServicePersonalException not validate parameters.
     */
    UserInfo registrUser(final UserInfo user, String repeatPassword)
            throws PersonalException, ServicePersonalException;

    /**
     * Get user info by id for profile.
     *
     * @param identity id user.
     *
     * @return user .
     *
     * @throws PersonalException sql exception.
     */
    UserInfo getUser(final Integer identity) throws PersonalException;

    /**
     * updated user.
     *
     * @param newUser        new parameter for save.
     * @param idEntity       id user.
     * @param oldPassword    old password.
     * @param repeatPassword repeat new password.
     *
     * @throws PersonalException        sql exception.
     * @throws ServicePersonalException no validate parameters.
     */
    void updateUser(final UserInfo newUser, final Integer idEntity,
                    final String oldPassword, final String repeatPassword) throws PersonalException, ServicePersonalException;
}
