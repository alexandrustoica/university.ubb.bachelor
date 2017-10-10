package manager;

import javafx.util.Pair;
import task.MultiplicationTask;
import task.SumTask;
import task.Task;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class MatrixCalculator {

    private Integer threads;
    private DistributionManager<Integer> distributionManager;

    /**
     * Checks if we can sum the two matrices.
     */
    private BiFunction<Matrix, Matrix, Boolean> sumChecker =
            (left, right) -> left.lines().equals(right.lines()) && left.columns().equals(right.columns());

    /**
     * Checks if we can multiply the two matrices.
     */
    private BiFunction<Matrix, Matrix, Boolean> multiplierChecker =
            (left, right) -> left.columns().equals(right.lines());

    /**
     * Converts a list of lists into a matrix.
     */
    private Function<List<List<Integer>>, Matrix> convertToMatrix =
            (list) -> new Matrix().fromList(list);

    /**
     * Splits a list into @parts lists of relatively equal size.
     */
    private BiFunction<List<Integer>, Integer, List<List<Integer>>> splitList =
            (list, parts) -> distributionManager.partition(list, parts);

    /**
     * Creates a thread based on a runnable task. Runs the task and returns the result.
     */
    private Function<Task<List<Integer>>, List<Integer>> solveTaskAndReturnResult =
            (task) -> {
                new Thread(task).run();
                return task.result();
            };

    public MatrixCalculator(Integer threads) {
        this.threads = threads;
        this.distributionManager = new DistributionManager<>();
    }


    /**
     * Returns an optional matrix as result because
     * based on the input the operation might not be possible.
     */
    public Optional<Matrix> sum(Matrix left, Matrix right) {
        return sumChecker.apply(left, right) ? Optional.of(sumOf(left, right)) : Optional.empty();
    }

    /**
     * @return - the result matrix
     */
    private Matrix sumOf(Matrix left, Matrix right) {
        List<Integer> result = parallelSumOf(
                distributionManager.distribute(left.toList(), threads),
                distributionManager.distribute(right.toList(), threads));
        return splitList.andThen(convertToMatrix).apply(result, left.columns());
    }


    /**
     * @return - the result matrix as a list
     */
    private List<Integer> parallelSumOf(List<List<Integer>> left, List<List<Integer>> right) {
        return Stream.iterate(0, index -> index + 1).limit(threads)
                .map(index -> new SumTask(left.get(index), right.get(index)))
                .map(task -> solveTaskAndReturnResult.apply(task))
                .flatMap(List::stream).collect(Collectors.toList());
    }

    /**
     * @return - an optional of matrix because the
     * operation might not be possible.
     */
    public Optional<Matrix> multiply(Matrix left, Matrix right) {
        return multiplierChecker.apply(left, right) ? Optional.of(multiplicationOf(left, right)) :
                Optional.empty();
    }

    /**
     * Converts two matrices into a list of groups with the following format
     * [(line[indexLine], column[0]) ... (line[indexLine], column[n])]
     * where n = right.columns().size()
     *
     * @param indexLine - the line we need to focus on in order to create the groups
     * @return - the list of groups
     */
    @SuppressWarnings("unchecked")
    private List convertToGroup(Matrix left, Matrix right, Integer indexLine) {
        return Stream.iterate(0, column -> column + 1).limit(right.columns())
                .map(column -> new Pair(left.getLine(indexLine), right.getColumn(column)))
                .collect(Collectors.toList());
    }

    /**
     * Generates all the groups with the following format
     * [[(line[0], column[0]) ... (line[0], column[n])]
     * ... ... ... ... ... ... ... ... ... ... ... ...
     * [(line[m], column[0]) ... (line[m], column[n])]]
     * where n = right.columns().size() and m = left.lines().size();
     *
     * @return - all the groups as a list
     */
    private List generateMultiplicationGroups(Matrix left, Matrix right) {
        return Stream.iterate(0, line -> line + 1).limit(left.lines())
                .map(line -> convertToGroup(left, right, line))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    /**
     * Applies the multiplication task for each group in parallel on @threads threads.
     *
     * @return - the result matrix as a list
     */
    private List<Integer> parallelMultiplicationOf(List<List<Pair<List<Integer>, List<Integer>>>> groups) {
        return Stream.iterate(0, index -> index + 1).limit(threads)
                .map(index -> new MultiplicationTask(groups.get(index)))
                .map(task -> solveTaskAndReturnResult.apply(task))
                .flatMap(List::stream).collect(Collectors.toList());
    }

    /**
     * @return - the result matrix as a matrix.
     */
    @SuppressWarnings("unchecked")
    private Matrix multiplicationOf(Matrix left, Matrix right) {
        return new Matrix().fromList(distributionManager.partition(
                parallelMultiplicationOf(distributionManager.distribute
                        (generateMultiplicationGroups(left, right), threads)), right.columns()));
    }

}
