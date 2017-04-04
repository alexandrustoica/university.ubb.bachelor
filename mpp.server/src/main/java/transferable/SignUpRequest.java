package transferable;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        03/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */


public class SignUpRequest implements RequestProtocol {

    private String username;
    private String password;
    private String confirm;

    public SignUpRequest(String username, String password, String confirm) {
        this.password = password;
        this.username = username;
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
        return RequestType.SIGNUP;
    }
}
