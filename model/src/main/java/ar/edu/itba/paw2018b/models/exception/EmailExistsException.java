package ar.edu.itba.paw2018b.models.exception;

public class EmailExistsException extends Throwable {
    public EmailExistsException(){
        super("There already exists a user with that email");
    }
}
