package manager;

import domain.Idable;
import domain.RelationEntity;
import javafx.util.Pair;
import model.ModelManyToMany;
import service.RemoteNotificationCenterService;
import service.ServiceManyToMany;
import transfarable.NotificationType;
import transfarable.RemoteNotification;
import translator.GenericTranslator;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ManyToManyManager<TransferT extends Idable<Id>, TransferU extends Idable<Id>,
        EntityT extends Idable<Id>,EntityU extends Idable<Id>,
        Relation extends RelationEntity<EntityT, EntityU> & Idable<Id>, Id extends Serializable>
        implements ServiceManyToMany<TransferT, TransferU, EntityT, EntityU, Relation, Id>{

    private final ModelManyToMany<EntityT, EntityU, Relation, Id> model;
    private final RemoteNotificationCenterService center;
    protected GenericTranslator<EntityT, TransferT> translatorT;
    protected GenericTranslator<EntityU, TransferU> translatorU;
    private final Supplier<RemoteException> excepted;

    public ManyToManyManager(ModelManyToMany<EntityT, EntityU, Relation, Id> model,
                             RemoteNotificationCenterService center) {
        this.model = model;
        this.center = center;
        excepted = () -> new RemoteException("Element Not Found");
    }

    @Override
    public TransferT insert(TransferT element) throws RemoteException {
        TransferT result = translatorT.translate(model.insert(translatorT.transform(element)));
        notifyClients();
        return result;
    }

    @Override
    public TransferT update(TransferT element, TransferT with) throws RemoteException {
        TransferT result = translatorT.translate(model.update(model.getElementById(element.getId())
                        .orElseThrow(() -> new RemoteException("Unable to find item...")),
                translatorT.transform(with)));
        notifyClients();
        return result;
    }
    @Override
    public TransferT delete(TransferT element) throws RemoteException {
        TransferT result = translatorT.translate(model.delete(translatorT.transform(element))
                .orElseThrow(() -> new RemoteException("Unable to delete element")));
        notifyClients();
        return result;
    }

    @Override
    public TransferT getElementById(Id id) throws RemoteException {
        return translatorT.translate(model.getElementById(id)
                .orElseThrow(() -> new RemoteException("Unable to find the element!")));
    }

    @Override
    public List<TransferT> all() throws RemoteException {
        return model.all().stream()
                .map(element -> translatorT.translate(element))
                .collect(Collectors.toList());
    }

    @Override public TransferU add(TransferU element) throws RemoteException {
        TransferU result = translatorU.translate(model.add(translatorU.transform(element)));
        notifyClients();
        return result;
    }

    @Override public TransferU refresh(TransferU element, TransferU with) throws RemoteException {
        TransferU result = translatorU.translate(model.refresh(model.receiveElementById(element.getId())
                        .orElseThrow(() -> new RemoteException("Unable to find item...")),
                translatorU.transform(with)));
        notifyClients();
        return result;
    }

    @Override public TransferU remove(TransferU element) throws RemoteException {
        TransferU result = translatorU.translate(model.remove(translatorU.transform(element))
                .orElseThrow(() -> new RemoteException("Unable to delete element")));
        notifyClients();
        return result;
    }

    @Override public TransferU receiveElementById(Id id) throws RemoteException {
        return translatorU.translate(model.receiveElementById(id)
                .orElseThrow(() -> new RemoteException("Unable to find the element!")));
    }

    @Override public List<TransferU> every() throws RemoteException {
        return model.every().stream()
                .map(element -> translatorU.translate(element))
                .collect(Collectors.toList());
    }

    @Override public Pair<TransferT, TransferU> insert(TransferT left, TransferU right) throws RemoteException {
        Pair<Optional<EntityT>, Optional<EntityU>> result =
                model.insert(model.getElementById(left.getId()).orElseThrow(excepted),
                model.receiveElementById(right.getId()).orElseThrow(excepted));
        notifyClients();
        return new Pair<>(translatorT.translate(result.getKey().orElseThrow(excepted)),
                translatorU.translate(result.getValue().orElseThrow(excepted)));
    }

    @Override public Pair<TransferT, TransferU> delete(TransferT left, TransferU right) throws RemoteException {
        Pair<Optional<EntityT>, Optional<EntityU>> result =
                model.delete(model.getElementById(left.getId()).orElseThrow(excepted),
                        model.receiveElementById(right.getId()).orElseThrow(excepted));
        notifyClients();
        return new Pair<>(translatorT.translate(result.getKey().orElseThrow(excepted)),
                translatorU.translate(result.getValue().orElseThrow(excepted)));
    }

    private void notifyClients() throws RemoteException {
        center.notifyAll(new RemoteNotification(NotificationType.DATA_UPDATE));
    }

}