package main;

import domain.Complex;
import domain.Generator;
import domain.Matrix;
import domain.MatrixBuilder;
import manager.MatrixCalculator;

import java.io.IOException;
import java.util.Optional;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */


public class Main {

    public static <T> void runBasedOn(
            final Integer lines,
            final Integer columns,
            final Integer threads,
            final BiFunction<T, T, T> action,
            final Function<String, T> importer,
            final Generator<T> generator) {

        Matrix<T> left = new MatrixBuilder<T>()
                .withLines(lines).withColumns(columns).withStringImporter(importer)
                .withGenerator(generator)
                .build().generate();

        Matrix<T> right = new MatrixBuilder<T>()
                .withLines(1000).withColumns(1000).withStringImporter(importer)
                .withGenerator(generator)
                .build().generate();

//        new FileManager("left").write(left.exportToString());
//        Matrix<T> leftFromFile = left.fromString(new FileManager("left").read());
//
//        new FileManager("right").write(right.exportToString());
//        Matrix<T> rightFromFile = right.fromString(new FileManager("right").read());

        Long startActionTime = System.currentTimeMillis();
        Optional<Matrix<T>> result = new MatrixCalculator<T>().withConcurrency(threads)
                .basedOn(action)
                .performOn(left, right);

        Long endActionTime = System.currentTimeMillis();
        // result.ifPresent(System.out::println);
        System.out.println("Action Time: " + (endActionTime - startActionTime) + "ms " + "threads : " + threads);
    }

    public static void main(String[] args) throws IOException {

        Function<String, Complex<Integer>> complexImporter = (string) -> {
            String[] data = string.replace("[", "")
                    .replace("]", "").split("\\s");
            return new Complex<>(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
        };

        Generator<Complex<Integer>> complexGenerator =
                () -> new Complex<>(new Random().nextInt(100), new Random().nextInt(100));

        Function<String, Double> doubleImporter = Double::parseDouble;
        Generator<Double> doubleGenerator = () -> new Random().nextDouble();


        BiFunction<Complex<Integer>, Complex<Integer>, Complex<Integer>> complexMultiplication =
                (left, right) -> new Complex<>(left.getReal() * right.getReal() + left.getImaginary() * right.getImaginary(),
                        left.getReal() * right.getImaginary() + left.getImaginary() * right.getReal());
        BiFunction<Double, Double, Double> doubleMultiplication = (left, right) -> left * right;
        BiFunction<Double, Double, Double> doubleSpecial = (left, right) -> 1 / (1 / left + 1 / right);

        runBasedOn(1000, 1000, 2, complexMultiplication, complexImporter, complexGenerator);
        runBasedOn(1000, 1000, 4, complexMultiplication, complexImporter, complexGenerator);
        runBasedOn(1000, 1000, 6, complexMultiplication, complexImporter, complexGenerator);
        runBasedOn(1000, 1000, 8, complexMultiplication, complexImporter, complexGenerator);

        runBasedOn(1000, 1000, 2, doubleMultiplication, doubleImporter, doubleGenerator);
        runBasedOn(1000, 1000, 4, doubleMultiplication, doubleImporter, doubleGenerator);
        runBasedOn(1000, 1000, 6, doubleMultiplication, doubleImporter, doubleGenerator);
        runBasedOn(1000, 1000, 8, doubleMultiplication, doubleImporter, doubleGenerator);

        runBasedOn(1000, 1000, 2, doubleSpecial, doubleImporter, doubleGenerator);
        runBasedOn(1000, 1000, 4, doubleSpecial, doubleImporter, doubleGenerator);
        runBasedOn(1000, 1000, 6, doubleSpecial, doubleImporter, doubleGenerator);
        runBasedOn(1000, 1000, 8, doubleSpecial, doubleImporter, doubleGenerator);


    }

}
