package main;

import controller.ControllerHome;
import controller.ControllerLogin;
import controller.ControllerSignUp;
import javafx.stage.Stage;
import loader.SpringFXMLLoader;
import manager.ClientManager;
import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import service.ClientService;

import java.io.IOException;
import java.util.Properties;
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
    private final ClientService clientManager;

    public SpringConfiguration() throws Exception {
        clientManager = new ClientManager();
        clientManager.start();
    }

    private static final Integer port = 1099;
    private static final String host = "localhost";
    private static final String propertiesURL = "/client.properties";

    @Bean
    public ClientService clientProtocol() throws Exception {
        return clientManager;
    }

    /** Local Resources Bundle */
    @Bean
    public ResourceBundle resourceBundle() {
        return ResourceBundle.getBundle("Bundle");
    }

    @Bean @Lazy
    public ControllerHome controllerApplication() throws Exception {
        return new ControllerHome(stageManager, clientManager);
    }

    @Bean @Lazy
    public ControllerLogin controllerLogin() throws Exception {
        return new ControllerLogin(stageManager, clientManager);
    }

    @Bean @Lazy
    public ControllerSignUp controllerSignUp() throws Exception {
        return new ControllerSignUp(stageManager, clientManager);
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

    /**
     * Effect: Returns the port from the application's properties
     */
    private static Integer getPort(Properties properties) {
        Integer serverPort = port;
        try {
            serverPort = Integer.parseInt(properties.getProperty("server.port"));
        } catch (NumberFormatException error) {
            handleErrors(error);
        }
        return serverPort;
    }

    /**
     * Effect: Returns the list of properties from external file.
     * @param propertiesURL: the file's URL
     * @return the list of properties
     */
    private static Properties getProperties(String propertiesURL) {
        Properties properties = new Properties();
        try {
            properties.load(MainClient.class.getResourceAsStream(propertiesURL));
        } catch (IOException error) {
            handleErrors(error);
        }
        return properties;
    }

    /**
     * Effect: Handles exception in our client class.
     */
    private static void handleErrors(Exception error) {
        System.out.println(error.getMessage());
    }

}