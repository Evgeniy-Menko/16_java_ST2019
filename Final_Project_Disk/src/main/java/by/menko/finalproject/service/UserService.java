package by.menko.finalproject.service;

import by.menko.finalproject.entity.User;
import by.menko.finalproject.exception.PersonalException;

public interface UserService extends Service {
    User finUserByEmail(String email, String password) throws PersonalException;
}
