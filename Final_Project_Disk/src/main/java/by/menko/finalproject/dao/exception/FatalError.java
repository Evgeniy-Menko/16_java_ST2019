package by.menko.finalproject.dao.exception;

public class FatalError  extends RuntimeException {


    public FatalError() {
        super();
    }


    public FatalError(final String message) {
        super(message);
    }


    public FatalError(final String message, final Throwable cause) {
        super(message, cause);
    }

    public FatalError(final Throwable cause) {
        super(cause);
    }
}