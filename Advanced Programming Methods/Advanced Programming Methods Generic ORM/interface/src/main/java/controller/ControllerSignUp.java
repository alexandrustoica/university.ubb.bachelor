package controller;

import dto.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
import service.RegisterService;
import service.SignUpService;
import view.ViewType;

import java.util.Optional;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
public class ControllerSignUp extends PerformerManager implements ControllerInterface {

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField confirmTextField;

    @FXML
    private Label errorLabel;

    @FXML
    private ImageView backgroundImage;

    @FXML
    private StackPane backgroundImagePane;

    @Lazy
    @Autowired
    private StageManager manager;

    @FXML
    private Button signUpButton;

    @Autowired
    private Integer id;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private SignUpService service;

    private static Logger logger;

    @Override
    public void initialize() {
        logger = Logger.getLogger(ControllerSignUp.class);
        backgroundImage.fitWidthProperty().bind(backgroundImagePane.widthProperty());
        backgroundImage.fitHeightProperty().bind(backgroundImagePane.heightProperty());
    }

    @FXML
    void onLogoButtonClick() { }

    @FXML
    void onLoginButtonClick() {
        manager.switchScene(ViewType.LOGIN);
    }

    @FXML
    void onSignUpButtonClick() {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String confirm = confirmTextField.getText();
        Optional<User> user = perform(service::signUp, username, password, confirm);
        user.ifPresent(this::gotoGenericView);
    }
    private void gotoGenericView(User item) {
        perform(registerService::register, item, id);
        manager.switchScene(ViewType.GENERIC_VIEW);
    }

    @Override
    protected void handler(Throwable exception) {
        logger.error(exception.getMessage());
    }
}
