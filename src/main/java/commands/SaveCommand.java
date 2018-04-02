package commands;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.io.File;

import static org.jooq.lambda.Unchecked.runnable;

public class SaveCommand implements Command<Void> {

    private final File file;
    private final Image image;

    public SaveCommand(final Image image, final File toFile) {
        this.image = image;
        this.file = toFile;
    }

    @Override
    public Void execute() {
        runnable(() -> ImageIO.write(SwingFXUtils.fromFXImage(image, null),
                "png", file)).run();
        return null;
    }
}
