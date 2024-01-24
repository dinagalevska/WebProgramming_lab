package mk.finki.ukim.mk.lab.model.exceptions;

public class MovieAlreadyExistsException extends RuntimeException{
    public MovieAlreadyExistsException(){
        super("Movie already exists");
    }
}
