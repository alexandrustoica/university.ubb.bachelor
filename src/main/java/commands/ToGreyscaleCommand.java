package commands;

import pixel.Pixel;
import pixel.PixelData;

import java.util.stream.Stream;

public class ToGreyscaleCommand implements Command<Stream<Pixel>> {

    private final Stream<Pixel> original;

    public ToGreyscaleCommand(final Stream<Pixel> original) {
        this.original = original;
    }

    @Override
    public Stream<Pixel> execute() {
        return original.map(pixel -> {
            Integer grey = (pixel.red() + pixel.blue() + pixel.green()) / 3;
            return new PixelData(grey, grey, grey, pixel.alpha());
        });
    }

    @Override
    public Stream<Pixel> undo() {
        return original;
    }
}
