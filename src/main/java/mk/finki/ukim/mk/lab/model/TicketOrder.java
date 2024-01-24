package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Entity
//@Table(name = "ticket_orders")
public class TicketOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String movieTitle;
    private int numberOfTickets;
    @ManyToOne
    private User user;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateCreated;
    private float price;


//    @ManyToOne
//    private Discount discount;

    public TicketOrder(){}

    public TicketOrder(String movieTitle, int numberOfTickets, User user, LocalDateTime dateCreated) {
        this.movieTitle = movieTitle;
        this.numberOfTickets = numberOfTickets;
        this.user=user;
        this.dateCreated=dateCreated;
    }


}
