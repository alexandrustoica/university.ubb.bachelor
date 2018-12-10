package controller;

import cell.EventListCell;
import cell.PlayerListCell;
import domain.Event;
import domain.Player;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import manager.StageManager;
import notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import service.ClientService;
import system.Subscriber;
import utils.Functional;
import utils.ThrowPipe;
import view.ViewType;

import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Component
public class ControllerHome implements ControllerProtocol, Subscriber {

    @FXML private Label activeUserLabel;

    @FXML private TextField searchEventTextField;
    @FXML private TextField searchPlayerTextField;
    @FXML private TextField playerNameTextField;
    @FXML private TextField playerAgeTextField;

    @FXML private ListView<Player> playerListView;
    @FXML private ListView<Event> eventListView;

    private final StageManager stageManager;
    private final ClientService clientManager;

    @Autowired
    @Lazy
    public ControllerHome(StageManager stageManager, ClientService clientManager) throws RemoteException {
        this.stageManager = stageManager;
        this.clientManager = clientManager;
        this.clientManager.subscribe(this);
    }

    private void setCellFactories() {
        eventListView.setCellFactory(param -> new EventListCell(clientManager));
        playerListView.setCellFactory(param -> new PlayerListCell(clientManager));
    }

    private <T> void updateListView(ListView<T> listView, List<T> list) {
        listView.getItems().clear();
        listView.setItems(FXCollections.observableList(list));
    }

    private void setupEventListProperties() {
        eventListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
            ThrowPipe.wrap(() -> updateListView(playerListView,
                    (newValue == null) ? clientManager.getPlayers() : clientManager.getPlayerFromEvent(newValue))));
    }

    private void setupPlayerSearchProperties() {
        searchPlayerTextField.textProperty().addListener((observable, oldValue, newValue) ->
                ThrowPipe.wrap(() -> updateListView(playerListView, clientManager.getPlayers().stream().filter(player ->
                        player.getName().contains(newValue)).collect(Collectors.toList()))));
    }

    private void setupEventSearchProperties() {
        searchEventTextField.textProperty().addListener((observable, oldValue, newValue) ->
                ThrowPipe.wrap(() -> updateListView(eventListView, clientManager.getEvents().stream().filter(event ->
                        event.getDistance().toString().contains(newValue)).collect(Collectors.toList()))));
    }

    private void update() throws RemoteException {
        Platform.runLater(() -> ThrowPipe.wrap(() -> updateListView(playerListView, clientManager.getPlayers())));
        Platform.runLater(() -> ThrowPipe.wrap(() -> updateListView(eventListView, clientManager.getEvents())));
    }

    private void handler(Functional.SimpleMethod method) {
        try { method.accept(); }
        catch (Exception exception) { exception.printStackTrace(); }
    }

    @Override
    public void initialize() {
        eventListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ThrowPipe.wrap(() -> activeUserLabel.setText(clientManager.getUser().getName()));
        setCellFactories();
        setupEventSearchProperties();
        setupPlayerSearchProperties();
        setupEventListProperties();
        handler(this::update);
    }

    @FXML
    private void onAddButtonClick() throws RemoteException {
        String name = playerNameTextField.getText();
        Integer age = Integer.parseInt((playerAgeTextField.getText().matches("[0-9]+")) ? playerAgeTextField.getText() : "0");
        List<Event> list = eventListView.getSelectionModel().getSelectedItems().stream().collect(Collectors.toList());
        Integer id = clientManager.addPlayer(name, age, list);
    }

    @FXML
    private void onLogoutButtonClick() {
        stageManager.switchScene(ViewType.LOGIN);
    }

    @Override
    public void update(Notification notification) throws RemoteException {
        handler(this::update);
    }
}
