package translator;

import domain.Idable;
import org.jetbrains.annotations.NotNull;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface GenericTranslator<EntityT extends Idable<?>, TransferT extends Idable<?>> {

    EntityT transform(@NotNull TransferT transferable);
    TransferT translate(@NotNull EntityT entity);

}
