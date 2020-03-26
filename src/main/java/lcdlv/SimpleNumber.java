package lcdlv;

import java.util.Objects;

public class SimpleNumber implements Operand{
    private int value;

    public SimpleNumber(int value) {

        this.value = value;
    }

    public int add(SimpleNumber op2) {
        return value + op2.calculate();
    }

    @Override
    public int calculate() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleNumber simpleNumber = (SimpleNumber) o;
        return value == simpleNumber.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public int multiply(SimpleNumber otherSimpleNumber) {
        return this.value * otherSimpleNumber.value;
    }
}
