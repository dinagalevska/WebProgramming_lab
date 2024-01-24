package mk.finki.ukim.mk.lab.repository.inmemory;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.Production;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MovieRepository {
    public List<Movie> findAll(){
        return DataHolder.movies;
    }
    public List<Movie> searchMovies(String text){
        return DataHolder.movies.stream().filter(movie -> movie.getTitle().contains(text) || movie.getSummary().contains(text))
                .toList();
    }
    public List<Movie> searchMovies(String text, float rating){
        return DataHolder.movies.stream().filter(movie -> movie.getRating() >= rating || (movie.getTitle().contains(text))).collect(Collectors.toList());
    }

    public Optional<Movie> findByID(Long id) {
        return DataHolder.movies.stream()
                .filter(movie -> movie.getId().equals(id)).findFirst();
    }

    public Optional<Movie> findByName(String name) {
        return DataHolder.movies.stream()
                .filter(movie -> movie.getTitle().equals(name)).findFirst();
    }

    public Optional<Movie> save(String title, String summary, double rating, Production production) {
        if (production == null) {
            throw new IllegalArgumentException();
        }
        Movie movie = new Movie(title, summary, rating, production);
        DataHolder.movies.removeIf(mv -> mv.getTitle().equals(title));
        DataHolder.movies.add(movie);
        return Optional.of(movie);
    }

    public void deleteById(Long id) {
        DataHolder.movies.removeIf(movie -> movie.getId().equals(id));
    }
}
