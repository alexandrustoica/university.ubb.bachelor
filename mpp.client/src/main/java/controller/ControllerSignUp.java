package controller;

import domain.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import service.ClientService;
import view.ViewType;

import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Component
public class ControllerSignUp implements ControllerProtocol {

    private final StageManager stageManager;
    private final ClientService clientManager;

    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;
    @FXML private TextField confirmTextField;
    @FXML private Label errorLabel;

    @Autowired @Lazy
    public ControllerSignUp(StageManager stageManager, ClientService clientManager) throws Exception {
        this.stageManager = stageManager;
        this.clientManager = clientManager;
    }

    @Override
    public void initialize() { }

    @FXML private void onLoginButtonClick() throws RemoteException {
        clientManager.stop();
        stageManager.switchScene(ViewType.LOGIN);
    }

    @FXML private void onSignUpButtonClick() {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String confirm = confirmTextField.getText();
        try {
            User user = clientManager.signUp(username, password, confirm);
            clientManager.setUser(user);
            stageManager.switchScene(ViewType.HOME);
        } catch (RemoteException exception) {
            errorLabel.setText(exception.getMessage());
        }
    }
}
