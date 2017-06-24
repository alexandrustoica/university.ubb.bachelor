package service;

import dto.User;
import org.jetbrains.annotations.NotNull;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface RegisterService extends Remote {
    User register(@NotNull final User user, @NotNull final Integer id) throws RemoteException;
    User unregister(@NotNull final Integer id) throws RemoteException;
    User get(@NotNull final Integer id) throws RemoteException;
}
