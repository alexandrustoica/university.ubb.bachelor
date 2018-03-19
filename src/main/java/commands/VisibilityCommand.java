package commands;

import pixel.Pixel;
import pixel.PixelData;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class VisibilityCommand implements Command<Stream<Pixel>> {

    private final List<Pixel> original;
    private final List<Pixel> result;

    public VisibilityCommand(
            final Stream<Pixel> original,
            final Stream<Pixel> result) {
        this.original = original.collect(Collectors.toList());
        this.result = result.collect(Collectors.toList());
    }

    private Pixel convertToNegativeDifferentPixels(
            final Pixel left, final Pixel right) {
        return left.equals(right) ? left : new PixelData(
                255 - right.red(),
                255 - right.blue(),
                255 - right.green(),
                right.alpha());
    }

    @Override
    public Stream<Pixel> execute() {
        return IntStream.range(0, original.size()).boxed()
                .map(index -> convertToNegativeDifferentPixels(
                        original.get(index), result.get(index)));
    }

    @Override
    public Stream<Pixel> undo() {
        return original.stream();
    }
}
