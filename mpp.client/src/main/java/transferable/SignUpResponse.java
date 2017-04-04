package transferable;

import domain.User;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        03/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */


public class SignUpResponse implements ResponseProtocol {

    private User user;

    public SignUpResponse(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public ResponseType getType() {
        return ResponseType.SIGNUP;
    }
}
