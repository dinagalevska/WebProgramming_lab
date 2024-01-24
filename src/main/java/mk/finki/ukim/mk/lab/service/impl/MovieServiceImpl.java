package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.Production;
import mk.finki.ukim.mk.lab.model.exceptions.MovieAlreadyExistsException;
import mk.finki.ukim.mk.lab.repository.jpa.MovieRepositoryJpa;
import mk.finki.ukim.mk.lab.repository.jpa.ProductionRepositoryJpa;
import mk.finki.ukim.mk.lab.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class MovieServiceImpl implements MovieService {
    private final MovieRepositoryJpa movieRepository;
    private final ProductionRepositoryJpa productionRepository;

    public MovieServiceImpl(MovieRepositoryJpa movieRepository, ProductionRepositoryJpa productionRepository) {
        this.movieRepository = movieRepository;
        this.productionRepository = productionRepository;
    }


    @Override
    public List<Movie> listAll() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Movie> searchMovies(String text) {
        return movieRepository.findByTitleLike(text);
    }
    @Override
    public List<Movie> searchMoviesByRating(String text) {
        return movieRepository.findByRating(Double.valueOf(text));
    }

    @Override
    public Movie addNewMovie(Movie movie) {
        if (this.movieRepository.findByTitle(movie.getTitle())) throw new MovieAlreadyExistsException();

        return movieRepository.save(movie);
    }

    @Override
    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }


    @Override
    public Optional<Movie> findMovieById(Long movieId) {
        return movieRepository.findById(movieId);
    }

    @Override
    public Movie saveMovie(String movieTitle, String summary, Double rating, long productionId, float price) {
        if (movieTitle.isEmpty()||summary.isEmpty()||rating<0||productionRepository.findById(productionId)==null) throw new IllegalArgumentException();
        return movieRepository.save(new Movie(movieTitle,summary,rating,productionRepository.findById(productionId).get()));
    }



    @Override
    public Optional<Movie> edit(Long id, String movieTitle, String summary, double rating, long productionId) {
        Movie movie = movieRepository.findById(id).orElseThrow();
        Production production = productionRepository.findById(productionId).orElseThrow();
        movie.setTitle(movieTitle);
        movie.setSummary(summary);
        movie.setRating(rating);
        movie.setProduction(production);
        movieRepository.save(movie);
        return Optional.of(movie);
    }
}
