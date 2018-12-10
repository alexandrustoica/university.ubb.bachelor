package translator;


import domain.UserEntity;
import dto.User;
import org.jetbrains.annotations.NotNull;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class UserTranslator implements GenericTranslator<UserEntity, User> {

    @Override
    public UserEntity transform(@NotNull User user) {
        return new UserEntity(user.getId(), user.getUsername(), user.getPassword());
    }

    @Override
    public User translate(@NotNull UserEntity user) {
        return new User(user.getId(), user.getUsername(), user.getPassword());
    }

}
