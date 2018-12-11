package service;

import dto.User;
import org.jetbrains.annotations.NotNull;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface LoginService extends Remote {
    User login(@NotNull final String username,
               @NotNull final String password) throws RemoteException;
}
