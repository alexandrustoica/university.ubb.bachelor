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

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Component
public class ControllerLogin implements ControllerProtocol {

    private final StageManager stageManager;
    private Connection connection;

    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;
    @FXML private Label errorLabel;

    @Autowired @Lazy
    public ControllerLogin(StageManager stageManager) {
        this.stageManager = stageManager;
    }

    @Override
    public void initialize() { }

    @Override
    public void setConnection(Connection connection) throws TException {
        this.connection = connection;
        this.connection.start();
    }

    @FXML
    private void onLoginButtonClick() {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        try {
            User user = connection.login(username, password);
            connection.setUser(user);
            stageManager.switchScene(ViewType.HOME, connection);
        } catch (TException exception) {
            errorLabel.setText(exception.getMessage());
        }
    }

    @FXML
    private void onSignUpButtonClick() {
        stageManager.switchScene(ViewType.SIGNUP, connection);
    }

}