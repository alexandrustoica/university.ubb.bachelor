package model;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface ConfigurationModel<T, U> {
    void consume(T element, U item);
    T supply(U item);
}
