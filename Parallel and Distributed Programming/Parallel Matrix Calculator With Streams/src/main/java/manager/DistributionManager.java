package manager;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class DistributionManager<T> {

    /**
     * Splits a list into @parts list of almost equal sizes.
     *
     * @param list  - the list
     * @param parts - the number of list we need
     * @return - the list of partitions
     */
    public List<List<T>> distribute(List<T> list, Integer parts) {
        return distribute(list, Collections.emptyList(), list.size() / parts, list.size() % parts);
    }

    private List<List<T>> distribute(List<T> list, List<List<T>> accumulator, Integer quotient, Integer remainder) {
        return list.isEmpty() ? accumulator : (remainder.equals(0) ?
                distribute(list.subList(quotient, list.size()), combine(list, accumulator, quotient), quotient, remainder) :
                distribute(list.subList(quotient + 1, list.size()), combine(list, accumulator, quotient + 1), quotient, remainder - 1));
    }

    private List<List<T>> combine(List<T> list, List<List<T>> accumulator, Integer quotient) {
        return Stream.concat(accumulator.stream(), Stream.of(list.subList(0, quotient))).collect(Collectors.toList());
    }

    /**
     * Splits a list into multiple lists of size @size.
     *
     * @param list - the list
     * @param size - the size of the partition
     * @return - the list of partitions
     * @apiNote - the last list might be smaller
     */
    public List<List<T>> partition(List<T> list, Integer size) {
        return Lists.partition(list, size);
    }

}
