//package mk.finki.ukim.mk.lab.service.impl;
//
//import mk.finki.ukim.mk.lab.model.ShoppingCart;
//import mk.finki.ukim.mk.lab.model.TicketOrder;
//import mk.finki.ukim.mk.lab.model.User;
//import mk.finki.ukim.mk.lab.model.enumerations.ShoppingCartStatus;
//import mk.finki.ukim.mk.lab.model.exceptions.OrderAlreadyInSHoppingCart;
//import mk.finki.ukim.mk.lab.model.exceptions.ShoppingCartNotFoundException;
//import mk.finki.ukim.mk.lab.model.exceptions.TicketOrderNotFound;
//import mk.finki.ukim.mk.lab.model.exceptions.UserNotFoundException;
//import mk.finki.ukim.mk.lab.repository.jpa.ShoppingCartRepositoryJpa;
//import mk.finki.ukim.mk.lab.repository.jpa.TicketRepositoryJpa;
//import mk.finki.ukim.mk.lab.repository.jpa.UserRepositoryJpa;
//import mk.finki.ukim.mk.lab.service.ShoppingCartService;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class ShoppingCartServiceImpl implements ShoppingCartService {
//    private final ShoppingCartRepositoryJpa shoppingCartRepositoryJpa;
//    private final UserRepositoryJpa userRepositoryJpa;
//    private final TicketRepositoryJpa ticketOrder;
//
//    public ShoppingCartServiceImpl(ShoppingCartRepositoryJpa shoppingCartRepositoryJpa, UserRepositoryJpa userRepositoryJpa, TicketRepositoryJpa ticketOrder) {
//        this.shoppingCartRepositoryJpa = shoppingCartRepositoryJpa;
//        this.userRepositoryJpa = userRepositoryJpa;
//        this.ticketOrder = ticketOrder;
//    }
//
//    @Override
//    public List<TicketOrder> listAllOrdersInShoppingCart(Long cartId) {
//        Optional<ShoppingCart> shoppingCart = this.shoppingCartRepositoryJpa.findById(cartId);
//        if(shoppingCart.isEmpty()) throw new ShoppingCartNotFoundException(cartId);
//        return shoppingCart.get().getTicketOrders();
//    }
//
//    @Override
//    public ShoppingCart getActiveShoppingCart(String username) {
//        return this.shoppingCartRepositoryJpa
//                .findByUserUsernameAndStatus(username, ShoppingCartStatus.CREATED)
//                .orElseGet(() -> {
//                    User user = this.userRepositoryJpa.findByUsername(username)
//                            .orElseThrow(UserNotFoundException::new);
//                    ShoppingCart shoppingCart = new ShoppingCart(user);
//                    return this.shoppingCartRepositoryJpa.save(shoppingCart);
//                });
//
//    }
//
//    @Override
//    public ShoppingCart addOrderToShoppingCart(String username, Long orderID) {
//        TicketOrder order = this.ticketOrder.findById(orderID)
//                .orElseThrow(() -> new TicketOrderNotFound(orderID));
//        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
//        List<TicketOrder> orders = shoppingCart.getTicketOrders().stream()
//                .filter(i->i.getId().equals(orderID))
//                .collect(Collectors.toList());
//
//        if(orders.size() > 0) throw new OrderAlreadyInSHoppingCart(orderID, username);
//
//        shoppingCart.getTicketOrders().add(order);
//        return this.shoppingCartRepositoryJpa.save(shoppingCart);
//    }
//}
