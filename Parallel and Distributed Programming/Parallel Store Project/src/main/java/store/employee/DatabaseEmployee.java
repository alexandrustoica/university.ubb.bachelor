package store.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import store.domain.Invoice;
import store.domain.Stock;
import store.repository.InvoiceRepository;

@Component
@Qualifier("databaseEmployee")
public final class DatabaseEmployee extends EmployeeDecorator {

    private final InvoiceRepository invoiceRepository;

    @Autowired
    public DatabaseEmployee(@Qualifier("cashier") final Employee employee,
                            InvoiceRepository invoiceRepository) {
        super(employee);
        invoiceRepository.findAll().forEach(employee::addInvoice);
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Invoice sell(Stock stock) {
        Invoice invoice = employee.sell(stock);
        invoiceRepository.save(invoice);
        return invoice;
    }
}
