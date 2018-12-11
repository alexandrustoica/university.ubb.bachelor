package effect;

import image.Figure;
import pixel.PixelData;

public final class SubtractEffect extends EffectDecorator {

    public SubtractEffect(final Figure figure) {
        super(new SideEffect((pixel) ->
                !pixel.equals(figure.pixelAt(pixel.x(), pixel.y())) ? pixel :
                        new PixelData(0, 0, 0, pixel.alpha(), pixel.x(), pixel
                                .y()), EffectType.SUBTRACT));
    }
}
