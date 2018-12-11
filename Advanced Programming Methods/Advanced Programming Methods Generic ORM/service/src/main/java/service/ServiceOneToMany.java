package service;

import domain.Idable;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */


@SuppressWarnings("unused")
public interface ServiceOneToMany<TransferOne extends Idable<Id>, TransferMany extends Idable<Id>,
        EntityOne extends Idable<Id>, EntityMany extends Idable<Id>, Id extends Serializable>
        extends ServiceRelational<TransferOne, EntityOne, Id> {
    TransferOne insert(TransferOne element, TransferMany item) throws RemoteException;
    TransferOne delete(TransferOne element, TransferMany item) throws RemoteException;
    TransferOne update(TransferOne element, TransferMany item, TransferMany with) throws RemoteException;
}