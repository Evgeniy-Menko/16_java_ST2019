package by.menko.finalproject.service.impl;

import by.menko.finalproject.dao.UserDao;

import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.entity.enumtype.Role;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.exception.PersonalException;
import by.menko.finalproject.exception.ServicePersonalException;
import by.menko.finalproject.service.UserService;

import javax.imageio.ImageIO;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Optional;


public class UserServiceImpl extends ServiceImpl implements UserService {
    private final static String NO_IMAGE = "images/no.png";
    private final static String PATH = "images/";

    public UserInfo finUserByEmail(String email, String password) throws PersonalException, ServicePersonalException {
        UserDao dao = transaction.createDao(TypeServiceAndDao.USER);

        Optional<UserInfo> user = dao.getSaltAndPassword(email);
        if (user.isPresent()) {
            boolean flag = PasswordUtils.verifyUserPassword(password, user.get().getPassword(), user.get().getSalt());
            if (flag) {
                return user.get();
            } else {
                throw new ServicePersonalException("unknowLogin");
            }
        } else {
            throw new ServicePersonalException("unknowLogin");
        }
    }


    @Override
    public UserInfo registrUser(UserInfo user, Part image, String pathTemp) throws PersonalException, IOException, ServicePersonalException {
        UserDao dao = transaction.createDao(TypeServiceAndDao.USER);
        boolean flag = dao.readByEmailAndNickname(user);
        if (!flag) {
            throw new ServicePersonalException("errorLoginNick");
        }
        String fileName = image.getSubmittedFileName();
        if (!fileName.isEmpty()) {
            createDirAndWriteToFile(pathTemp, image);
            user.setImage(PATH + fileName);
        } else {
            user.setImage(NO_IMAGE);
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
        return resultUser;


    }

    @Override
    public UserInfo getUser(Integer identity) throws PersonalException {
        UserDao dao = transaction.createDao(TypeServiceAndDao.USER);
        Optional<UserInfo> user = dao.read(identity);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new PersonalException();
        }

    }

    private void createDirAndWriteToFile(final String pathTemp,
                                         final Part filePart) throws PersonalException {
        try {
            File uploadDir = new File(pathTemp);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String fileName = filePart.getSubmittedFileName();
            File f = new File(pathTemp, fileName);
            BufferedImage image = null;

            image = ImageIO.read(filePart.getInputStream());

            String format = fileName.substring(fileName.lastIndexOf('.') + 1);
            ImageIO.write(image, format, f);
        } catch (IOException e) {
            throw new PersonalException();
        }
    }

    @Override
    public void updateUser(UserInfo newUser, Integer idEntity, String oldPassowrd, Part image, String pathTemp) throws PersonalException, ServicePersonalException {
        UserDao dao = transaction.createDao(TypeServiceAndDao.USER);
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
        if (newUser.getImage() != null && !newUser.getImage().isEmpty()
                && !oldUser.get().getImage().equals(newUser.getImage())) {
            createDirAndWriteToFile(pathTemp, image);
            String fileName = PATH+image.getSubmittedFileName();
            newUser.setImage(fileName);
        } else {
            newUser.setImage(oldUser.get().getImage());
        }

        dao.update(newUser);
    }
}

