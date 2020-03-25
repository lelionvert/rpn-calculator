package lcdlv;

import java.util.Objects;

public class Expression {

    private Operand firstOperand;
    private Operand secondOperand;
    private Operator operator;

    public Expression(Operand firstOperand) {
        this.firstOperand = firstOperand;
    }

    public Expression(Operand firstOperand, Operand secondOperand, Operator operator) {
        this(firstOperand);
        this.secondOperand = secondOperand;
        this.operator = operator;
    }

    public Expression(Expression expression, Operand secondOperand, Operator add) {
        this(new Operand(expression.firstOperand.add(expression.secondOperand)));
        this.secondOperand = secondOperand;
        operator = add;
    }

    public Expression add() {
        int resultAddition = firstOperand.add(secondOperand);

        return new Expression(new Operand(resultAddition));
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
}
