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

public class LoginResponse implements ResponseProtocol {

    private User user;

    public LoginResponse(User user) {
        this.user = user;
    }

    @Override
    public ResponseType getType() {
        return ResponseType.LOGIN;
    }

    public User getUser() {
        return user;
    }
}
