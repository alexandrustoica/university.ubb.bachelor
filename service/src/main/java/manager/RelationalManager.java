package manager;

import domain.Idable;
import model.Model;
import service.ServiceRelational;
import translator.GenericTranslator;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class RelationalManager<TransferT extends Idable<Id>, EntityT extends Idable<Id>, Id extends Serializable>
        implements ServiceRelational<TransferT, EntityT, Id> {

    protected final Model<EntityT, Id> model;
    protected GenericTranslator<EntityT, TransferT> translator;

    public RelationalManager(Model<EntityT, Id> model) {
        this.model = model;
    }

    @Override
    public TransferT insert(TransferT element) throws RemoteException {
        return translator.translate(model.insert(translator.transform(element)));
    }

    @Override
    public TransferT update(TransferT element, TransferT with) throws RemoteException {
        return translator.translate(model.update(translator.transform(element), translator.transform(with)));
    }

    @Override
    public TransferT delete(TransferT element) throws RemoteException {
        return translator.translate(model.delete(translator.transform(element))
                .orElseThrow(() -> new RemoteException("Unable to delete element")));
    }

    @Override
    public TransferT getElementById(Id id) throws RemoteException {
        return translator.translate(model.getElementById(id)
                .orElseThrow(() -> new RemoteException("Unable to find the element!")));
    }

    @Override
    public List<TransferT> all() throws RemoteException {
        return model.all().stream()
                .map(element -> translator.translate(element))
                .collect(Collectors.toList());
    }

}