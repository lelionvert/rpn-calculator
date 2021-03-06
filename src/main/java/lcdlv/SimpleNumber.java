package lcdlv;

import java.util.Objects;

public class SimpleNumber implements Operand{
    private int value;

    public SimpleNumber(int value) {

        this.value = value;
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
}
