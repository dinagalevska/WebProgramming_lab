package mk.finki.ukim.mk.lab.model.exceptions;


public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("User notFound");
    }

}
