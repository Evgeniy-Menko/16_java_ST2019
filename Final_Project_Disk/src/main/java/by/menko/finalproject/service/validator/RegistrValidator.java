package by.menko.finalproject.service.validator;


import by.menko.finalproject.entity.UserInfo;

import by.menko.finalproject.service.exception.ServicePersonalException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class RegistrValidator {
    private static Logger logger = LogManager.getLogger();
    private static final String REGEX = "[A-zА-яЁё]*";
    private static final String REGEX_NUMBER_PHONE = "^((8|\\+3|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String REGEX_PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{6,20}$";

    public void validate(final UserInfo user, final String repeatPassword) throws ServicePersonalException {
        if (user.getFirstName() == null || user.getFirstName().isEmpty() || !user.getFirstName().matches(REGEX)) {
            String message = String.format("Error first name %s", user.getFirstName());
            logger.debug(message);
            throw new ServicePersonalException("errorFirstName");
        }
        if (user.getLastName() == null || user.getLastName().isEmpty() || !user.getLastName().matches(REGEX)) {
            String message = String.format("Error last name %s", user.getLastName());
            logger.debug(message);
            throw new ServicePersonalException("errorLastName");
        }
        if (user.getNickname() == null || user.getNickname().isEmpty() || !user.getNickname().matches(REGEX)) {
            String message = String.format("Error nickname %s", user.getNickname());
            logger.debug(message);
            throw new ServicePersonalException("errorNickname");
        }
        if (user.getPhone() != null && !user.getPhone().isEmpty() && !user.getPhone().matches(REGEX_NUMBER_PHONE)) {
            String message = String.format("Error phone %s", user.getPhone());
            logger.debug(message);
            throw new ServicePersonalException("errorPhone");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty() || !user.getEmail().matches(EMAIL_PATTERN)) {
            String message = String.format("Error email %s", user.getEmail());
            logger.debug(message);
            throw new ServicePersonalException("errorEmail");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty() || repeatPassword == null
                || repeatPassword.isEmpty() || !user.getPassword().equals(repeatPassword)
                || !user.getPassword().matches(REGEX_PASSWORD)) {
            String message = String.format("Error password %s", user.getPassword());
            logger.debug(message);
            throw new ServicePersonalException("errorPassword");
        }
    }
}
