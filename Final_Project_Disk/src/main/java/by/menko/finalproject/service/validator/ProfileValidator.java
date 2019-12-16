package by.menko.finalproject.service.validator;

import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.exception.ServicePersonalException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;

public class ProfileValidator {
    private final static String REGEX = "[A-zА-яЁё]*";
    private final static String REGEX_NUMBER_PHONE = "^((8|\\+3|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";
    private final static String REGEX_PASSWORD = "[\\S]*";

    public UserInfo validate(HttpServletRequest request) throws IOException, ServletException, ServicePersonalException {
        Part image = request.getPart("image");
        String imageName = image.getSubmittedFileName();
        String firstName = request.getParameter("name");
        String lastName = request.getParameter("surname");
        String phone = request.getParameter("phone");
        String nickname = request.getParameter("nickname");
        String oldPassword = request.getParameter("oldPassword");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("password2");
        String formatImage = imageName.substring(imageName.lastIndexOf('.') + 1);
        boolean flag = "jpg".equals(formatImage) || "png".equals(formatImage) || "jpeg".equals(formatImage) || "gif".equals(formatImage);
        UserInfo user = new UserInfo();
        if (!imageName.isEmpty() && flag) {
            user.setImage(imageName);
        } else if (!imageName.isEmpty()) {
            throw new ServicePersonalException("errorFormatImage");
        }
        if (firstName != null && !firstName.isEmpty() && firstName.matches(REGEX)) {
            user.setFirstName(firstName);
        } else {
            throw new ServicePersonalException("errorFirstName");
        }
        if (lastName != null && !lastName.isEmpty() && lastName.matches(REGEX)) {
            user.setLastName(lastName);
        } else {
            throw new ServicePersonalException("errorLastName");
        }
        if (nickname != null && !nickname.isEmpty() && nickname.matches(REGEX)) {
            user.setNickname(nickname);
        } else {
            throw new ServicePersonalException("errorNickname");
        }
        if (phone != null && !phone.isEmpty() && phone.matches(REGEX_NUMBER_PHONE)) {
            user.setPhone(phone);
        } else if (phone != null && !phone.isEmpty()) {
            throw new ServicePersonalException("errorPhone");
        }
        if (oldPassword != null && !oldPassword.isEmpty()) {

            if (oldPassword.length() >= 4 && oldPassword.length() <= 20
                    && oldPassword.matches(REGEX_PASSWORD)) {
                user.setPassword(password);
            } else {
                throw new ServicePersonalException("errorPassword");
            }
            if (password != null && !password.isEmpty() && repeatPassword != null
                    && !repeatPassword.isEmpty() && password.equals(repeatPassword)
                    && password.length() >= 4 && password.length() <= 20
                    && password.matches(REGEX_PASSWORD)) {
                user.setPassword(password);
            } else {
                throw new ServicePersonalException("errorPassword");
            }
        }

        return user;
    }
}
