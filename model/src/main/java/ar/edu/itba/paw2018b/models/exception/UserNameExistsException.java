package ar.edu.itba.paw2018b.models.exception;

public class UserNameExistsException extends RuntimeException {
    public UserNameExistsException () {
        super();
    }
    public UserNameExistsException (String s) {
        super(s);
    }
    public UserNameExistsException (String s, Throwable throwable) {
        super(s, throwable);
    }
    public UserNameExistsException (Throwable throwable) {
        super(throwable);
    }
}