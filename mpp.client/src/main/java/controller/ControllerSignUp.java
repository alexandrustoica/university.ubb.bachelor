package controller;

import client.ClientTransmissionController;
import client.ClientTransmissionProtocol;
import domain.User;
import error.Errors;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import manager.StageManager;
import observer.ObserverAuthenticationProtocol;
import observer.ObserverType;
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
public class ControllerSignUp implements ControllerProtocol, ObserverAuthenticationProtocol {

    private final StageManager stageManager;
    private final ClientTransmissionProtocol controller;

    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;
    @FXML private TextField confirmTextField;
    @FXML private Label errorLabel;

    @Autowired @Lazy
    public ControllerSignUp(StageManager stageManager, ClientTransmissionController controller) {
        this.stageManager = stageManager;
        this.controller = controller;
        this.controller.setObserver(this);
    }

    @Override
    public void initialize() {

    }

    @FXML private void onLoginButtonClick() {
        stageManager.switchScene(ViewType.LOGIN);
    }

    @FXML private void onSignUpButtonClick() {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String confirm = confirmTextField.getText();
        controller.sendSignUpRequest(username, password, confirm);
    }

    @Override
    public ObserverType getType() {
        return ObserverType.SIGNUP;
    }

    @Override
    public void notifyErrors(Errors errors) {
        Platform.runLater(() -> errorLabel.setText(errors.getMessage()));
    }

    @Override
    public void notifySuccess(User user) {
        stageManager.switchScene(ViewType.HOME);
    }
}
