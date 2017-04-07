package controller;

import client.ClientTransmissionController;
import client.ClientTransmissionProtocol;
import domain.Event;
import domain.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        03/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

@Component
public class ControllerApplication implements ControllerProtocol {

    @FXML private Label activeUserLabel;

    @FXML private TextField searchEventTextField;
    @FXML private TextField searchPlayerTextField;
    @FXML private TextField playerNameTextField;
    @FXML private TextField playerAgeTextField;

    @FXML private ListView<Player> playerListView;
    @FXML private ListView<Event> eventListView;

    private final StageManager stageManager;
    private final ClientTransmissionProtocol controller;

    @Autowired
    @Lazy
    public ControllerApplication(StageManager stageManager, ClientTransmissionController controller) {
        this.stageManager = stageManager;
        this.controller = controller;
    }

    @Override
    public void initialize() {
        updateData();
    }

    @FXML
    private void onAddButtonClick() {
        // TODO
    }

    @FXML
    private void onLogoutButtonClick() {
        // TODO
    }

    private void updateData() {
        // TODO
    }


}
