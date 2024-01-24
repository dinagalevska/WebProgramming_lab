package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.TicketOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface TicketRepositoryJpa extends JpaRepository<TicketOrder, Long> {
    void deleteById(Long id);

    List<TicketOrder> findByUserUsernameLike(String text);

    List<TicketOrder> findByDateCreatedBetween(LocalDateTime from, LocalDateTime to);
    List<TicketOrder> findByMovieTitle(String title);

    List<TicketOrder> findByMovieTitleLike(String searchedTitle);
}
