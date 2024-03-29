package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepositoryJpa extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByUserUsername(String username);
}
