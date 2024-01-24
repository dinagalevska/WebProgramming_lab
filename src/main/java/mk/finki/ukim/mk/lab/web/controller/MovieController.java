package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.Production;
import mk.finki.ukim.mk.lab.service.MovieService;
import mk.finki.ukim.mk.lab.service.ProductionService;
import mk.finki.ukim.mk.lab.service.TicketOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final TicketOrderService ticketOrderService;
    private final ProductionService productionService;

    public MovieController(MovieService movieService, TicketOrderService ticketOrderService, ProductionService productionService) {
        this.movieService = movieService;
        this.ticketOrderService = ticketOrderService;
        this.productionService = productionService;
    }

    @GetMapping
    public String getMoviesPage(@RequestParam(required = false) String error, Model model){
        List<Movie> movies = movieService.listAll();
        model.addAttribute("movies", movies);
        model.addAttribute("error", error);
        return "listMovies";
    }

    @PostMapping("/add")
    public String saveMovie(@RequestParam String movieTitle, @RequestParam String summary, @RequestParam double rating, @RequestParam long productionid, @RequestParam float price){
        movieService.saveMovie(movieTitle,summary,rating,productionid, price);
        return "redirect:/movies";
    }

    @GetMapping("/edit-form/{movieId}")
    public String getEditMovieForm(@PathVariable(required = false) Long movieId,Model model){
        if (this.movieService.findMovieById(movieId).isPresent()){
            Movie movie = this.movieService.findMovieById(movieId).get();
            List<Production> productions = this.productionService.findAll();
            model.addAttribute("productions", productions);
            model.addAttribute("movie", movie);
            return "edit-movie";
        }
        return "redirect:/movies?error=MovieNotFound";
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable long id){
        this.movieService.deleteById(id);
        return "redirect:/movies";
    }

    @GetMapping("/add-form")
    public String getAddMoviePage(Model model){
        List<Production> productions = productionService.findAll();
        model.addAttribute("productions", productions);
        return "add-movie";
    }
    @PostMapping("/edit/{id}")
    public String editMovie(@PathVariable("id") Long movieId, @RequestParam String movieTitle,
                            @RequestParam String summary, @RequestParam Double rating, @RequestParam long productionId){
        movieService.edit(movieId,movieTitle,summary,rating,productionId);
        return "redirect:/movies";
    }
    @PostMapping("/add-order")
    public String TicketOrder(@RequestParam String selectedMovie, @RequestParam int numTickets, @RequestParam String clientName, @RequestParam LocalDateTime dateCreated, Model model){
        ticketOrderService.save(selectedMovie, clientName,numTickets, dateCreated);
        model.addAttribute("movieTitle",selectedMovie);
        model.addAttribute("numTickets",numTickets);
        model.addAttribute("clientName", clientName);
        model.addAttribute("dateCreated", dateCreated);
        return "redirect:/ticketOrder?movieTitle=" + selectedMovie + "&numTickets=" + numTickets+ "&clientName=" + clientName+"&dateCreated=" +dateCreated;
    }
}

