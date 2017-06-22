package controller;

import center.LocalNotificationCenter;
import center.SubscriberController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
import view.ViewType;

import java.rmi.RemoteException;


/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
public class ControllerGenericView implements SubscriberController {

    private static Logger logger;

    @FXML
    private TextField searchTextFieldLeft;

    @FXML
    private TextField searchTextFieldRight;

    @FXML
    private Button searchButtonLeft;

    @FXML
    private Button searchButtonRight;

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

    private ObservableList<Project> listLeft;
    private ObservableList<Task> listRight;

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

    @Override
    public void initialize() {
        logger = Logger.getLogger(ControllerGenericView.class);
        try {
            notificationCenter.subscribe(this);
            usernameLabel.setText(registerService.get(id).getUsername());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        build();
    }

    private void update() {
        System.out.print("Update");
        build();
    }

    private void build() {
        try {
            listLeft = FXCollections.observableArrayList(service.all());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        listViewLeft.setItems(listLeft);
        try {
            listRight = FXCollections.observableArrayList(service.every());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        listViewRight.setItems(listRight);
    }

    @FXML
    private void onAddButtonLeft() {
        String text = dataTextFieldLeft.getText();
        Project project = new Project(text);
        try {
            Project inserted = service.insert(project);
            listViewRight.getSelectionModel().getSelectedItems().forEach(item -> {
                try {
                    service.insert(inserted, item);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onAddButtonRight() {
        String text = dataTextFieldRight.getText();
        Task task = new Task(text);
        try {
            Task inserted = service.add(task);
            listViewLeft.getSelectionModel().getSelectedItems().forEach(item -> {
                try {
                    service.insert(item, inserted);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onUpdateButtonLeft() {
        String text = dataTextFieldLeft.getText();
        Project with = new Project(text);
        listViewLeft.getSelectionModel().getSelectedItems().forEach(item -> {
            try {
                service.update(item, with);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void onUpdateButtonRight() {
        String text = dataTextFieldRight.getText();
        Task with = new Task(text);
        listViewRight.getSelectionModel().getSelectedItems().forEach(item -> {
            try {
                service.refresh(item, with);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void onLogoutButtonClick() {
        try {
            User user = registerService.unregister(id);
            logger.info(user.getUsername() + " has logged out ...");
            manager.switchScene(ViewType.LOGIN);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onSearchButtonLeft() {

    }

    @FXML
    private void onSearchButtonRight() {

    }

    @Override public void update(RemoteNotification notification) {
        update();
    }
}
