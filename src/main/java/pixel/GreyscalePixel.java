package pixel;

public class GreyscalePixel extends PixelDecorator {

    public GreyscalePixel(final Pixel pixel) {
        super(new PixelData(
                (pixel.blue() + pixel.red() + pixel.green()) / 3,
                (pixel.blue() + pixel.red() + pixel.green()) / 3,
                (pixel.blue() + pixel.red() + pixel.green()) / 3,
                pixel.alpha()));
    }
}
