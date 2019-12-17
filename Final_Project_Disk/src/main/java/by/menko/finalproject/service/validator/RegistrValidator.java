package by.menko.finalproject.service.validator;


import by.menko.finalproject.entity.UserInfo;

import by.menko.finalproject.service.exception.ServicePersonalException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;

@MultipartConfig
public class RegistrValidator {
    private static Logger logger = LogManager.getLogger();
    private final static String REGEX = "[A-zА-яЁё]*";
    private final static String REGEX_NUMBER_PHONE = "^((8|\\+3|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private final static String REGEX_PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{6,20}$";

    public UserInfo validate(final HttpServletRequest request) throws IOException, ServletException, ServicePersonalException {
        Part image = request.getPart("image");
        String imageName = image.getSubmittedFileName();
        String firstName = request.getParameter("name");
        String lastName = request.getParameter("surname");
        String phone = request.getParameter("phone");
        String nickname = request.getParameter("nickname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("password2");
        String formatImage = imageName.substring(imageName.lastIndexOf('.') + 1);
        boolean flag = "jpg".equals(formatImage) || "png".equals(formatImage) || "jpeg".equals(formatImage) || "gif".equals(formatImage);
        UserInfo user = new UserInfo();
        if (!imageName.isEmpty() && flag) {
            user.setImage(imageName);
        } else if (!imageName.isEmpty()) {
            logger.debug(String.format("Error format image %s", imageName));
            throw new ServicePersonalException("errorFormatImage");
        }
        if (firstName != null && !firstName.isEmpty() && firstName.matches(REGEX)) {
            user.setFirstName(firstName);
        } else {
            logger.debug(String.format("Error first name %s", firstName));
            throw new ServicePersonalException("errorFirstName");
        }
        if (lastName != null && !lastName.isEmpty() && lastName.matches(REGEX)) {
            user.setLastName(lastName);
        } else {
            logger.debug(String.format("Error last name %s", lastName));
            throw new ServicePersonalException("errorLastName");
        }
        if (nickname != null && !nickname.isEmpty() && nickname.matches(REGEX)) {
            user.setNickname(nickname);
        } else {
            logger.debug(String.format("Error nickname %s", nickname));
            throw new ServicePersonalException("errorNickname");
        }
        if (phone != null && !phone.isEmpty() && phone.matches(REGEX_NUMBER_PHONE)) {
            user.setPhone(phone);
        } else if (phone != null && !phone.isEmpty()) {
            logger.debug(String.format("Error phone %s", phone));
            throw new ServicePersonalException("errorPhone");
        }
        if (email != null && !email.isEmpty() && email.matches(EMAIL_PATTERN)) {
            user.setEmail(email);
        } else {
            logger.debug(String.format("Error email %s", email));
            throw new ServicePersonalException("errorEmail");
        }
        if (password != null && !password.isEmpty() && repeatPassword != null
                && !repeatPassword.isEmpty() && password.equals(repeatPassword)
                && password.length() >= 4 && password.length() <= 20
                && password.matches(REGEX_PASSWORD)) {
            user.setPassword(password);
        } else {
            logger.debug(String.format("Error password %s", password));
            throw new ServicePersonalException("errorPassword");
        }


        return user;
    }
}
