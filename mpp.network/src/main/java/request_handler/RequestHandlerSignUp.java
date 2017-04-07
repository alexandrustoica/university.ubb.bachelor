package request_handler;

import domain.User;
import error.Error;
import error.Errors;
import model.ModelUser;
import request.RequestSignUp;
import response.ResponseProtocol;
import response.ResponseSignUp;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        07/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class RequestHandlerSignUp
        extends RequestHandlerDatabase
        implements InternalRequestHandlerProtocol {

    private RequestSignUp request;
    private Errors errors;

    @SuppressWarnings("all")
    public RequestHandlerSignUp(RequestSignUp request) {
        this.request = request;
        this.errors = new Errors();
    }

    private Boolean checkUsername(String username, ModelUser model) {
        for (User user: model.getAll()) {
            if (user.getName().equals(username)) {
                errors.add(new Error("Username already in use!"));
                return false;
            }
        }
        return true;
    }

    private Boolean checkPassword(String password, String confirm) {
        if (!password.equals(confirm)) {
            errors.add(new Error("Passwords don't match!"));
            return false;
        }
        return true;
    }

    @Override
    public ResponseProtocol solve() {
        ModelUser model = new ModelUser(getDatabaseURL());
        if (checkUsername(request.getUsername(), model) &&
                checkPassword(request.getPassword(), request.getConfirm())) {
            User user = new User(request.getUsername(), request.getPassword());
            int id = model.add(user);
            User result = model.getElementById(id);
            return new ResponseSignUp(result);
        }
        return new ResponseSignUp(errors);
    }

}
