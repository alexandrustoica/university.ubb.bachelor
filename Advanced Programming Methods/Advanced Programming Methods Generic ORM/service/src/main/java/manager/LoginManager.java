package manager;

import domain.UserEntity;
import dto.User;
import model.Model;
import org.jetbrains.annotations.NotNull;
import service.LoginService;
import translator.GenericTranslator;
import translator.UserTranslator;

import java.rmi.RemoteException;
import java.util.Optional;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class LoginManager implements LoginService {

    private final Model<UserEntity, Integer> model;
    private final GenericTranslator<UserEntity, User> translator;

    public LoginManager(final Model<UserEntity, Integer> model) throws RemoteException {
        super();
        this.model = model;
        this.translator = new UserTranslator();
    }

    @Override
    public User login(@NotNull String username,
                                @NotNull String password) throws RemoteException {
        Optional<User> result = findUser(username, password);
        return result.orElseThrow(() -> new RemoteException("Wrong username or password..."));
    }

    private Optional<User> findUser(@NotNull String username, @NotNull String password) {
        return model.all().stream()
                .map(translator::translate)
                .filter(user -> user.getPassword().equals(password) && user.getUsername().equals(username))
                .findFirst();
    }
}
