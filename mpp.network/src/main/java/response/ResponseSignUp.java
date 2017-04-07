package response;

import domain.User;
import error.Errors;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        07/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */


public class ResponseSignUp implements ResponseProtocol {

    private User user;
    private Errors errors;

    public ResponseSignUp(Errors errors) {
        this.errors = errors;
    }

    public ResponseSignUp(User user) {
        this.user = user;
    }

    @Override
    public ResponseType getType() {
        return ResponseType.SIGN_UP;
    }

    public User getUser() {
        return user;
    }

    public Errors getErrors() {
        return errors;
    }

}
