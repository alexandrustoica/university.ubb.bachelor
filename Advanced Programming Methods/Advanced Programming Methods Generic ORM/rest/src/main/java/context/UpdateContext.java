package context;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class UpdateContext<T> {

    private T element;
    private T with;

    /**
     * @apiNote This constructor is for Spring REST.
     */
    public UpdateContext() {
        this(null, null);
    }

    public UpdateContext(T element, T with) {
        this.element = element;
        this.with = with;
    }

    public T getElement() {
        return element;
    }

    public T getWith() {
        return with;
    }
}
