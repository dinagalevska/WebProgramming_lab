package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String summary;
    private Double rating;
    @ManyToOne
    @JoinColumn(name = "movies")
    private Production production;

    @OneToMany
    List<Price> prices;

    public Movie() {
    }

    public Movie(String title, String summary, Double rating, Production production) {
        this.title = title;
        this.summary = summary;
        this.rating = rating;
        this.production = production;
//        this.prices = new ArrayList<>();
    }
}
