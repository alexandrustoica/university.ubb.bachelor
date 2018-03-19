package commands;

import image.Image;
import java.io.File;

public class SaveCommand implements Command<Void> {

    private final File file;
    private final Image image;

    public SaveCommand(final Image image, final File toFile) {
        this.image = image;
        this.file = toFile;
    }

    @Override
    public Void execute() {
        image.saveTo(file, "png");
        return null;
    }

    @Override
    public Void undo() {
        return null;
    }
}
