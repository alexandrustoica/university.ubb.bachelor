package pixel;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = {"red", "blue", "green", "alpha", "x", "y"})
public class PixelData implements Pixel {

    private final Integer red;
    private final Integer blue;
    private final Integer green;
    private final Integer alpha;
    private final Integer x;
    private final Integer y;

    public PixelData(
            final Integer red,
            final Integer blue,
            final Integer green,
            final Integer alpha,
            final Integer x,
            final Integer y) {
        this.red = red;
        this.blue = blue;
        this.green = green;
        this.alpha = alpha;
        this.x = x;
        this.y = y;
    }

    public PixelData(final Integer red,
                     final Integer blue,
                     final Integer green,
                     final Integer alpha) {
        this(red, blue, green, alpha, 0, 0);
    }

    public PixelData(final Integer pixel, final Integer x, final Integer y) {
        this((pixel >> 16) & 0xff, (pixel & 0xff),
                (pixel >> 8) & 0xff, (pixel >> 24) & 0xff, x, y);
    }

    public Integer toInteger() {
        return (this.alpha << 24) |
                (this.red << 16) |
                (this.green << 8) |
                this.blue;
    }

    public Integer red() {
        return red;
    }

    public Integer blue() {
        return blue;
    }

    public Integer green() {
        return green;
    }

    public Integer alpha() {
        return alpha;
    }

    public Integer x() {
        return x;
    }

    public Integer y() {
        return y;
    }
}
