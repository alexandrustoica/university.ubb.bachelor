package request;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        08/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class RequestLogin implements RequestProtocol {

    private String username;
    private String password;

    public RequestLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public RequestType getType() {
        return RequestType.LOGIN;
    }
}
