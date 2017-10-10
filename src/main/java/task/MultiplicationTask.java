package task;


import javafx.util.Pair;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class MultiplicationTask implements Task<List<Integer>> {

    private List<Integer> result;
    private List<Pair<List<Integer>, List<Integer>>> tasks;

    public MultiplicationTask(List<Pair<List<Integer>, List<Integer>>> tasks) {
        this.tasks = tasks;
    }

    private Function<Pair<List<Integer>, List<Integer>>, Integer> solveGroup =
            (group) -> Stream.iterate(0, index -> index + 1).limit(group.getKey().size())
                    .mapToInt(index -> group.getKey().get(index) * group.getValue().get(index))
                    .sum();

    @Override
    public void run() {
        result = tasks.stream().map(group -> solveGroup.apply(group))
                .collect(Collectors.toList());
    }

    public List<Integer> result() {
        return result;
    }
}
