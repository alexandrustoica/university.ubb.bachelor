package effect;

import pixel.PixelData;

public final class GreyscaleEffect extends EffectDecorator {

    public GreyscaleEffect() {
        super(new SideEffect((pixel) -> {
            Integer grey = (pixel.red() + pixel.blue() + pixel.green()) / 3;
            return new PixelData(grey, grey, grey, pixel.alpha());
        }, EffectType.GREYSCALE));
    }
}
