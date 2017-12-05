package store.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import store.domain.Invoice;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Component
@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Integer> { }
