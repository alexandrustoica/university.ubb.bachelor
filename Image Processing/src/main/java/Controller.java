import commands.*;
import effect.*;
import image.Figure;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Optional;
import java.util.function.Function;

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
    private Slider binarySlider;

    private Stage primaryStage;

    private EffectConfiguration configuration;

    public void setStage(final Stage stage) {
        this.primaryStage = stage;
    }

    public void initialize() {
        setUpInterfaceConstraints();
        configuration = new EffectConfiguration();
        binarySlider.valueProperty().addListener((observable, oldValue, newValue) ->
                onBinarySliderValueChange(newValue));
    }

    @FXML
    public void onGreyscaleButtonClick() {
        configuration = greyscaleButton.isSelected() ?
                configuration.add(new GreyscaleEffect()) :
                configuration.remove(EffectType.GREYSCALE);
        render();
    }

    private void onBinarySliderValueChange(final Number newValue) {
        configuration = newValue.intValue() > 0 ?
                configuration.remove(EffectType.BINARY)
                        .add(new BinaryEffect(newValue.intValue())) :
                configuration.remove(EffectType.BINARY);
        render();
    }

    @FXML
    public void onChooseImageClick() {
        Optional<File> file = getFileFromUser(fileChooser ->
                fileChooser.showOpenDialog(primaryStage));
        file.ifPresent(it -> new LoadImageCommand(originalImage, it).execute());
        file.ifPresent(it -> new LoadImageCommand(resultImage, it).execute());
    }

    @FXML
    public void onImageSubtractionClick() {
        Optional<File> file = getFileFromUser(fileChooser ->
                fileChooser.showOpenDialog(primaryStage));
        file.ifPresent(this::onSubtractFileUploaded);
    }

    private void onSubtractFileUploaded(final File file) {
        configuration = configuration
                .remove(EffectType.SUBTRACT)
                .add(new SubtractEffect(new Figure(file)));
        render();
    }

    @FXML
    public void onExportImageClick() {
        Optional<File> file = getFileFromUser(fileChooser ->
                fileChooser.showSaveDialog(primaryStage));
        file.ifPresent(it -> new SaveCommand(resultImage.getImage(), it).execute());
    }

    private Optional<File> getFileFromUser(
            final Function<FileChooser, File> fromFileChooser) {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Select an image ...",
                        asList("*.png", "*.jpg", "*.jpeg")));
        return Optional.ofNullable(fromFileChooser.apply(fileChooser));
    }

    private void render() {
        new Thread(() -> resultImage.setImage(
                new Figure(originalImage.getImage()).apply(configuration
                        .toTransformer()).toImage())).start();
    }

    private void setUpInterfaceConstraints() {
        leftImageScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        leftImageScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        rightImageScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        rightImageScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        originalImage.fitHeightProperty()
                .bind(leftImageScrollPane.heightProperty());
        resultImage.fitHeightProperty()
                .bind(rightImageScrollPane.heightProperty());
    }
}
