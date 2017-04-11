package controller;

import client.ClientTransmissionController;
import client.ClientTransmissionProtocol;
import domain.Event;
import domain.Player;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import manager.StageManager;
import observer.ObserverClientProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import response.NotificationType;
import view.ViewType;

import java.util.ArrayList;
import java.util.stream.Collectors;

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
public class ControllerApplication implements ControllerProtocol, ObserverClientProtocol {

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
        this.controller.setObserver(this);
        updateData();
        Platform.runLater(this::build);
    }

    private void build() {
        eventListView.setCellFactory(param -> new ListCell<Event>() {
            @Override
            protected void updateItem(Event item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null && !empty) {
                    setText(item.toString() + " Players: " + controller.getPlayersFromEvent(item.getID()).size());
                } else {
                    setText(null);
                }
            }
        });

        playerListView.setCellFactory(param -> new ListCell<Player>() {
            @Override
            protected void updateItem(Player item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null && !empty) {
                    String idEvents = "";
                    for (Event event : controller.getEventsFromPlayer(item.getID())) {
                        idEvents += " " + event.getID();
                    }
                    setText(item.toString() + " Events: " + idEvents);
                } else {
                    setText(null);
                }
            }
        });

        searchEventTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            Platform.runLater(() -> {
                ArrayList<Event> result = controller.getAllEvents().stream().filter(event ->
                        event.getDistance().toString().contains(newValue)).collect(Collectors.toCollection(ArrayList::new));
                this.eventListView.setItems(FXCollections.observableArrayList(result));
            });
        });

        searchPlayerTextField.textProperty().addListener(((observable, oldValue, newValue) -> {
            Platform.runLater(() -> {
                this.playerListView.setItems(FXCollections.observableArrayList(controller.getAllPlayers()).filtered(
                        player -> player.getName().contains(newValue)));
            });
        }));
    }

    @Override
    public void initialize() {
        activeUserLabel.setText(controller.getActiveUser().getName());
    }

    @FXML
    private void onAddButtonClick() {
        String name = playerNameTextField.getText();
        Integer age = Integer.parseInt(playerAgeTextField.getText());
        ObservableList<Event> events = eventListView.getSelectionModel().getSelectedItems();
        ArrayList<Integer> idEvents = new ArrayList<>();
        events.forEach(event -> idEvents.add(event.getID()));
        controller.addPlayer(name, age, idEvents);
    }

    @FXML
    private void onLogoutButtonClick() {
        controller.exit();
        stageManager.switchScene(ViewType.LOGIN);
    }

    private void updateData() {
        Platform.runLater(() -> {
            ArrayList<Player> players = controller.getAllPlayers();
            ArrayList<Event> events = controller.getAllEvents();
            playerListView.getItems().clear();
            playerListView.setItems(FXCollections.observableArrayList(players));
            eventListView.getItems().clear();
            eventListView.setItems(FXCollections.observableArrayList(events));
        });
    }


    @Override
    public void notify(NotificationType notification) {
        updateData();
    }

}
