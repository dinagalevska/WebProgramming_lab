package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.jpa.ShoppingCartRepositoryJpa;
import mk.finki.ukim.mk.lab.repository.jpa.TicketRepositoryJpa;
import mk.finki.ukim.mk.lab.repository.jpa.UserRepositoryJpa;
import mk.finki.ukim.mk.lab.service.TicketOrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketOrderServiceImpl implements TicketOrderService {
    private final TicketRepositoryJpa ticketOrderRepository;
    private final ShoppingCartRepositoryJpa shoppingCartRepository;
    private final UserRepositoryJpa userRepository;
//    private final DiscountRepositoryJpa discountRepository;

    public TicketOrderServiceImpl(TicketRepositoryJpa ticketOrderRepository, ShoppingCartRepositoryJpa shoppingCartRepository, UserRepositoryJpa userRepository) {
        this.ticketOrderRepository = ticketOrderRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
//        this.discountRepository = discountRepository;
    }

    @Override
    public List<TicketOrder> searchByUser(String user) {
        return ticketOrderRepository.findByUserUsernameLike(user);
    }

    @Override
    public List<TicketOrder> findAllOrders() {
        return ticketOrderRepository.findAll();
    }

    @Override
    public void deleteOrder(Long id) {
        ticketOrderRepository.deleteById(id);
    }

    @Override
    public Optional<TicketOrder> findOrderById(long orderId) {
        return ticketOrderRepository.findById(orderId);
    }




    @Override
    public TicketOrder save(String movieTitle, String clientName, int numberOfTickets, LocalDateTime date) {
        System.out.println(movieTitle);
        System.out.println(clientName);
        System.out.println(numberOfTickets);
        if (movieTitle!=null&&clientName!=null&&numberOfTickets!=0){
            User user = userRepository.findById(clientName).orElseThrow();
            TicketOrder ticketOrder = new TicketOrder(movieTitle, numberOfTickets, user, date);
            ShoppingCart shoppingCart = null;
            if (shoppingCartRepository.findByUserUsername(clientName).isPresent()){
                shoppingCart = shoppingCartRepository.findByUserUsername(clientName).get();
                shoppingCart.getTicketOrders().add(ticketOrder);
            } else {
                List<TicketOrder> ticketOrders = new ArrayList<>();
                ticketOrders.add(ticketOrder);
                shoppingCart = new ShoppingCart(user,date,ticketOrders);
                shoppingCart.getTicketOrders().add(ticketOrder);
            }
            shoppingCartRepository.save(shoppingCart);
            return ticketOrderRepository.save(ticketOrder);
        } else throw new IllegalArgumentException();
    }

    @Override
    public Optional<TicketOrder> editOrder(Long id, long numTickets) {
        TicketOrder ticketOrder = ticketOrderRepository.findById(id).orElseThrow();
        ticketOrder.setNumberOfTickets((int) numTickets);
        return Optional.of(ticketOrderRepository.save(ticketOrder));
    }

    @Override
    public List<TicketOrder> searchOrdersBetweenDates(LocalDateTime from, LocalDateTime to) {
        return ticketOrderRepository.findByDateCreatedBetween(from,to);
    }

    @Override
    public List<TicketOrder> searchOrdersByTitle(String searchedTitle) {
        return ticketOrderRepository.findByMovieTitleLike(searchedTitle);
    }

//    @Override
//    public Optional<TicketOrder> editOrderByDiscount(Long id, long discountId) {
//        TicketOrder ticketOrder = ticketOrderRepository.findById(id).orElseThrow();
//        Discount discount = discountRepository.findById(discountId).orElseThrow();
//        float oldPrice = ticketOrder.getPrice();
//        float newPrice = oldPrice - discount.getPercent()/100 * oldPrice;
//        ticketOrder.setPrice(newPrice);
//        return Optional.of(ticketOrderRepository.save(ticketOrder));
//    }
}
