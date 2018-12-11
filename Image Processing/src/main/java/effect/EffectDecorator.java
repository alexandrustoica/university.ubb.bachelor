package effect;

import pixel.Pixel;

import java.util.function.Function;

public class EffectDecorator implements Effect {

    private final Effect effect;

    EffectDecorator(final Effect effect) {
        this.effect = effect;
    }

    @Override
    public Effect composeWithEffect(final Effect effect) {
        return this.effect.composeWithEffect(effect);
    }

    @Override
    public EffectType type() {
        return effect.type();
    }

    @Override
    public Function<Pixel, Pixel> transformer() {
        return effect.transformer();
    }
}
