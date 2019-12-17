package by.menko.finalproject.service.exception;

public class ServicePersonalException extends Exception {
    public ServicePersonalException() {}

    public ServicePersonalException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServicePersonalException(String message) {
        super(message);
    }

    public ServicePersonalException(Throwable cause) {
        super(cause);
    }
}
