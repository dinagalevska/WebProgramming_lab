package mk.finki.ukim.mk.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.service.MovieService;
import mk.finki.ukim.mk.lab.service.TicketOrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "movieListServlet", urlPatterns = "")
@AllArgsConstructor
public class MovieListServlet extends HttpServlet {
    private final MovieService movieService;
    private final SpringTemplateEngine springTemplateEngine;
    private final TicketOrderService ticketOrderService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange iWebExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req,resp);

        WebContext context = new WebContext(iWebExchange);
        context.setVariable("movies", movieService.listAll());

        String byName = req.getParameter("search");
        String byRating = req.getParameter("rating");

        if (byName!=null && !byName.isEmpty() && byRating!=null && !byRating.isEmpty()){
//            List<Movie> filteredByRating = movieService.searchMovies(byName, Float.parseFloat(byRating));
//            context.setVariable("movies", filteredByRating);
        }
        else context.setVariable("movies", movieService.listAll());

        springTemplateEngine.process("listMovies.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("movieTitle");
        String name = req.getParameter("name");
        int tickets = Integer.parseInt(req.getParameter("numTickets"));
        resp.sendRedirect("/ticketOrder?movieTitle="+title + "&name=" + name + "&tickets=" + tickets);
    }
}
