package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@SuppressWarnings("WeakerAccess")
public class MatrixBuilder<T> {

    private Integer lines;
    private Integer columns;
    private Generator<T> generator;
    private List<T> accumulator = new ArrayList<>();
    private Function<String, T> importer;

    public MatrixBuilder<T> withLines(final Integer lines) {
        this.lines = lines;
        return this;
    }

    public MatrixBuilder<T> withColumns(final Integer columns) {
        this.columns = columns;
        return this;
    }

    public MatrixBuilder<T> withGenerator(final Generator<T> generator) {
        this.generator = generator;
        return this;
    }

    public MatrixBuilder<T> withAccumulator(final List<T> accumulator) {
        this.accumulator = accumulator;
        return this;
    }

    public MatrixBuilder<T> withStringImporter(final Function<String, T> importer) {
        this.importer = importer;
        return this;
    }

    public Matrix<T> build() {
        return new Matrix<>(lines, columns, generator, accumulator, importer);
    }
}