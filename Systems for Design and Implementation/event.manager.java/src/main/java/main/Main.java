package main;

import javafx.application.Application;
import javafx.stage.Stage;
import manager.StageManager;
import network.ConnectionManager;
import org.apache.thrift.transport.TTransportException;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import view.ViewType;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@SpringBootApplication
@SuppressWarnings("all")
public class Main extends Application {

    private ConfigurableApplicationContext context;
    private StageManager stageManager;

    private static final Integer port = 9091;
    private static final String host = "localhost";
    private static final String propertiesURL = "/client.properties";

    public static void main(String[] args) {
        Application.launch();
    }

    /**
     * Effect: Spring gets initialised here.
     * <p> We need Spring in order to create the StageManager. </p>
     * @throws Exception : If Spring is unable to getUser an Application Context.
     */
    @Override
    public void init() throws Exception {
        context = getContext();
    }

    /**
     * Effect: Starts the FX Application Thread.
     * <p> Needs a stage in order to create the StageManager, so the bean initialisation must be lazy. </p>
     * @param stage: The init stage that FX Application provides. [Stage]
     * @throws Exception : If Spring is unable to create the bean for StageManager.
     */
    @Override
    public void start(Stage stage) throws Exception {
        stageManager = context.getBean(StageManager.class, stage);
        displayScene();
    }

    /**
     * Effect: Displays the frame scene of our application.
     */
    private void displayScene() throws TTransportException {
        stageManager.switchScene(ViewType.LOGIN, new ConnectionManager(host, port));
    }

    /**
     * Effect: Closes the spring's application context.
     *
     * This method is called when the application should stop, and provides a
     * convenient place to prepare for application exit and destroy resources.
     *
     * @throws Exception : If the application is not closing properly.
     */
    @Override
    public void stop() throws Exception {
        context.close();
    }

    /**
     * Effect: Create an application context with the application's command line args.
     * @return ConfigurableApplicationContext The application's context.
     */
    private ConfigurableApplicationContext getContext() {
        // creates an application & context builder based on the client class
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Main.class);
        // retrieves a read-only list of raw arg and converts the list to String[] -- application's args
        String[] args = getParameters().getRaw().stream().toArray(String[]::new);
        builder.headless(false); // needed for TestFX integration
        return builder.run(args);
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
            properties.load(Main.class.getResourceAsStream(propertiesURL));
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

