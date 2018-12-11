package io;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface Writer<T> {

    /**
     * Writes data to an unknown destination.
     *
     * @param data - the data [type T]
     */
    void write(T data);
}
