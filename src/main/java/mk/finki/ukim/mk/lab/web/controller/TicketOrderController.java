package mk.finki.ukim.mk.lab.web.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.Production;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.service.MovieService;
import mk.finki.ukim.mk.lab.service.ProductionService;
import mk.finki.ukim.mk.lab.service.TicketOrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
@Controller
@RequestMapping("/ticketOrder")
public class TicketOrderController {
    private final MovieService movieService;
    private final TicketOrderService ticketOrderService;
    private final ProductionService productionService;

    public TicketOrderController(MovieService movieService, TicketOrderService ticketOrderService, ProductionService productionService) {
        this.movieService = movieService;
        this.ticketOrderService = ticketOrderService;
        this.productionService = productionService;
    }

    @GetMapping
    public String getTicketOrderPage(@RequestParam String movieTitle,
                                     @RequestParam int numTickets,
                                     @RequestParam String clientName,
                                     @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime dateCreated,
                                     Model model) {
        System.out.println(clientName);
        model.addAttribute("movieTitle", movieTitle);
        model.addAttribute("numTickets", numTickets);
        model.addAttribute("clientName", clientName);
        model.addAttribute("dateCreated", dateCreated);
        return "orderConfirmation";
    }
}