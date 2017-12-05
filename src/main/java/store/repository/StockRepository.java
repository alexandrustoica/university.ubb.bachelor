package store.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import store.domain.Stock;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Component
@Repository
public interface StockRepository extends CrudRepository<Stock, Integer> { }
