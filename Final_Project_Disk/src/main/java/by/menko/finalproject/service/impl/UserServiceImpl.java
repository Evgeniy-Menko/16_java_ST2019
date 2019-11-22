package by.menko.finalproject.service.impl;

import by.menko.finalproject.dao.UserDao;
import by.menko.finalproject.entity.User;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.exception.PersonalException;
import by.menko.finalproject.service.UserService;

import java.util.Optional;


public class UserServiceImpl extends ServiceImpl implements UserService {
    public User finUserByEmail(String email, String password) throws PersonalException {
        UserDao dao = transaction.createDao(TypeServiceAndDao.USER);
        Optional<User> user = dao.readUserByEmail(email, password);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new PersonalException();
        }

    }
}
