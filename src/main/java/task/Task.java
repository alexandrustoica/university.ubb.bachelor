package task;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface Task<T> extends Runnable {
    T result();
}
