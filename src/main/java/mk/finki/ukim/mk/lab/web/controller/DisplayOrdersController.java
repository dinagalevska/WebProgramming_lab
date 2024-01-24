package mk.finki.ukim.mk.lab.web.controller;

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
@RequestMapping("/displayOrders")
public class DisplayOrdersController {
    private final MovieService movieService;
    private final TicketOrderService ticketOrderService;
    private final ProductionService productionService;

    public DisplayOrdersController(MovieService movieService, TicketOrderService ticketOrderService, ProductionService productionService) {
        this.movieService = movieService;
        this.ticketOrderService = ticketOrderService;
        this.productionService = productionService;
    }
    @GetMapping
    public String getOrdersPage(@RequestParam(required = false) String searchedTitle,
                                @RequestParam(required = false) String searchedUser,
                                @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime dateFrom,
                                @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime dateTo,
                                Model model) {
        List<TicketOrder> orders;

        if (searchedTitle != null && !searchedTitle.isEmpty()) {
            System.out.println("TEST 1");
            orders = ticketOrderService.searchOrdersByTitle(searchedTitle);
        } else if (searchedUser != null && !searchedUser.isEmpty()) {
            System.out.println("TEST 2");
            orders = ticketOrderService.searchByUser(searchedUser);
        } else if (dateFrom!=null && dateTo!=null) {
            System.out.println("TEST 3");
            orders = ticketOrderService.searchOrdersBetweenDates(dateFrom,dateTo);
        } else {
            System.out.println("TETS 4");
            orders = ticketOrderService.findAllOrders();
            System.out.println(orders);
        }
        System.out.println(orders);
        model.addAttribute("ticketOrders", orders);
        return "listOrders";
    }
    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable int id){
        this.ticketOrderService.deleteOrder((long) id);
        return "redirect:/displayOrders";
    }
    @PostMapping("/edit")
    public String saveOrder(@RequestParam (required = false) String orderId,
                            @RequestParam (required = false) int numTickets,
                            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime date
    ){
//        this.ticketOrderService.save(movieTitle,clientName, numTickets,date);
//        System.out.println("ORDER");
        this.ticketOrderService.editOrder(Long.valueOf(orderId), numTickets);
        return "redirect:/displayOrders";
    }
    @GetMapping("/edit-order/{orderId}")
    public String editOrder(@PathVariable(required = false) int orderId,Model model){
        if (this.ticketOrderService.findOrderById(orderId).isPresent()){
            TicketOrder order = this.ticketOrderService.findOrderById(orderId).get();
            model.addAttribute("order", order);
            model.addAttribute("movieTitle", order.getMovieTitle());
            model.addAttribute("numTickets", order.getNumberOfTickets());
            return "edit-order";
        }
        return "redirect:/displayOrders?error=OrderNotFound";
    }
}


