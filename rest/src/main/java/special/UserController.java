package special;

import controller.GenericController;
import domain.UserEntity;
import dto.User;
import model.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import translator.UserTranslator;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user/")
public class UserController extends GenericController<User, UserEntity, Integer> {

    public UserController(final Model<UserEntity, Integer> model) {
        super(model);
        this.translator = new UserTranslator();
    }
}
