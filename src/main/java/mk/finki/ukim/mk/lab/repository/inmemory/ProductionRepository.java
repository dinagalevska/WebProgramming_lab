package mk.finki.ukim.mk.lab.repository.inmemory;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Production;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductionRepository {
    public List<Production> findAll() {
        return DataHolder.productions;
    }

    public Optional<Production> findById(Long id) {
        return DataHolder.productions.stream().filter(production -> production.getId().equals(id)).findFirst();
    }
}
