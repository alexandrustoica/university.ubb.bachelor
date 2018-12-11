package effect;

import pixel.Pixel;

import java.util.function.Function;

public interface Effect {
    Effect composeWithEffect(final Effect effect);
    EffectType type();
    Function<Pixel, Pixel> transformer();
}
