package store.deposit;


import jdk.nashorn.internal.ir.annotations.Immutable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Immutable
@Component
public class DepositDecorator<T, Id>
        implements Deposit<T, Id> {

    protected final Deposit<T, Id> deposit;

    @Autowired
    public DepositDecorator(@Qualifier("stockDeposit") Deposit<T, Id> deposit) {
        this.deposit = deposit;
    }

    @Override
    public T deposit(T element) {
        return deposit.deposit(element);
    }

    @Override
    public Optional<T> remove(T element) {
        return deposit.remove(element);
    }

    @Override
    public Optional<T> findById(Id id) {
        return deposit.findById(id);
    }

    @Override
    public List<T> elements() {
        return deposit.elements();
    }
}
