package service;

import domain.Idable;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@SuppressWarnings("unused")
public interface ServiceRelational<TransferT extends Idable<Id>, EntityT extends Idable<Id>, Id extends Serializable>
        extends Remote {
    TransferT insert (TransferT element) throws RemoteException;
    TransferT update(TransferT element, TransferT with) throws RemoteException;
    TransferT delete(TransferT element) throws RemoteException;
    TransferT getElementById(Id id) throws RemoteException;
    List<TransferT> all() throws RemoteException;
}