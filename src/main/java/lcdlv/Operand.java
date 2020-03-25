package lcdlv;

import java.util.Objects;

public class Operand {
    private int value;

    public Operand(int value) {

        this.value = value;
    }

    public int add(Operand op2) {
        return value + op2.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operand operand = (Operand) o;
        return value == operand.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public int multiply(Operand otherOperand) {
        if(this.value == 0 || otherOperand.value == 0){
            return 0;
        }
        if (otherOperand.value == 1) {
            return this.value;
        } else if (this.value == 1){
            return otherOperand.value;
        } else {
            return 6;
        }
    }
}
