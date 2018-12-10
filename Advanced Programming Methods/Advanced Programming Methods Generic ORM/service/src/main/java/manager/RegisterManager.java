package manager;

import domain.LoggerEntity;
import domain.UserEntity;
import dto.User;
import model.Model;
import org.jetbrains.annotations.NotNull;
import service.RegisterService;
import translator.GenericTranslator;
import translator.UserTranslator;

import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class RegisterManager implements RegisterService {

    private final Model<UserEntity, Integer> model;
    private final Model<LoggerEntity, Integer> logger;
    private final GenericTranslator<UserEntity, User> translator;

    public RegisterManager(final Model<UserEntity, Integer> model,
                           final Model<LoggerEntity, Integer> logger) throws RemoteException {
        super();
        this.model = model;
        this.logger = logger;
        this.translator = new UserTranslator();
    }

    @Override public User register(@NotNull User user, @NotNull Integer id) throws RemoteException {
        String host = null;
        try {
            host = RemoteServer.getClientHost();
        } catch (ServerNotActiveException e) {
            e.printStackTrace();
        }
        logger.insert(new LoggerEntity(user.getId(), host, id));
        return user;
    }

    @Override public User unregister(@NotNull Integer id) throws RemoteException {
        LoggerEntity found = logger.all().stream()
                .filter(item -> item.getIdClient().equals(id))
                .findFirst()
                .orElseThrow(() -> new RemoteException("Unable to find the client's ID ..."));
        LoggerEntity deleted = logger.delete(found).orElseThrow(() -> new RemoteException("Unable to logout ..."));
        return translator.translate(model.getElementById(deleted.getIdUser())
                .orElseThrow(() -> new RemoteException("Unable to find the client's user")));
    }

    @Override public User get(@NotNull Integer id) throws RemoteException {
        LoggerEntity found = logger.all().stream()
                .filter(item -> item.getIdClient().equals(id))
                .findFirst()
                .orElseThrow(() -> new RemoteException("Unable to find the client's ID ..."));
        return translator.translate(model.getElementById(found.getIdUser())
                .orElseThrow(() -> new RemoteException("Unable to find the client's user")));
    }
}
