package task;

import java.util.List;
import java.util.function.BiFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class Task<T> implements Runnable {

    private List<T> left;
    private List<T> right;
    private List<T> result;
    private BiFunction<T, T, T> operator;

    public Task(List<T> left, List<T> right, List<T> result, BiFunction<T, T, T> operator) {
        this.left = left;
        this.right = right;
        this.result = result;
        this.operator = operator;
    }

    @Override
    public void run() {
        for(Integer index = 0; index < left.size(); index++) {
            result.add(operator.apply(left.get(index), right.get(index)));
        }
    }

    public List<T> getResult() {
        return result;
    }
}