package main;

import manager.FileManager;
import manager.Matrix;
import manager.MatrixCalculator;

import java.io.IOException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */


public class Main {

    public static void main(String[] args) throws IOException {
        Long startTime = System.currentTimeMillis();

        // Step#1: Generate Matrices
        Matrix left = new Matrix().generate(100, 100, 100);
        Matrix right = new Matrix().generate(100, 100, 100);

        // Step#2: Save Matrices to Files
        new FileManager("left").write(left.toString());
        new FileManager("right").write(right.toString());

        // Step#3: Read Matrices from Files
        Matrix lhs = new Matrix().fromString(new FileManager("left").read());
        Matrix rhs = new Matrix().fromString(new FileManager("right").read());

        // Step#4: Calculate Sum and Multiply
        MatrixCalculator calculator = new MatrixCalculator(4);
        // System.out.println("LHS" + System.lineSeparator() + lhs);
        // System.out.println("LHS" + System.lineSeparator() + rhs);

        // Sum of LHS and RHS
        Long startSumTime = System.currentTimeMillis();
        Matrix sum = calculator.sum(lhs, rhs).orElse(new Matrix());
        Long endSumTime = System.currentTimeMillis();
        // System.out.println("+" + System.lineSeparator() + sum);

        // Multiplication of LHS with RHS
        Long startMultiplicationTime = System.currentTimeMillis();
        Matrix multiplication = calculator.multiply(lhs, rhs).orElse(new Matrix());
        Long endMultiplicationTime = System.currentTimeMillis();
        // System.out.println("*" + System.lineSeparator() + multiplication);

        // Statistics
        System.out.println("Total Time: " + (endMultiplicationTime - startTime) + "ms");
        System.out.println("Sum Time: " + (endSumTime - startSumTime) + "ms");
        System.out.println("Multiplication Time: " + (endMultiplicationTime - startMultiplicationTime) + "ms");


    }

}
