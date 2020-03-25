package lcdlv;

import java.util.Objects;

public class Expression {

    private final int value;
    private Operand firstOperand;
    private Operand secondOperand;
    private Operator operator;

    public Expression(int expression) {

        this.value = expression;
    }

    public Expression(Operand firstOperand, Operand secondOperand, Operator operator) {
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
        this.operator = operator;

        value = 0;
    }

    public Expression add() {
        int resultAddition = firstOperand.add(secondOperand);

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
