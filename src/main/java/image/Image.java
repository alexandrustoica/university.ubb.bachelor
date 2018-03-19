package image;

import javafx.embed.swing.SwingFXUtils;
import org.jooq.lambda.Unchecked;
import pixel.Pixel;
import pixel.PixelData;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Image {

    private final BufferedImage buffer;

    public Image(final File file) {
        this.buffer = Unchecked.supplier(() -> ImageIO.read(file)).get();
    }

    public Image(final javafx.scene.image.Image image) {
        this.buffer = SwingFXUtils.fromFXImage(image, null);
    }

    public Image(final List<Pixel> pixels,
                 final Integer width,
                 final Integer height) {
        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        IntStream.range(0, pixels.size()).boxed()
                .parallel()
                .forEach(it -> buffer.setRGB(
                        it % width,
                        it / width,
                        pixels.get(it).toInteger()));
    }

    public Integer width() {
        return buffer.getWidth();
    }

    public Integer height() {
        return buffer.getHeight();
    }

    public List<Pixel> pixels() {
        return streamOfPixels().collect(Collectors.toList());
    }

    public Image apply(final Function<Pixel, Pixel> function) {
        return new Image(pixels().stream().map(function).collect(Collectors.toList()),
                width(), height());
    }

    public Image apply(final Stream<Pixel> stream) {
        return new Image(stream.collect(Collectors.toList()), width(), height());
    }

    public Stream<Pixel> streamOfPixels() {
        return IntStream.iterate(0, index -> index + 1)
                .limit(width() * height()).boxed()
                .parallel()
                .map(it -> buffer.getRGB(it % width(), it / width()))
                .map(PixelData::new);
    }

    public Image saveTo(final File file, final String extension) {
        Unchecked.runnable(() -> ImageIO.write(buffer, extension, file)).run();
        return this;
    }
}
