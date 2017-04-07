package request;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        07/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class RequestSignUp implements RequestProtocol {

    private String username;
    private String password;
    private String confirm;

    public RequestSignUp(String username, String password, String confirm) {
        this.username = username;
        this.password = password;
        this.confirm = confirm;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirm() {
        return confirm;
    }

    @Override
    public RequestType getType() {
        return RequestType.SIGN_UP;
    }

}
