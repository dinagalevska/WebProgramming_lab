package mk.finki.ukim.mk.lab.repository.inmemory;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TicketRepository {
    public List<TicketOrder> listAll(){
        return DataHolder.ticketOrders;
    }
    public TicketOrder add(TicketOrder ticketOrder){
        DataHolder.ticketOrders.add(ticketOrder);
        return ticketOrder;
    }

    public void deleteByTitle(Long id) {
        DataHolder.ticketOrders.removeIf(ticketOrder -> ticketOrder.getId().equals(id));
    }
    public Optional<TicketOrder> findByID(Long id) {
        return DataHolder.ticketOrders.stream()
                .filter(movie -> movie.getId().equals(id)).findFirst();
    }
}
