package domain;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@FunctionalInterface
public interface Generator<T> {
    T generate();
}
