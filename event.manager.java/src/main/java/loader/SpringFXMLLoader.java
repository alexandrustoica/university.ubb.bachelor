package loader;

import controller.ControllerProtocol;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import network.Connection;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Component
public class SpringFXMLLoader {

    private final ResourceBundle resourceBundle;        // local resources
    private final ApplicationContext context;           // spring application context

    /**
     * @param context: The spring's application context [ApplicationContext]
     * @param resourceBundle: The local resources. [ResourceBundle]
     */
    @Autowired
    public SpringFXMLLoader(ApplicationContext context, ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
        this.context = context;
    }

    /**
     * Effect: Loads a given fxml file.
     * @param fxmlFilePath: The fxml file's path [String]
     * @return root: The root node [Parent]
     * @throws IOException : If we are unable to load the fxml file.
     */
    public Parent load(String fxmlFilePath, Connection connection) throws IOException, TException {
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(context::getBean);
        loader.setResources(resourceBundle);
        loader.setLocation(getClass().getResource(fxmlFilePath));
        Parent result =  loader.load();
        ControllerProtocol controller = loader.getController();
        controller.setConnection(connection);
        return result;
    }

}