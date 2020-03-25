package lcdlv;

import java.util.Objects;

public class Expression {

    private final int value;
    private Operand op1;
    private Operand op2;
    private Operator operator;

    public Expression(int expression) {

        this.value = expression;
    }

    public Expression(Operand op1, Operand op2, Operator operator) {
        this.op1 = op1;
        this.op2 = op2;
        this.operator = operator;

        value = 0;
    }

    public Expression add() {
        int resultAddition = op1.add(op2);

        return new Expression(resultAddition);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expression that = (Expression) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public boolean typeOf(Operator add) {
        return add.equals(this.operator);
    }

}
