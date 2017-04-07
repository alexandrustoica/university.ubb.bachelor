package controller;

import client.ClientTransmissionController;
import client.ClientTransmissionProtocol;
import domain.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import view.ViewType;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        02/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

@Component
public class ControllerLogin implements ControllerProtocol {

    private final StageManager stageManager;
    private final ClientTransmissionProtocol controller;

    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Label errorLabel;

    @Autowired
    @Lazy
    public ControllerLogin(StageManager stageManager, ClientTransmissionController controller) {
        this.stageManager = stageManager;
        this.controller = controller;
    }

    @Override
    public void initialize() {

    }

    @FXML
    private void onLoginButtonClick() {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        User user = controller.requestLogin(username, password);
        if (user == null) {
            Platform.runLater(() ->
                this.errorLabel.setText(controller.getErrors().getMessage()));
            return;
        }
        this.controller.setActiveUser(user);
        this.stageManager.switchScene(ViewType.HOME);

    }

    @FXML
    private void onSignUpButtonClick() {
        stageManager.switchScene(ViewType.SIGNUP);
    }

}

