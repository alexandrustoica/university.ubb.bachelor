package manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class Matrix {

    private List<List<Integer>> matrix;

    public Matrix() {
        this.matrix = new ArrayList<>();
    }

    /**
     * Generates a matrix with @lines lines and @columns columns.
     *
     * @param lines   - the number of lines [Integer]
     * @param columns - the number of columns [Integer]
     * @return - the generated matrix
     */
    public Matrix generate(Integer lines, Integer columns) {
        return generate(lines, columns, Math.abs(new Random().nextInt()));
    }

    /**
     * Generates a matrix with @lines lines, @columns columns and
     * bound for each element equal with @max.
     *
     * @param lines   - the number of lines
     * @param columns - the number of columns
     * @param max     - the bound for elements
     * @return - the generated matrix
     */
    public Matrix generate(Integer lines, Integer columns, Integer max) {
        matrix = Stream.iterate(0, line -> line + 1).limit(lines)
                .map(line -> Stream.iterate(0, column -> column + 1)
                        .limit(columns).map(item -> new Random().nextInt(max))
                        .collect(Collectors.toList())).collect(Collectors.toList());
        return this;
    }

    /**
     * Converts a matrix to a simple list.
     *
     * @return - the matrix as list
     */
    public List<Integer> toList() {
        return matrix.stream().flatMap(List::stream).collect(Collectors.toList());
    }

    /**
     * @param indexLine - the index of the wanted line
     * @return - the line found at @index
     * @apiNote - might produce IndexOutOfBoundsException
     */
    public List<Integer> getLine(Integer indexLine) {
        return matrix.get(indexLine);
    }

    /**
     * @param indexColumn - the index of the wanted column
     * @return - the column found at @index
     * @apiNote - might produce IndexOutOfBoundsException
     */
    public List<Integer> getColumn(Integer indexColumn) {
        return matrix.stream().map(line -> line.get(indexColumn))
                .collect(Collectors.toList());
    }

    /**
     * Converts a list of lists into a matrix (each list represents a line).
     *
     * @param list - the list of lists
     * @return - the matrix
     */
    public Matrix fromList(List<List<Integer>> list) {
        matrix = list;
        return this;
    }

    /**
     * @return - the number of lines
     */
    public Integer lines() {
        return matrix.size();
    }

    /**
     * @return - the number of columns
     */
    public Integer columns() {
        return matrix.get(0).size();
    }

    /**
     * Converts a string to a matrix.
     *
     * @param string - the string
     * @return - the matrix from string
     * @apiNote - the numbers in the string should be separated by space and the lines by System.lineSeparator()
     */
    public Matrix fromString(String string) {
        return fromString(string, System.lineSeparator(), " ");
    }

    /**
     * Converts a string to a matrix.
     *
     * @param string          - the matrix as string
     * @param lineSeparator   - the line separator regex
     * @param numberSeparator - the number separator regex
     * @return - the matrix from string
     */
    public Matrix fromString(String string, String lineSeparator, String numberSeparator) {
        matrix = Stream.of(string.split(lineSeparator))
                .map(line -> Stream.of(line.split(numberSeparator)).map(Integer::parseInt).collect(Collectors.toList()))
                .collect(Collectors.toList());
        return this;
    }

    /**
     * @return - the matrix as string
     */
    @Override
    public String toString() {
        return matrix.stream()
                .map(line -> line.stream().map(Object::toString).map(item -> item + " ")
                        .reduce("", (acc, item) -> acc += item))
                .reduce("", (acc, item) -> acc += item + System.lineSeparator());
    }
}
