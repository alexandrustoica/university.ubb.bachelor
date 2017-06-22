package service;

import domain.Idable;
import domain.RelationEntity;
import javafx.util.Pair;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface ServiceManyToMany<TransferT extends Idable<Id>, TransferU extends Idable<Id>,
        EntityT extends Idable<Id>, EntityU extends Idable<Id>,
        Relation extends RelationEntity<EntityT, EntityU> & Idable<Id>,
        Id extends Serializable> extends Remote {
    TransferT insert(TransferT element) throws RemoteException;
    TransferT update(TransferT element, TransferT with) throws RemoteException;
    TransferT delete(TransferT element) throws RemoteException;
    TransferT getElementById(Id id) throws RemoteException;
    List<TransferT> all() throws RemoteException;
    TransferU add(TransferU element) throws RemoteException;
    TransferU refresh(TransferU element, TransferU with) throws RemoteException;
    TransferU remove(TransferU element) throws RemoteException;
    TransferU receiveElementById(Id id) throws RemoteException;
    List<TransferU> every() throws RemoteException;
    List<TransferU> everyFrom(TransferT element) throws RemoteException;
    List<TransferT> allFrom(TransferU element) throws RemoteException;
    Pair<TransferT, TransferU> insert(TransferT left, TransferU right) throws RemoteException;
    Pair<TransferT, TransferU> delete(TransferT left, TransferU right) throws RemoteException;
}