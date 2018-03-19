package pixel;

public class PixelDecorator implements Pixel {

    private final Pixel pixel;

    public PixelDecorator(final Pixel pixel) {
        this.pixel = pixel;
    }

    @Override
    public Integer red() {
        return pixel.red();
    }

    @Override
    public Integer blue() {
        return pixel.blue();
    }

    @Override
    public Integer green() {
        return pixel.green();
    }

    @Override
    public Integer alpha() {
        return pixel.alpha();
    }

    @Override
    public Integer toInteger() {
        return pixel.toInteger();
    }
}
