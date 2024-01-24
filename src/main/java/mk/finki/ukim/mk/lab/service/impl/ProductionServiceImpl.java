package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Production;
import mk.finki.ukim.mk.lab.repository.jpa.ProductionRepositoryJpa;
import mk.finki.ukim.mk.lab.service.ProductionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductionServiceImpl implements ProductionService {

    private final ProductionRepositoryJpa productionRepository;

    public ProductionServiceImpl(ProductionRepositoryJpa productionRepository) {
        this.productionRepository = productionRepository;
    }


    @Override
    public List<Production> findAll() {
        return this.productionRepository.findAll();
    }

    @Override
    public Production findById(long id) {
        return this.productionRepository.findById(id).get();
    }
}
