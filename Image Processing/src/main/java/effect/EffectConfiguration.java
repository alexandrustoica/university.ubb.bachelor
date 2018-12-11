package effect;

import com.google.common.collect.ImmutableList;
import lombok.ToString;
import pixel.Pixel;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@ToString(of = {"effects"})
public final class EffectConfiguration {

    private final List<Effect> effects;

    private EffectConfiguration(List<Effect> effects) {
        this.effects = effects;
    }

    public EffectConfiguration() {
        this.effects = new ArrayList<>();
    }

    public Function<Pixel, Pixel> toTransformer() {
        return effects.stream().reduce((effect, accumulator) -> accumulator
                .composeWithEffect(effect)).orElse(new SideEffect()).transformer();
    }

    public EffectConfiguration add(final Effect effect) {
        return new EffectConfiguration(ImmutableList.<Effect>builder().addAll
                (effects).add(effect).build());
    }

    public EffectConfiguration remove(final EffectType type) {
        return new EffectConfiguration(effects.stream()
                .parallel()
                .filter(it -> !it.type().equals(type))
                .collect(Collectors.toList()));
    }
}
