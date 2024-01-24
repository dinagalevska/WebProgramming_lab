package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepositoryJpa extends JpaRepository<Price, Long> {

}
