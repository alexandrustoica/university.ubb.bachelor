package request_handler;

import domain.User;
import model.ModelUser;
import network.RequestHandler;
import transferable.ResponseProtocol;
import transferable.SignUpRequest;
import transferable.SignUpResponse;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        03/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class RequestHandlerSignUp
        extends RequestHandlerDatabase
        implements InternalRequestHandlerProtocol{

    private SignUpRequest request;

    public RequestHandlerSignUp(SignUpRequest request) {
        this.request = request;
    }

    private Boolean validateName(String username, ModelUser model) {
        for (User user: model.getAll()) {
            if (user.getName().equals(username)) {
                return false;
            }
        }
        return true;
    }

    private Boolean validatePassword(String password, String confirm) {
        return password.equals(confirm);
    }

    @Override
    public ResponseProtocol solve() {
        SignUpResponse response = new SignUpResponse(null);
        String username = request.getUsername();
        String password = request.getPassword();
        String confirm = request.getConfirm();
        ModelUser model = new ModelUser(getDatabaseURL());
        if (validateName(username, model) && validatePassword(password, confirm)) {
            User user = new User(username, password);
            int id = model.add(user);
            User result = model.getElementById(id);
            response = new SignUpResponse(result);
        }
        return response;
    }

}
