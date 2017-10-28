package main;

import domain.Complex;
import domain.Matrix;
import domain.MatrixBuilder;
import manager.FileManager;
import manager.MatrixCalculator;

import java.io.IOException;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */


public class Main {

    public static void main(String[] args) throws IOException {

        Function<String, Complex<Integer>> importer = (string) -> {
            String[] data = string.replace("[", "")
                    .replace("]", "").split("\\s");
            return new Complex<>(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
        };

        Matrix<Complex<Integer>> left = new MatrixBuilder<Complex<Integer>>()
                .withLines(10).withColumns(10).withStringImporter(importer)
                .withGenerator(() -> new Complex<>(new Random().nextInt(100), new Random().nextInt(100)))
                .build().generate();

        Matrix<Complex<Integer>> right = new MatrixBuilder<Complex<Integer>>()
                .withLines(10).withColumns(10).withStringImporter(importer)
                .withGenerator(() -> new Complex<>(new Random().nextInt(100), new Random().nextInt(100)))
                .build().generate();

        new FileManager("left").write(left.exportToString());
        Matrix<Complex<Integer>> leftFromFile = left.fromString(new FileManager("left").read());

        new FileManager("right").write(right.exportToString());
        Matrix<Complex<Integer>> rightFromFile = right.fromString(new FileManager("right").read());

        System.out.println(leftFromFile);
        System.out.println(rightFromFile);

        Long startActionTime = System.currentTimeMillis();
        Optional<Matrix<Complex<Integer>>> result = new MatrixCalculator<Complex<Integer>>().withConcurrency(4)
                .basedOn((lhs, rhs) -> new Complex<>(lhs.getReal() + rhs.getReal(), lhs.getImaginary() + rhs.getImaginary()))
                .performOn(leftFromFile, rightFromFile);
        Long endActionTime = System.currentTimeMillis();
        result.ifPresent(System.out::println);
        System.out.println("Action Time: " + (endActionTime - startActionTime) + "ms");
    }

}
