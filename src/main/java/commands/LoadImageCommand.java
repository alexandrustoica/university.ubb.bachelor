package commands;

import image.Figure;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class LoadImageCommand implements Command<Figure> {

    private final ImageView view;
    private final File file;

    public LoadImageCommand(final ImageView view, final File file) {
        this.view = view;
        this.file = file;
    }

    @Override
    public Figure execute() {
        view.setImage(new Image(file.toURI().toString()));
        return new Figure(file.getAbsoluteFile());
    }
}
