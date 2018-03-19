package commands;

import image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class LoadImageCommand implements Command<Image> {

    private final ImageView view;
    private final File file;

    public LoadImageCommand(final ImageView view, final File file) {
        this.view = view;
        this.file = file;
    }

    @Override
    public Image execute() {
        view.setImage(new javafx.scene.image.Image(file.toURI().toString()));
        return new Image(file.getAbsoluteFile());
    }

    @Override
    public Image undo() {
        return null;
    }
}
