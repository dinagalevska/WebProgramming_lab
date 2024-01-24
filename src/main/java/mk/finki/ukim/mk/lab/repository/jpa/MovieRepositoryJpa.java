package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepositoryJpa extends JpaRepository<Movie, Long> {
//    List<Movie> findBySummary(String text);

    List<Movie> findByRating(Double rating);

//    void deleteById(long id);

    boolean findByTitle(String title);
    List<Movie> findByTitleLike(String text);

    List<Movie> findByPricesPrice(float price);
}
