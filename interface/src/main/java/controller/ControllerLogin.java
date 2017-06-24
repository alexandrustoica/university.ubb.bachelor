package controller;

import dto.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import manager.PerformerManager;
import manager.StageManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import service.LoginService;
import service.RegisterService;
import view.ViewType;

import java.rmi.RemoteException;
import java.util.Optional;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
public class ControllerLogin extends PerformerManager implements ControllerInterface {

    private static Logger logger;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Label errorLabel;

    @FXML
    private ImageView backgroundImage;

    @FXML
    private StackPane backgroundImagePane;

    @Lazy
    @Autowired
    private StageManager manager;

    @Lazy
    @Autowired
    private LoginService loginService;

    @Lazy
    @Autowired
    private RegisterService registerService;

    @Autowired
    private Integer id;

    @Override
    public void initialize() {
        logger = Logger.getLogger(ControllerLogin.class);
    }

    @FXML
    void onLogoButtonClick() { }

    @FXML
    void onSignUpButtonClick()  {
        manager.switchScene(ViewType.SIGN_UP);
    }

    @FXML
    void onLoginButtonClick() throws RemoteException {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        Optional<User> user = login(username, password);
        user.ifPresent(this::gotoGenericView);
    }

    private void gotoGenericView(User item) {
        perform(registerService::register, item, id);
        manager.switchScene(ViewType.GENERIC_VIEW);
    }

    private Optional<User> login(String username, String password) {
        return perform(loginService::login, username, password);
    }

    @Override
    protected void handler(Throwable exception) {
        logger.error(exception.getMessage());
    }
}
