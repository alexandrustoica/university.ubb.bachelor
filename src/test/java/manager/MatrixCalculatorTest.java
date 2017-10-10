package manager;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.stream.Stream;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class MatrixCalculatorTest {

    private static Logger logger;
    private static final Integer MAX = 100;
    private static final Integer MAX_THREADS = 10;

    @Before
    public void setUp() throws Exception {
        logger = Logger.getLogger(MatrixCalculator.class);
    }

    @Test
    public void sum() throws Exception {
        Integer lines = new Random().nextInt(10);
        Integer columns = new Random().nextInt(10);
        MatrixCalculator calculator = new MatrixCalculator(3);
        Matrix left = new Matrix().generate(lines, columns);
        Matrix right = new Matrix().generate(lines, columns);
        Matrix result = calculator.sum(left, right).orElse(new Matrix());
        Assert.assertTrue(Stream.iterate(0, index -> index + 1)
                .limit(result.toList().size())
                .allMatch(index -> result.toList().get(index)
                        .equals(left.toList().get(index) + right.toList().get(index))));
    }

    @Test
    public void sumException() throws Exception {
        MatrixCalculator calculator = new MatrixCalculator(3);
        Matrix left = new Matrix().generate(3, 4);
        Matrix right = new Matrix().generate(4, 3);
        Assert.assertFalse(calculator.sum(left, right).isPresent());
    }

    @Test
    public void sumPerformance() throws Exception {
        for (Integer index = 0; index < 20; index++) {
            Integer lines = Math.abs(new Random().nextInt(MAX)) + 1;
            Integer columns = Math.abs(new Random().nextInt(MAX)) + 1;
            Integer threads = Math.min(Math.abs(new Random().nextInt(MAX_THREADS) + 1),
                    lines < columns ? lines : columns);
            sumMatricesAndPrintTime(new Matrix().generate(lines, columns),
                    new Matrix().generate(lines, columns), threads, index + 1);
        }
    }

    private void sumMatricesAndPrintTime(Matrix left, Matrix right, Integer threads, Integer index) {
        System.out.println("Task#" + index + " works on matrices of size: " + left.lines() + "x" + left.columns() + " with " + threads + " threads.");
        System.out.println("Task#" + index + " took: " + sumMatricesAndReturnTime(left, right, threads) + "ms");
    }

    private Long sumMatricesAndReturnTime(Matrix left, Matrix right, Integer threads) {
        MatrixCalculator calculator = new MatrixCalculator(threads);
        Long startTime = System.currentTimeMillis();
        calculator.sum(left, right);
        Long endTime = System.currentTimeMillis();
        return (endTime - startTime);
    }

    @Test
    public void multiply() throws Exception {
        Integer lines = Math.abs(new Random().nextInt(3)) + 2;
        Integer columns = Math.abs(new Random().nextInt(3)) + 2;
        MatrixCalculator calculator = new MatrixCalculator(3);
        Matrix left = new Matrix().generate(lines, columns, 10);
        Matrix right = new Matrix().generate(columns, lines, 10);
        Matrix result = calculator.multiply(left, right).orElse(new Matrix());
        Assert.assertEquals((long) result.getLine(0).get(0),
                Stream.iterate(0, index -> index + 1).limit(left.columns())
                        .mapToInt(index -> left.getLine(0).get(index) * right.getColumn(0).get(index))
                        .sum());
    }

    @Test
    public void isMultiplyingSimpleMatrices() throws Exception {
        MatrixCalculator calculator = new MatrixCalculator(3);
        Matrix left = new Matrix().fromString(new FileManager("test_multiplyA").read());
        Matrix right = new Matrix().fromString(new FileManager("test_multiplyB").read());
        Matrix expected = new Matrix().fromString(new FileManager("test_multiply_expected").read());
        Matrix result = calculator.multiply(left, right).orElse(new Matrix());
        System.out.println(expected);
        System.out.println(result);
        Assert.assertEquals(expected.toList(), result.toList());
    }

    @Test
    public void multiplicationPerformance() throws Exception {
        for (Integer index = 0; index < 20; index++) {
            Integer lines = Math.abs(new Random().nextInt(MAX)) + 1;
            Integer columns = Math.abs(new Random().nextInt(MAX)) + 1;
            Integer threads = Math.min(Math.abs(new Random().nextInt(MAX_THREADS) + 1),
                    lines < columns ? lines : columns);
            multiplyMatricesAndPrintTime(new Matrix().generate(lines, columns),
                    new Matrix().generate(columns, lines), threads, index + 1);
        }
    }

    private void multiplyMatricesAndPrintTime(Matrix left, Matrix right, Integer threads, Integer index) {
        System.out.println("Task#" + index + " works on matrices of size: " + left.lines() + "x" + left.columns() + " with " + threads + " threads.");
        System.out.println("Task#" + index + " took: " + multiplyMatricesAndReturnTime(left, right, threads) + "ms");
    }

    private Long multiplyMatricesAndReturnTime(Matrix left, Matrix right, Integer threads) {
        MatrixCalculator calculator = new MatrixCalculator(threads);
        Long startTime = System.currentTimeMillis();
        calculator.multiply(left, right);
        Long endTime = System.currentTimeMillis();
        return (endTime - startTime);
    }

}