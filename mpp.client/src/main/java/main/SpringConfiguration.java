package main;

import client.ClientTransmissionController;
import controller.ControllerApplication;
import controller.ControllerLogin;
import controller.ControllerSignUp;
import javafx.stage.Stage;
import loader.SpringFXMLLoader;
import manager.StageManager;
import client.ClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        02/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

@Configuration
@ComponentScan("loader")
public class SpringConfiguration {

    @Autowired
    private SpringFXMLLoader loader;             // FXML Loader with DI.

    private StageManager stageManager;

    private static final Integer port = 55555;
    private static final String host = "localhost";
    private static final String propertiesURL = "/client.properties";

    @Bean
    public ClientTransmissionController clientTransmissionController() {
        return new ClientTransmissionController(clientConnectionManager());
    }

    @Bean
    public ClientConnectionManager clientConnectionManager() {
        Properties properties = getProperties(propertiesURL);
        Integer serverPort = getPort(properties);
        String serverHost = properties.getProperty("server.host", host);
        return new ClientConnectionManager(serverHost, serverPort);
    }

    /** Local Resources Bundle */
    @Bean
    public ResourceBundle resourceBundle() {
        return ResourceBundle.getBundle("Bundle");
    }

    @Bean @Lazy
    public ControllerApplication controllerApplication() {
        return new ControllerApplication(stageManager, clientTransmissionController());
    }

    @Bean @Lazy
    public ControllerLogin controllerLogin() {
        return new ControllerLogin(stageManager, clientTransmissionController());
    }

    @Bean @Lazy
    public ControllerSignUp controllerSignUp() {
        return new ControllerSignUp(stageManager, clientTransmissionController());
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
     * Effect: Handles exceptions in our client class.
     */
    private static void handleErrors(Exception error) {
        System.out.println(error.getMessage());
    }

}