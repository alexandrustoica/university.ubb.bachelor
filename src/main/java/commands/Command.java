package commands;

public interface Command<T> {
    T execute();
}
