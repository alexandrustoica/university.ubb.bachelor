import commands.*;
import image.Image;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pixel.Pixel;

import java.io.File;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class Controller {

    @FXML
    private ScrollPane leftImageScrollPane;

    @FXML
    private ScrollPane rightImageScrollPane;

    @FXML
    private ImageView originalImage;

    @FXML
    private ImageView resultImage;

    @FXML
    private CheckBox greyscaleButton;

    @FXML
    private Slider visibilitySlider;

    private Stage primaryStage;

    private File resultFile;

    public void setStage(final Stage stage) {
        this.primaryStage = stage;
    }

    public void initialize() {
        leftImageScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        leftImageScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        rightImageScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        rightImageScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        originalImage.fitHeightProperty().bind(leftImageScrollPane.heightProperty());
        resultImage.fitHeightProperty().bind(rightImageScrollPane.heightProperty());
        resultFile = new File("outputt.png");
        visibilitySlider.valueProperty().addListener((
                (observable, oldValue, newValue) -> System.out.println(newValue)));
    }

    public void onSeeChangesButtonClick() {
        final Image original = new Image(originalImage.getImage());
        final Image result = new Image(resultImage.getImage());
        Command<Stream<Pixel>> command = new VisibilityCommand(
                original.streamOfPixels(), result.streamOfPixels());
        Runnable task = () -> {
            Image difference = result.apply(command.execute());
            new SaveCommand(difference, resultFile).execute();
            new LoadImageCommand(resultImage, resultFile).execute();
        };
        new Thread(task).start();
    }

    @FXML
    public void onGreyscaleButtonClick() {
        final Image image = new Image(originalImage.getImage());
        Command<Stream<Pixel>> command =
                new ToGreyscaleCommand(image.streamOfPixels());
        Runnable task = () -> {
            Image result = greyscaleButton.isSelected() ?
                    image.apply(command.execute()) : image.apply(command.undo());
            new SaveCommand(result, resultFile).execute();
            new LoadImageCommand(resultImage, resultFile).execute();
        };
        new Thread(task).start();
    }

    @FXML
    public void onChooseImageClick() {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Select an image ...",
                        asList("*.png", "*.jpg", "*.jpeg")));
        Optional<File> file = Optional.ofNullable(
                fileChooser.showOpenDialog(primaryStage));
        file.ifPresent(it -> new LoadImageCommand(originalImage, it).execute());
        file.ifPresent(it -> new LoadImageCommand(resultImage, it).execute());
    }
}
