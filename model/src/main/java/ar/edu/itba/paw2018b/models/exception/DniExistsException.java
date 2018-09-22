package ar.edu.itba.paw2018b.models.exception;

public class DniExistsException extends RuntimeException {
    public DniExistsException () {
        super();
    }
    public DniExistsException (String s) {
        super(s);
    }
    public DniExistsException (String s, Throwable throwable) {
        super(s, throwable);
    }
    public DniExistsException (Throwable throwable) {
        super(throwable);
    }
}
