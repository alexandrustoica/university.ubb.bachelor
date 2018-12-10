package domain;

import java.io.Serializable;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface Idable<T> extends Serializable {
    T getId();
    void setId(T id);
}
