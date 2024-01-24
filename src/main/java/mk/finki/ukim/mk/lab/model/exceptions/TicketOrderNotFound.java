package mk.finki.ukim.mk.lab.model.exceptions;

public class TicketOrderNotFound extends RuntimeException{
    public TicketOrderNotFound(Long id) {
        super(String.format("Order with id %d notFound", id));
    }
}
