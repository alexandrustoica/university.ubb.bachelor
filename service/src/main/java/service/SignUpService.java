package service;

import org.jetbrains.annotations.NotNull;
import transfarable.User;

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
