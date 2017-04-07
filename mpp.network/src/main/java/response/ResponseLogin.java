package response;

import domain.User;
import error.Errors;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        08/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ResponseLogin implements ResponseProtocol {

    private User user;
    private Errors errors;

    public ResponseLogin(Errors errors) {
        this.errors = errors;
    }

    public ResponseLogin(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Errors getErrors() {
        return errors;
    }

    @Override
    public ResponseType getType() {
        return ResponseType.LOGIN;
    }
}
