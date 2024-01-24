package mk.finki.ukim.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.Production;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.jpa.MovieRepositoryJpa;
import mk.finki.ukim.mk.lab.repository.jpa.ProductionRepositoryJpa;
import mk.finki.ukim.mk.lab.repository.jpa.TicketRepositoryJpa;
import mk.finki.ukim.mk.lab.repository.jpa.UserRepositoryJpa;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Movie> movies = null;
    public static List<TicketOrder> ticketOrders = null;
    public static List<Production> productions = null;
    public static List<User> users = null;
    private final MovieRepositoryJpa movieRepository;
    private final TicketRepositoryJpa ticketOrderRepository;
    private final ProductionRepositoryJpa productionRepository;
    private final UserRepositoryJpa userRepository;

    public DataHolder(MovieRepositoryJpa movieRepository, TicketRepositoryJpa ticketOrderRepository, ProductionRepositoryJpa productionRepository, UserRepositoryJpa userRepository) {
        this.movieRepository = movieRepository;
        this.ticketOrderRepository = ticketOrderRepository;
        this.productionRepository = productionRepository;
        this.userRepository = userRepository;
    }


    @PostConstruct
    public void init(){
        movies = new LinkedList<>();
        ticketOrders = new LinkedList<>();
        productions = new LinkedList<>();
        users = new LinkedList<>();
        Production p1 = new Production("Production 1", "Production country 1", "production address 1");
        Production p2 = new Production("Production 2", "Production country 2", "production address 2");
        Production p3 = new Production("Production 3", "Production country 3", "production address 3");
        Production p4 = new Production("Production 4", "Production country 4", "production address 4");
        Production p5 = new Production("Production 5", "Production country 5", "production address 5");
        User u1 = new User("dina", "Dina", "Galevska");
        User u2 = new User("bojana", "Bojana", "Jancheska");
        if (productionRepository.count()==0){
            productions.add(p1);
            productions.add(p2);
            productions.add(p3);
            productions.add(p4);
            productions.add(p5);
            productionRepository.saveAll(productions);
        }

        if (userRepository.count()==0){
            users.add(u1);
            users.add(u2);
            userRepository.saveAll(users);
        }

        if (movieRepository.count()==0){
        movies.add(new Movie("Barbie", "feminist movie", 6.0, p1));
        movies.add(new Movie("Oppenheimer", "excellent movie", 7.5, p1));
        movies.add(new Movie("Spider-Man", "good movie", 9.0,p2));
        movies.add(new Movie("Lady Bird", "good movie", 9.9,p1));
        movies.add(new Movie("Everything Everywhere All at Once", "excellent movie", 9.9, p1));
        movies.add(new Movie("Legally Blonde", "good movie", 7.9, p3));
        movies.add(new Movie("The Notebook", "good romance", 8.9, p3));
        movies.add(new Movie("Midsommar", "good horror", 6.9, p4));
        movies.add(new Movie("The Maze Runner", "great movie", 7.5, p5));
        movies.add(new Movie("Shutter Island", "great movie", 6.8, p4));
            movieRepository.saveAll(movies);
        }
//        if (ticketOrderRepository.count()==0){
//            ticketOrders.add(new TicketOrder("When Harry met Sally", 1));
//            ticketOrders.add(new TicketOrder("Call me by your name",  8));
//            ticketOrders.add(new TicketOrder("Titanic",  6));
//            ticketOrders.add(new TicketOrder("The Matrix",  5));
//            ticketOrders.add(new TicketOrder("Armageddon",  4));
//            ticketOrders.add(new TicketOrder("Forrest Gump",  2));
//            ticketOrders.add(new TicketOrder("Napoleon",  3));
//            ticketOrders.add(new TicketOrder("Barbie",  7));
//            ticketOrders.add(new TicketOrder("James Bond",  5));
//            ticketOrderRepository.saveAll(ticketOrders);
//        }



    }


//    public static List<Movie> movies = null;
//    public static List<Production> productions = null;
//    public static List<TicketOrder> ticketOrders = null;
//
//    @PostConstruct
//    public void init() {
//        movies = new ArrayList<>();
//        productions = new ArrayList<>();
//        ticketOrders = new ArrayList<>();
//
//        productions.add(new Production("Production 1", "Production country 1", "production address 1"));
//        productions.add(new Production("Production 2", "Production country 2", "production address 2"));
//        productions.add(new Production("Production 3", "Production country 3", "production address 3"));
//        productions.add(new Production("Production 4", "Production country 4", "production address 4"));
//        productions.add(new Production("Production 5", "Production country 5", "production address 5"));
//
//        movies.add(new Movie("Barbie", "feminist movie", 6.0, productions.get(0)));
//        movies.add(new Movie("Oppenheimer", "excellent movie", 7.5, productions.get(1)));
//        movies.add(new Movie("Spider-Man", "good movie", 9.0,productions.get(2)));
//        movies.add(new Movie("Lady Bird", "good movie", 9.9,productions.get(0)));
//        movies.add(new Movie("Everything Everywhere All at Once", "excellent movie", 9.9, productions.get(2)));
//        movies.add(new Movie("Legally Blonde", "good movie", 7.9, productions.get(3)));
//        movies.add(new Movie("The Notebook", "good romance", 8.9, productions.get(3)));
//        movies.add(new Movie("Midsommar", "good horror", 6.9, productions.get(4)));
//        movies.add(new Movie("The Maze Runner", "great movie", 7.5, productions.get(3)));
//        movies.add(new Movie("Shutter Island", "great movie", 6.8, productions.get(1)));
//    }
}
