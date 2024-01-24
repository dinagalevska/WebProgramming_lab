package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Data
@Entity
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateFrom;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateTo;

    float price;

    @ManyToOne
    Movie movie;

    public Price(LocalDateTime dateFrom, LocalDateTime dateTo, float price) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.price = price;
    }

    public Price() {
    }
}
