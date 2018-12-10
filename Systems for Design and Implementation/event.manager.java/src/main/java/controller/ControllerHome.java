package controller;

import cell.EventListCell;
import cell.PlayerListCell;
import domain.Event;
import domain.Player;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import manager.StageManager;
import network.Connection;
import network.Observer;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import service.Notification;
import utils.Functional;
import utils.ThrowPipe;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Component
public class ControllerHome implements ControllerProtocol, Observer {

    @FXML public BorderPane applicationPane;
    @FXML public Button userProfileButton;
    @FXML private Label activeUserLabel;
    @FXML private TextField searchEventTextField;
    @FXML private TextField searchPlayerTextField;
    @FXML private TextField playerNameTextField;
    @FXML private TextField playerAgeTextField;
    @FXML private ListView<Player> playerListView;
    @FXML private ListView<Event> eventListView;

    private final StageManager stageManager;
    private Connection connection;

    @Autowired @Lazy
    public ControllerHome(StageManager stageManager) {
        this.stageManager = stageManager;
    }

    private void setCellFactories() {
        eventListView.setCellFactory(param -> new EventListCell(connection));
        playerListView.setCellFactory(param -> new PlayerListCell(connection));
    }

    private <T> void updateListView(ListView<T> listView, List<T> list) {
        listView.getItems().clear();
        listView.setItems(FXCollections.observableArrayList(list));
    }

    private void setupEventListProperties() {
        eventListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
            ThrowPipe.wrap(() -> updateListView(playerListView,
                    (newValue == null) ? connection.getPlayers() : connection.getPlayerFromEvent(newValue))));
    }

    private void setupPlayerSearchProperties() {
        searchPlayerTextField.textProperty().addListener((observable, oldValue, newValue) ->
                ThrowPipe.wrap(() -> updateListView(playerListView, connection.getPlayers().stream().filter(player ->
                        player.getName().contains(newValue)).collect(Collectors.toList()))));
    }

    private void setupEventSearchProperties() {
        searchEventTextField.textProperty().addListener((observable, oldValue, newValue) ->
                ThrowPipe.wrap(() -> updateListView(eventListView, connection.getEvents().stream().filter(event ->
                        event.getDistance().toString().contains(newValue)).collect(Collectors.toList()))));
    }

    private void update() throws TException {
        Platform.runLater(() -> {
            try {
                updateListView(playerListView, connection.getPlayers());
            } catch (TException e) {
                e.printStackTrace();
            }
        });
        Platform.runLater(() -> {
            try {
                updateListView(eventListView, connection.getEvents());
            } catch (TException e) {
                e.printStackTrace();
            }
        });
    }

    private void handler(Functional.SimpleMethod method) {
        try { method.accept(); }
        catch (Exception exception) { exception.printStackTrace(); }
    }

    private void build() throws TException {
        eventListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ThrowPipe.wrap(() -> activeUserLabel.setText(connection.getUser().getName()));
        setCellFactories();
        setupEventSearchProperties();
        setupPlayerSearchProperties();
        setupEventListProperties();
        update();
    }

    @Override
    public void initialize() { }

    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
        this.connection.pushObserver(this);
        try { build();}
        catch (TException e) { e.printStackTrace(); }
    }

    @FXML
    private void onAddButtonClick() throws TException {
        String name = playerNameTextField.getText();
        Integer age = Integer.parseInt((playerAgeTextField.getText().matches("[0-9]+")) ? playerAgeTextField.getText() : "0");
        List<Event> list = eventListView.getSelectionModel().getSelectedItems().stream().collect(Collectors.toList());
        Integer id = connection.addPlayer(name, age, list);
    }

    @FXML
    private void onLogoutButtonClick() throws TException {
        this.connection.stop();
        this.connection.popObserver(this);
        Platform.exit();
    }

    @Override
    public void update(Notification notification) {
        try { update(); }
        catch (TException e) { e.printStackTrace(); }
    }
}
