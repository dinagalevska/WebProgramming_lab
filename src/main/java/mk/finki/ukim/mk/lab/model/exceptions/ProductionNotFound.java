package mk.finki.ukim.mk.lab.model.exceptions;

public class ProductionNotFound extends RuntimeException{
    public ProductionNotFound(Long id) {
        super(String.format("Production with id %d notFound", id));
    }
}
