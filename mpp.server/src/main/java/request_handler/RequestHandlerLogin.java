package request_handler;

import model.ModelUser;
import transferable.LoginRequest;
import transferable.LoginResponse;
import transferable.ResponseProtocol;


/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        03/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */


public class RequestHandlerLogin
        extends RequestHandlerDatabase
        implements InternalRequestHandlerProtocol {

    private final LoginRequest request;
    private LoginResponse response;

    public RequestHandlerLogin(LoginRequest request) {
        this.request = request;
    }

    @Override
    public ResponseProtocol solve() {
        response = new LoginResponse(null);
        String username = request.getUsername();
        String password = request.getPassword();
        ModelUser model = new ModelUser(getDatabaseURL());
        model.getAll().forEach(user -> {
            if ((user.getName().equals(username)) && user.getPassword().equals(password)) {
                response = new LoginResponse(user);
            }
        });
        return response;
    }

}
