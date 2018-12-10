package context;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class RelationalInsertContext<T, U> {

    private T element;
    private U item;

    /**
     * @apiNote This constructor is for Spring REST.
     */
    public RelationalInsertContext() {
        this(null, null);
    }

    public RelationalInsertContext(T element, U item) {
        this.element = element;
        this.item = item;
    }

    public T getElement() {
        return element;
    }

    public U getItem() {
        return item;
    }
}
