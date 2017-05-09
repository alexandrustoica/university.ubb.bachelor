package controller;

import domain.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import manager.StageManager;
import network.Connection;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import view.ViewType;

import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Component
public class ControllerSignUp implements ControllerProtocol {

    private final StageManager stageManager;
    private Connection connection;

    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;
    @FXML private TextField confirmTextField;
    @FXML private Label errorLabel;

    @Autowired @Lazy
    public ControllerSignUp(StageManager stageManager) {
        this.stageManager = stageManager;
    }

    @Override
    public void initialize() { }

    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @FXML private void onLoginButtonClick() throws RemoteException {
        //connection.stop();
        stageManager.switchScene(ViewType.LOGIN, connection);
    }

    @FXML private void onSignUpButtonClick() {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String confirm = confirmTextField.getText();
        try {
            User user = connection.signUp(username, password, confirm);
            connection.setUser(user);
            stageManager.switchScene(ViewType.HOME, connection);
        } catch (TException exception) {
            errorLabel.setText(exception.getMessage());
        }
    }
}
