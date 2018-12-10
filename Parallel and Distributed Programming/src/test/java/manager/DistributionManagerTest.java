package manager;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class DistributionManagerTest {

    private DistributionManager<Integer> distribution;

    @Before
    public void setUp() throws Exception {
        distribution = new DistributionManager<>();
    }

    @Test
    public void distribute() throws Exception {
        distribute(3, 9);
        distribute(3, 10);
        distribute(4, 10);
        distribute(5, 10);
        distribute(6, 10);
    }

    private void distribute(Integer parts, Integer limit) {
        Integer remainder = limit % parts;
        List<Integer> list = Stream.iterate(0, index -> index + 1).limit(limit).collect(Collectors.toList());
        List<List<Integer>> test = distribution.distribute(list, parts);
        System.out.println(list);
        System.out.println(test);
        Assert.assertEquals((long)parts, (long)test.size());
        Assert.assertTrue(!remainder.equals(0) || test.stream().allMatch(line -> line.size() == limit / parts));
        Assert.assertTrue(remainder.equals(0) || test.stream().anyMatch(line -> line.size() == limit / parts));
        Assert.assertTrue(remainder.equals(0) || test.stream().anyMatch(line -> line.size() == limit / parts + 1));
    }

    @Test
    public void partition() throws Exception {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<List<Integer>> test = distribution.partition(list, 3);
        Assert.assertEquals(4, test.size());
        Assert.assertEquals(1, test.get(test.size() - 1).size());
        Assert.assertTrue(test.get(0).containsAll(Lists.newArrayList(1, 2, 3)));
    }

}