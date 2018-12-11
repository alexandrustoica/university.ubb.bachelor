package domain;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class Complex<T> {

    private T real;
    private T imaginary;

    public Complex(final T real, final T imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public T getReal() {
        return real;
    }

    public T getImaginary() {
        return imaginary;
    }

    @Override
    public String toString() {
        return "[" + real.toString() + " " + imaginary.toString() + "]";
    }
}
