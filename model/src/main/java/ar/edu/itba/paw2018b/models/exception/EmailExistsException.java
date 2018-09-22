package ar.edu.itba.paw2018b.models.exception;

public class EmailExistsException extends Throwable {
    public EmailExistsException(){
        super("There already exists a user with that email");
    }
    public EmailExistsException(String s) {
        super(s);
    }
    public EmailExistsException(String s, Throwable throwable) {
        super(s, throwable);
    }
    public EmailExistsException(Throwable throwable) {
        super(throwable);
    }
}
