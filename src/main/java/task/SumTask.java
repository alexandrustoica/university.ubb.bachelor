package task;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class SumTask implements Task<List<Integer>> {

    private List<Integer> result;
    private List<Integer> left;
    private List<Integer> right;

    public SumTask(List<Integer> left, List<Integer> right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        result = Stream.iterate(0, index -> index + 1)
                .limit(left.size())
                .map(index -> left.get(index) + right.get(index))
                .collect(Collectors.toList());
    }

    public List<Integer> result() {
        return result;
    }
}
