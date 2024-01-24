package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.TicketOrder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TicketOrderService{
    List<TicketOrder> searchByUser(String user);
    List<TicketOrder> findAllOrders();
    void deleteOrder(Long id);
    Optional<TicketOrder> findOrderById(long orderId);
    TicketOrder save(String movieTitle, String clientName, int numberOfTickets, LocalDateTime date);
    Optional<TicketOrder> editOrder(Long id, long numTickets);
    List<TicketOrder> searchOrdersBetweenDates(LocalDateTime from, LocalDateTime to);

    List<TicketOrder> searchOrdersByTitle(String searchedTitle);
//    Optional<TicketOrder> editOrderByDiscount(Long id, long discountId);
}