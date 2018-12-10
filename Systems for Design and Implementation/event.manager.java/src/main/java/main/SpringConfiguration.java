package main;

import controller.ControllerHome;
import controller.ControllerLogin;
import controller.ControllerSignUp;
import javafx.stage.Stage;
import loader.SpringFXMLLoader;
import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.ResourceBundle;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Configuration
@ComponentScan("loader")
@SuppressWarnings("all")
public class SpringConfiguration {

    @Autowired
    private SpringFXMLLoader loader;             // FXML Loader with DI.

    private StageManager stageManager;

    public SpringConfiguration() throws Exception {
    }

    /** Local Resources Bundle */
    @Bean
    public ResourceBundle resourceBundle() {
        return ResourceBundle.getBundle("Bundle");
    }

    @Bean @Lazy
    public ControllerHome controllerHome() {
        return new ControllerHome(stageManager);
    }

    @Bean @Lazy
    public ControllerLogin controllerLogin() {
        return new ControllerLogin(stageManager);
    }

    @Bean @Lazy
    public ControllerSignUp controllerSignUp() {
        return new ControllerSignUp(stageManager);
    }

    /**
     * Effect: Bean for Stage Manager Spring DI
     *
     * <p>Requires @Lazy because the stage is NOT initialize when Spring
     * is initializing the application's context.</p>
     *
     * @param stage: The stage of the FX Application.
     * @return The application's Stage Manager.
     */
    @Bean @Lazy
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public StageManager stageManager(Stage stage) {
        stageManager = new StageManager(loader, stage);
        return stageManager;
    }
}