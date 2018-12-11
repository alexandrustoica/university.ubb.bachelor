package effect;

import lombok.ToString;
import pixel.Pixel;

import java.util.function.Function;

@ToString(of = "type")
public class SideEffect implements Effect {

    private final Function<Pixel, Pixel> function;
    private final EffectType type;

    SideEffect(final Function<Pixel, Pixel> function, final EffectType
            type) {
        this.function = function;
        this.type = type;
    }

    SideEffect() {
        this((pixel) -> pixel, EffectType.NONE);
    }

    public Effect composeWithEffect(final Effect effect) {
        return new SideEffect(
                function.andThen(effect.transformer()), EffectType.COMPOSITION);
    }

    public Function<Pixel, Pixel> transformer() {
        return function;
    }

    public EffectType type() {
        return type;
    }
}
