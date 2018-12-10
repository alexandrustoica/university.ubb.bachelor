package io;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface Reader<T> {

    /**
     * Reads data from an unknown source.
     *
     * @return - the data [type T]
     */
    T read();
}
