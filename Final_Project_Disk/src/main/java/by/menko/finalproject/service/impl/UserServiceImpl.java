package by.menko.finalproject.service.impl;

import by.menko.finalproject.dao.UserDao;

import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.entity.enumtype.Role;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.exception.PersonalException;
import by.menko.finalproject.exception.ServicePersonalException;
import by.menko.finalproject.service.UserService;

import java.util.Optional;


public class UserServiceImpl extends ServiceImpl implements UserService {

    public UserInfo finUserByEmail(String email, String password) throws PersonalException, ServicePersonalException {
        UserDao dao = transaction.createDao(TypeServiceAndDao.USER);
        try {
            Optional<UserInfo> user = dao.getSaltAndPassword(email);
            if (user.isPresent()) {
                boolean flag = PasswordUtils.verifyUserPassword(password, user.get().getPassword(), user.get().getSalt());
                if (flag) {
                    user.get().setPassword("");
                    user.get().setSalt("");
                    transaction.commit();
                    return user.get();
                } else {
                    throw new ServicePersonalException("unknowLogin");
                }
            } else {
                throw new ServicePersonalException("unknowLogin");
            }
        } catch (PersonalException e) {
            transaction.rollback();
            throw new PersonalException();
        }
    }


    @Override
    public UserInfo registrUser(UserInfo user) throws PersonalException, ServicePersonalException {
        UserDao dao = transaction.createDao(TypeServiceAndDao.USER);
        try {
            boolean flag = dao.readByEmailAndNickname(user);
            if (!flag) {
                throw new ServicePersonalException("errorLoginNick");
            }
            String salt = PasswordUtils.getSalt(30);
            String hashPassword = PasswordUtils.generateSecurePassword(user.getPassword(), salt);
            user.setSalt(salt);
            user.setPassword(hashPassword);
            user.setRole(Role.USER);
            Integer id;

            id = dao.createUser(user);

            UserInfo resultUser = new UserInfo();
            resultUser.setIdEntity(id);
            resultUser.setRole(user.getRole());
            transaction.commit();
            return resultUser;
        } catch (PersonalException e) {
            transaction.rollback();
            throw new PersonalException();
        }

    }

    @Override
    public UserInfo getUser(Integer identity) throws PersonalException {
        UserDao dao = transaction.createDao(TypeServiceAndDao.USER);
        try {
            Optional<UserInfo> user = dao.read(identity);
            transaction.commit();
            if (user.isPresent()) {
                return user.get();
            } else {
                throw new PersonalException();
            }
        } catch (PersonalException e) {
            transaction.rollback();
            throw new PersonalException();
        }
    }


    @Override
    public void updateUser(UserInfo newUser, Integer idEntity, String oldPassowrd) throws PersonalException, ServicePersonalException {
        UserDao dao = transaction.createDao(TypeServiceAndDao.USER);
        try {
            Optional<UserInfo> oldUser = dao.readAllInfo(idEntity);
            if (oldPassowrd != null && !oldPassowrd.isEmpty()) {
                if (oldUser.isPresent()) {
                    boolean flag = PasswordUtils.verifyUserPassword(oldPassowrd,
                            oldUser.get().getPassword(), oldUser.get().getSalt());
                    if (flag) {
                        String password = PasswordUtils.generateSecurePassword(newUser.getPassword(), oldUser.get().getSalt());
                        newUser.setPassword(password);
                    } else {
                        throw new ServicePersonalException("unknowPassword");
                    }
                } else {
                    throw new PersonalException();
                }
            } else if (oldUser.isPresent()) {
                newUser.setPassword(oldUser.get().getPassword());
            } else {
                throw new PersonalException();
            }
            newUser.setIdEntity(oldUser.get().getIdEntity());
            if (oldUser.get().getImage().equals(newUser.getImage())) {
                newUser.setImage(oldUser.get().getImage());
            }
            dao.update(newUser);
            transaction.commit();
        } catch (PersonalException e) {
            transaction.rollback();
            throw new PersonalException();
        }
    }
}

