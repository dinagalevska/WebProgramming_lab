package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.Production;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<Movie> listAll();
    Optional<Movie> findById(Long id);
    List<Movie> searchMovies(String text);
    List<Movie> searchMoviesByRating(String rating);
    Movie addNewMovie(Movie movie);
    void deleteById(Long id);
    Optional<Movie> findMovieById(Long movieId);
    Movie saveMovie(String movieTitle, String summary, Double rating, long productionId, float price);
    Optional<Movie> edit(Long id, String movieTitle, String summary, double rating, long productionId);


}
