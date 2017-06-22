package manager;

import domain.UserEntity;
import model.Model;
import org.jetbrains.annotations.NotNull;
import service.SignUpService;
import transfarable.User;
import translator.GenericTranslator;
import translator.UserTranslator;

import java.rmi.RemoteException;
import java.util.Optional;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class SignUpManager implements SignUpService {

    private final Model<UserEntity, Integer> model;
    private final GenericTranslator<UserEntity, User> translator;

    public SignUpManager(final Model<UserEntity, Integer> model) throws RemoteException {
        super();
        this.model = model;
        this.translator = new UserTranslator();
    }

    private Boolean existsUser(@NotNull String username) {
        return model.all().stream()
                .map(translator::translate)
                .anyMatch(user -> user.getUsername().equals(username));
    }

    private Optional<UserEntity> findUser(@NotNull final String username,
                                          @NotNull final String password,
                                          @NotNull final String confirm) {
        if (!existsUser(username) && password.equals(confirm)) {
            return Optional.ofNullable(model.insert(new UserEntity(username, password)));
        }
        return Optional.empty();
    }

    @Override
    public User signUp(@NotNull String username, @NotNull String password, @NotNull String confirm) throws RemoteException {
        return translator.translate(findUser(username, password, confirm)
                .orElseThrow(() -> new RemoteException("Username already in use...")));
    }
}
