package by.menko.finalproject.dao.exception;

public class PersonalException extends Exception {
    public PersonalException() {}

    public PersonalException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersonalException(String message) {
        super(message);
    }

    public PersonalException(Throwable cause) {
        super(cause);
    }
}
