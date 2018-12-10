package manager;

import domain.Idable;
import model.ModelOneToMany;
import service.ServiceOneToMany;
import translator.GenericTranslator;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.function.Supplier;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class OneToManyManager<TransferOne extends Idable<Id>, TransferMany extends Idable<Id>,
        EntityOne extends Idable<Id>, EntityMany extends Idable<Id>, Id extends Serializable>
        extends RelationalManager<TransferOne, EntityOne, Id>
        implements ServiceOneToMany<TransferOne, TransferMany, EntityOne, EntityMany, Id> {

    protected GenericTranslator<EntityMany, TransferMany> translatorMany;
    private ModelOneToMany<EntityOne, EntityMany, Id> model;
    private final Supplier<RemoteException> excepted;

    public OneToManyManager(ModelOneToMany<EntityOne, EntityMany, Id> model) throws RemoteException {
        super(model);
        this.model = model;
        excepted = () -> new RemoteException("Element Not Found!");
    }

    @Override public TransferOne insert(TransferOne element, TransferMany item) throws RemoteException {
        return translator.translate(model.insert(
                model.getElementById(element.getId()).orElseThrow(excepted),
                translatorMany.transform(item)).orElseThrow(excepted));
    }

    @Override public TransferOne delete(TransferOne element, TransferMany item) throws RemoteException {
        return translator.translate(model.delete(
                model.getElementById(element.getId()).orElseThrow(excepted),
                translatorMany.transform(item)).orElseThrow(excepted));
    }

    @Override
    public TransferOne update(TransferOne element, TransferMany item, TransferMany with) throws RemoteException {
        return translator.translate(model.update(
                model.getElementById(element.getId()).orElseThrow(excepted),
                translatorMany.transform(item), translatorMany.transform(with)).orElseThrow(excepted));
    }
}