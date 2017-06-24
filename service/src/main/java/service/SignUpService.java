package service;

import dto.User;
import org.jetbrains.annotations.NotNull;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface SignUpService extends Remote {
    User signUp(@NotNull final String username,
                @NotNull final String password,
                @NotNull final String confirm) throws RemoteException;

}
