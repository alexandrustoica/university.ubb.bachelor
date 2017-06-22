package controller;

import center.LocalNotificationCenter;
import center.SubscriberController;
import functional.SupplierThrow;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import manager.ListViewBuilder;
import manager.PerformerManager;
import manager.StageManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import service.RegisterService;
import service.ServiceProjectTask;
import transfarable.Project;
import transfarable.RemoteNotification;
import transfarable.Task;
import transfarable.User;
import view.Icon;
import view.ViewType;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;


/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
public class ControllerGenericView extends PerformerManager implements SubscriberController {

    private static Logger logger;

    @FXML
    private TextField searchTextFieldLeft;

    @FXML
    private TextField searchTextFieldRight;

    @FXML
    private ListView<Project> listViewLeft;

    @FXML
    private ListView<Task> listViewRight;

    @FXML
    private TextField dataTextFieldLeft;

    @FXML
    private TextField dataTextFieldRight;

    @FXML
    private Label usernameLabel;

    @Autowired
    private Integer id;

    @Lazy
    @Autowired
    private StageManager manager;

    @Lazy
    @Autowired
    private ServiceProjectTask service;

    @Lazy
    @Autowired
    private RegisterService registerService;

    @Lazy
    @Autowired
    private LocalNotificationCenter notificationCenter;

    private ObservableList<Project> listLeft;
    private ObservableList<Task> listRight;

    @Override
    public void initialize() {
        logger = Logger.getLogger(ControllerGenericView.class);
        perform(registerService::get, id)
                .ifPresent(item -> usernameLabel.setText(item.getUsername()));
        notificationCenter.subscribe(this);
        build();
    }

    private void build() {
        refreshLists();
        refreshListViews();

        buildSearchFunction();
    }

    private void buildSearchFunction() {
        setupSearch(searchTextFieldLeft, listViewLeft, listLeft, (item, value) ->
                item.getName().toLowerCase().contains(value));
        setupSearch(searchTextFieldRight, listViewRight, listRight, (item, value) ->
                item.getText().toLowerCase().contains(value));
    }

    private <T> void setupSearch(TextField field, ListView<T> view,
                                 ObservableList<T> list, BiFunction<T, String, Boolean> filter) {
        field.textProperty().addListener((observable, oldValue, newValue) ->
                view.setItems(list.filtered(item -> filter.apply(item, newValue))));
    }

    private void refreshLists() {
        listLeft = refreshList(service::all);
        listRight = refreshList(service::every);
    }

    private void refreshListViews() {
        listViewLeft.setItems(listLeft);
        listViewRight.setItems(listRight);
        listViewLeft = new ListViewBuilder<>(listViewLeft)
                .setIcon(Icon.CLOSE)
                .textProvider(Project::getName)
                .setColor("green")
                .setAction((service, item) -> {
                    perform(((ServiceProjectTask) service)::delete, item);
                    return null;}, service)
                .build();
        listViewRight = new ListViewBuilder<>(listViewRight)
                .setIcon(Icon.CLOSE)
                .textProvider(Task::getText)
                .setColor("green")
                .setAction((service, item) -> {
                    perform(((ServiceProjectTask) service)::remove, item);
                    return null;}, service)
                .build();
        listViewLeft.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listViewRight.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

    private <T> ObservableList<T> refreshList(SupplierThrow<List<T>, RemoteException> supplier) {
        return FXCollections.observableArrayList(perform(supplier).orElse(new ArrayList<>()));
    }

    @FXML
    private void onItemLeftClick() {
        Optional<List<Task>> result =
                perform(service::everyFrom, listViewLeft.getSelectionModel().getSelectedItem());
        result.ifPresent(list -> listViewRight.setItems(listRight.filtered(list::contains)));
    }

    @FXML
    private void onItemRightClick() {
        Optional<List<Project>> result =
                perform(service::allFrom, listViewRight.getSelectionModel().getSelectedItem());
        result.ifPresent(list -> listViewLeft.setItems(listLeft.filtered(list::contains)));
    }

    @FXML
    private void onBackButtonLeftClick() {
        listLeft = refreshList(service::all);
        Platform.runLater(this::refreshListViews);
    }

    @FXML
    private void onBackButtonRightClick() {
        listRight = refreshList(service::every);
        Platform.runLater(this::refreshListViews);
    }

    @FXML
    private void onAddButtonLeft() {
        String text = dataTextFieldLeft.getText();
        Project project = new Project(text);
        Optional<Project> inserted = perform(service::insert, project);
        inserted.ifPresent(element ->
                listViewRight.getSelectionModel().getSelectedItems()
                        .forEach(item -> perform(service::insert, element, item)));
    }

    @FXML
    private void onAddButtonRight() {
        String text = dataTextFieldRight.getText();
        Task task = new Task(text);
        Optional<Task> inserted = perform(service::add, task);
        inserted.ifPresent(element ->
                listViewLeft.getSelectionModel().getSelectedItems()
                        .forEach(item -> perform(service::insert, item, element)));
    }

    @FXML
    private void onUpdateButtonLeft() {
        String text = dataTextFieldLeft.getText();
        Project with = new Project(text);
        listViewLeft.getSelectionModel().getSelectedItems()
                .forEach(item -> perform(service::update, item, with));
    }

    @FXML
    private void onUpdateButtonRight() {
        String text = dataTextFieldRight.getText();
        Task with = new Task(text);
        listViewRight.getSelectionModel().getSelectedItems()
                .forEach(item -> perform(service::refresh, item, with));
    }

    @FXML
    private void onLogoutButtonClick() {
        Optional<User> user = perform(registerService::unregister, id);
        user.ifPresent(item -> logger.info(item.getUsername() + " has logged out..."));
        manager.switchScene(ViewType.LOGIN);
    }

    @Override
    public void update(RemoteNotification notification) {
        refreshLists();
        Platform.runLater(this::refreshListViews);
    }

    @Override
    protected void handler(Throwable exception) {
        logger.error(exception);
    }
}
