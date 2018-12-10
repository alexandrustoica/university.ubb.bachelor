package domain;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface RelationEntity<T, U> {
    T supplyT();
    U supplyU();
    RelationEntity<T, U> create(T element, U item);
}
