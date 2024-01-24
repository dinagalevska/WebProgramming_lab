package mk.finki.ukim.mk.lab.model.exceptions;

public class OrderAlreadyInSHoppingCart extends RuntimeException{
    public OrderAlreadyInSHoppingCart(Long id, String message) {
        super(String.format("Order with id %d already exists in shopping cart for user with username %s", id, message));
    }
}
