package effect;

import pixel.PixelData;

public final class BinaryEffect extends EffectDecorator {

    public BinaryEffect(final Integer pivot) {
        super(new SideEffect((pixel) -> {
            Integer grey = (pixel.red() + pixel.blue() + pixel.green()) / 3;
            return grey > pivot ?
                    new PixelData(255, 255, 255, 1) :
                    new PixelData(0, 0, 0, 1);
        }, EffectType.BINARY));
    }
}
