package manager;

import domain.Matrix;
import org.apache.commons.collections4.ListUtils;
import org.apache.log4j.Logger;
import task.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class MatrixCalculator<T> {

    private final Integer concurrency;
    private final DistributionManager<T> distribution = new DistributionManager<>();
    private final BiFunction<T, T, T> action;
    private final BiFunction<List<T>, Integer, List<List<T>>> distribute = distribution::distribute;
    private final Logger logger;
    private final PerformerManager performer;
    private final BiFunction<Matrix<T>, Matrix<T>, Boolean> checker =
            (left, right) -> left.getLines().equals(right.getLines()) &&
                    left.getColumns().equals(right.getColumns());

    public MatrixCalculator(final BiFunction<T, T, T> action, final Integer concurrency) {
        this.logger = Logger.getLogger(MatrixCalculator.class);
        this.performer = new PerformerManager((error) -> logger.error(error.getMessage()));
        this.action = action;
        this.concurrency = concurrency;
    }

    public MatrixCalculator() {
        this((left, right) ->  left, 1);
    }

    public MatrixCalculator<T> basedOn(final BiFunction<T, T, T> action) {
        return new MatrixCalculator<>(action, concurrency);
    }

    public MatrixCalculator<T> withConcurrency(final Integer concurrency) {
        return new MatrixCalculator<>(action, concurrency);
    }

    public Optional<Matrix<T>> performOn(final Matrix<T> left, final Matrix<T> right) {
        return checker.apply(left, right) ? Optional.of(perform(left, right)) : Optional.empty();
    }

    private Matrix<T> perform(Matrix<T> left, Matrix<T> right) {
        return new Matrix<>(left.getLines(), left.getColumns(), left.getGenerator(),
                getResultOfAction(distribute.apply(left.asList(), concurrency),
                        distribute.apply(right.asList(), concurrency)), left.getStringImporter());
    }

    private List<T> getResultOfAction(List<List<T>> left, List<List<T>> right) {
        return getResultOfAction(left, right, 0, Collections.emptyList());
    }

    private List<T> getResultOfAction(List<List<T>> left, List<List<T>> right, Integer index, List<T> accumulator) {
        return (index.equals(concurrency)) ? accumulator :
                getResultOfAction(left, right, index + 1,
                        ListUtils.union(accumulator, getResultFromTask(left.get(index), right.get(index), new ArrayList<>())));
    }

    private List<T> getResultFromTask(List<T> left, List<T> right, List<T> result) {
        Thread thread = new Thread(new Task<>(left, right, result, action));
        thread.start();
        performer.consume(thread::join);
        return result;
    }
}
