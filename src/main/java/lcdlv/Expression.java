package lcdlv;

import java.util.Objects;

public class Expression implements Operand {

    private Operand firstOperand;
    private Operand secondOperand;
    private Operator operator;

    public Expression(Operand firstOperand, Operand secondOperand, Operator operator) {
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
        this.operator = operator;
    }

    public boolean typeOf(Operator add) {
        return add.equals(this.operator);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expression that = (Expression) o;
        return Objects.equals(firstOperand, that.firstOperand) &&
                Objects.equals(secondOperand, that.secondOperand) &&
                operator == that.operator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstOperand, secondOperand, operator);
    }


    @Override
    public String toString() {
        return "Expression{" +
                "firstOperand=" + firstOperand +
                ", secondOperand=" + secondOperand +
                ", operator=" + operator +
                '}';
    }

    @Override
    public int calculate() {
        int result;
        if (typeOf(Operator.ADD)) {
            result = firstOperand.calculate() + secondOperand.calculate();
        } else if (typeOf(Operator.MULTIPLY)) {
            result = firstOperand.calculate() * secondOperand.calculate();
        } else {
            result = firstOperand.calculate();
        }
        return result;
    }
}
