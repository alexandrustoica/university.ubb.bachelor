package commands;

import pixel.Pixel;

import java.util.stream.Stream;

public interface Command<T> {
    T execute();
    T undo();
}
