package manager;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.apache.log4j.Logger;
import view.ViewType;

import java.io.IOException;
import java.io.Serializable;
import java.util.function.Consumer;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class StageManager implements Serializable {

    private static Logger logger;
    private Consumer<IOException> handler;
    private Consumer<RuntimeException> explorer;

    private final Stage primaryStage;           // the application's primary stage
    private final SpringFXMLLoader loader;      // the fxml loader with DI
    private ViewType lastVisited;

    @Getter
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public StageManager(SpringFXMLLoader loader, Stage stage) {
        logger = Logger.getLogger(StageManager.class);
        handler = exception -> logger.error(exception.getCause());
        explorer = exception -> logger.error(exception.getCause());
        this.loader = loader;
        this.primaryStage = stage;
    }

    public void switchScene(final ViewType type) {
        Parent root = getRootNode(type.getFXMLFile());
        show(root, type.getTitle());
        lastVisited = type;
    }

    public <T> void switchScene(final ViewType type, T element) {
        Parent root = getRootNode(type.getFXMLFile(), element);
        show(root, type.getTitle());
        lastVisited = type;
    }

    public <T> void refresh(T element) {
        switchScene(lastVisited, element);
    }

    public void refresh() {
        switchScene(lastVisited);
    }

    private void show(final Parent root, String title) {
        Scene scene = getNewScene(root);
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Scene getNewScene(Parent root) {
        Scene scene = primaryStage.getScene();
        scene = (scene == null) ? new Scene(root) : scene;
        scene.setRoot(root);
        return scene;
    }

    private Parent getRootNode(String fxmlFilePath) {
        try {
            return loader.load(fxmlFilePath);
        } catch (IOException exception) {
            handler.accept(exception);
        }
        return null;
    }

    public <T> Parent getRootNode(String fxmlFilePath, T element) {
        try {
            return loader.load(fxmlFilePath, element);
        } catch (IOException exception) {
            handler.accept(exception);
        }
        return null;
    }
}