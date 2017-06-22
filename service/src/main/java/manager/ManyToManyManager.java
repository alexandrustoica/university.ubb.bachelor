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
        notifyClients();
        return translatorT.translate(model.insert(translatorT.transform(element)));
    }


    @Override
    public TransferT update(TransferT element, TransferT with) throws RemoteException {
        notifyClients();
        return translatorT.translate(model.update(model.getElementById(element.getId())
                        .orElseThrow(() -> new RemoteException("Unable to find item...")),
                translatorT.transform(with)));
    }
    @Override
    public TransferT delete(TransferT element) throws RemoteException {
        notifyClients();
        return translatorT.translate(model.delete(translatorT.transform(element))
                .orElseThrow(() -> new RemoteException("Unable to delete element")));
    }

    @Override
    public TransferT getElementById(Id id) throws RemoteException {
        notifyClients();
        return translatorT.translate(model.getElementById(id)
                .orElseThrow(() -> new RemoteException("Unable to find the element!")));
    }

    @Override
    public List<TransferT> all() throws RemoteException {
        notifyClients();
        return model.all().stream()
                .map(element -> translatorT.translate(element))
                .collect(Collectors.toList());
    }

    @Override public TransferU add(TransferU element) throws RemoteException {
        notifyClients();
        return translatorU.translate(model.add(translatorU.transform(element)));
    }

    @Override public TransferU refresh(TransferU element, TransferU with) throws RemoteException {
        notifyClients();
        return translatorU.translate(model.refresh(model.receiveElementById(element.getId())
                        .orElseThrow(() -> new RemoteException("Unable to find item...")),
                translatorU.transform(with)));
    }

    @Override public TransferU remove(TransferU element) throws RemoteException {
        notifyClients();
        return translatorU.translate(model.remove(translatorU.transform(element))
                .orElseThrow(() -> new RemoteException("Unable to delete element")));
    }

    @Override public TransferU receiveElementById(Id id) throws RemoteException {
        notifyClients();
        return translatorU.translate(model.receiveElementById(id)
                .orElseThrow(() -> new RemoteException("Unable to find the element!")));
    }

    @Override public List<TransferU> every() throws RemoteException {
        notifyClients();
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