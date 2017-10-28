package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */


@SuppressWarnings("WeakerAccess")
public class Matrix<T> {

    private final List<T> accumulator;
    private final Integer lines;
    private final Integer columns;
    private final Generator<T> generator;
    private final Function<String, T> stringImporter;

    public Matrix(final Integer lines,
                  final Integer columns,
                  final Generator<T> generator,
                  final Function<String, T> stringImporter) {
        this(lines, columns, generator, new ArrayList<>(), stringImporter);
    }

    public Matrix(final Integer lines,
                  final Integer columns,
                  final Generator<T> generator,
                  final List<T> accumulator,
                  final Function<String, T> stringImporter) {
        this.accumulator = accumulator;
        this.lines = lines;
        this.columns = columns;
        this.generator = generator;
        this.stringImporter = stringImporter;
    }

    public final Matrix<T> generate() {
        for (Integer line = 0; line < lines; line++) {
            for (Integer column = 0; column < columns; column++) {
                accumulator.add(generator.generate());
            }
        }
        return this;
    }

    public Generator<T> getGenerator() {
        return generator;
    }

    public List<T> asList() {
        return accumulator;
    }

    public List<T> getLine(final Integer index) {
        return accumulator.subList(index, index + columns);
    }

    public Integer getLines() {
        return lines;
    }

    public Integer getColumns() {
        return columns;
    }

    public Function<String, T> getStringImporter() {
        return stringImporter;
    }

    public Matrix<T> fromString(final String string) {
        return new Matrix<>(lines, columns, generator,
                Arrays.stream(string.split(","))
                        .map(stringImporter).collect(Collectors.toList()), stringImporter);
    }

    public String exportToString() {
        StringBuilder result = new StringBuilder();
        for (T item : accumulator) {
            result.append(item.toString()).append(",");
        }
        return result.deleteCharAt(result.length() - 1).toString();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Integer index = 0; index < accumulator.size(); index++) {
            result.append(accumulator.get(index).toString()).append(" ")
                    .append(((index + 1) % columns == 0) ? "\n" : " ");
        }
        return result.toString();
    }
}
