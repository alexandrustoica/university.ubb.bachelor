package request_handler;

import domain.User;
import error.Error;
import error.Errors;
import model.ModelUser;
import request.RequestLogin;
import response.ResponseLogin;
import response.ResponseProtocol;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        07/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class RequestHandlerLogin
        extends RequestHandlerDatabase
        implements InternalRequestHandlerProtocol {

    private RequestLogin request;
    private Errors errors;

    @SuppressWarnings("all")
    public RequestHandlerLogin(RequestLogin request) {
        this.request = request;
        this.errors = new Errors();
    }

    @Override
    public ResponseProtocol solve() {
        ModelUser model = new ModelUser(getDatabaseURL());
        for (User user : model.getAll()) {
            if (user.getName().equals(request.getUsername()) &&
                    user.getPassword().equals(request.getPassword())) {
                return new ResponseLogin(user);
            }
        }
        errors.add(new Error("Wrong username or password!"));
        return new ResponseLogin(errors);
    }

}
