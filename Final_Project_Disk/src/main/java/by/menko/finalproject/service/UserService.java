package by.menko.finalproject.service;


import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.exception.PersonalException;
import by.menko.finalproject.exception.ServicePersonalException;
import com.sun.javafx.geom.transform.Identity;

import javax.servlet.http.Part;
import java.io.IOException;

public interface UserService extends Service {
    UserInfo finUserByEmail(String email, String password) throws PersonalException, ServicePersonalException;

    UserInfo registrUser(UserInfo user, Part image, String pathTemp) throws PersonalException, IOException, ServicePersonalException;

    UserInfo getUser(Integer identity) throws PersonalException;
}
