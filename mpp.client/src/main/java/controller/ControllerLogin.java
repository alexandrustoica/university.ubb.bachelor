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
public class ControllerLogin implements ControllerProtocol, ObserverAuthenticationProtocol {

    private final StageManager stageManager;
    private final ClientTransmissionProtocol controller;

    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;
    @FXML private Label errorLabel;

    @Autowired @Lazy
    public ControllerLogin(StageManager stageManager, ClientTransmissionController controller) {
        this.stageManager = stageManager;
        this.controller = controller;
        this.controller.setObserver(this);
    }

    @Override
    public void initialize() {

    }

    @FXML private void onLoginButtonClick() {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        controller.sendLoginRequest(username, password);
    }

    @FXML private void onSignUpButtonClick() {
        stageManager.switchScene(ViewType.SIGNUP);
    }

    @Override
    public ObserverType getType() {
        return ObserverType.LOGIN;
    }

    @Override
    public void notifyErrors(Errors errors) {
        Platform.runLater(() -> errorLabel.setText(errors.getMessage()));
    }

    @Override
    public void notifySuccess(User user) {
        Platform.runLater(() -> stageManager.switchScene(ViewType.HOME));
    }
}
