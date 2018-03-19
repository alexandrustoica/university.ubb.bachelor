package pixel;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = {"red", "blue", "green", "alpha"})
public class PixelData implements Pixel {

    private final Integer red;
    private final Integer blue;
    private final Integer green;
    private final Integer alpha;

    public PixelData(final Integer red,
                     final Integer blue,
                     final Integer green,
                     final Integer alpha) {
        this.red = red;
        this.blue = blue;
        this.green = green;
        this.alpha = alpha;
    }

    public PixelData(final Integer pixel) {
        this.alpha = (pixel >> 24) & 0xff;
        this.red = (pixel >> 16) & 0xff;
        this.green = (pixel >> 8) & 0xff;
        this.blue = pixel & 0xff;
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
}
