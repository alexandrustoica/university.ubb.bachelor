import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jooq.lambda.Unchecked;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        FXMLLoader loader =
                new FXMLLoader(getClass().getResource("fxml/MainView.fxml"));
        Parent root = Unchecked.<Parent>supplier(loader::load).get();
        Scene scene = new Scene(root, 800, 600);
        Controller controller = loader.getController();
        controller.setStage(primaryStage);
        primaryStage.setTitle("FXML Welcome");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
