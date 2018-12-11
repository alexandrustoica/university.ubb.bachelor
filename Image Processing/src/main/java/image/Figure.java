package image;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.jetbrains.annotations.NotNull;
import pixel.Pixel;
import pixel.PixelData;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.function.Function;

import static org.jooq.lambda.Unchecked.supplier;

final public class Figure {

    private final Function<Pixel, Pixel> mapper;
    private final BufferedImage buffer;

    private Figure(
            final Function<Pixel, Pixel> mapper,
            final BufferedImage buffer) {
        this.mapper = mapper;
        this.buffer = buffer;
    }

    public Figure(final Image image) {
        this((pixel) -> pixel, SwingFXUtils.fromFXImage(image, null));
    }

    public Figure(final File file) {
        this((pixel) -> pixel, supplier(() -> ImageIO.read(file)).get());
    }

    private Integer width() {
        return buffer.getWidth();
    }

    private Integer height() {
        return buffer.getHeight();
    }

    public Figure apply(final Function<Pixel, Pixel> function) {
        return new Figure(function, buffer);
    }

    public Pixel pixelAt(final Integer x, final Integer y) {
        return new PixelData(buffer.getRGB(x, y), x, y);
    }

    public Image toImage() {
        final BufferedImage buffer =
                new BufferedImage(width(), height(), BufferedImage.TYPE_INT_RGB);
        for (Integer index = 0; index < width() * height(); index++) {
            buffer.setRGB(index % buffer.getWidth(), index / buffer.getWidth(),
                    mapper.apply(getPixelFromIndex(buffer, index))
                            .toInteger());
        }
        return SwingFXUtils.toFXImage(buffer, null);
    }

    @NotNull
    private PixelData getPixelFromIndex(BufferedImage buffer, Integer index) {
        return new PixelData(getRBGFromBuffer(this.buffer,
                index), index % buffer.getWidth(),
                index / buffer.getWidth());
    }

    private Integer getRBGFromBuffer(
            final BufferedImage buffer, final Integer index) {
        return buffer.getRGB(index % buffer.getWidth(),
                index / buffer.getWidth());
    }
}
