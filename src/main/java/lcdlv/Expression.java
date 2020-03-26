package lcdlv;

import java.util.Objects;

public class Expression {

    private Operand firstOperand;
    private Operand secondOperand;
    private Operator operator;


    public Expression(int value) {
        this.firstOperand = new Operand(value);
    }

    public Expression(Operand firstOperand, Operand secondOperand, Operator operator) {
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
        this.operator = operator;
    }

    public Expression(Expression firstExpression, Expression secondExpression, Operator operator) {
        if (operator.equals(Operator.MULTIPLY)){
            this.firstOperand = new Operand(firstExpression.multiply());
            this.secondOperand = new Operand(secondExpression.multiply());
            this.operator = operator;
        } else {
            this.firstOperand = new Operand(firstExpression.add());
            this.secondOperand = new Operand(secondExpression.add());
            this.operator = operator;
        }
    }

    public int add() {
        return firstOperand.add(secondOperand);
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

    public int multiply() {
        return firstOperand.multiply(secondOperand);
    }

    public Expression calculate() {
        if (typeOf(Operator.ADD)) {
            return new Expression(add());
        } else if (typeOf(Operator.MULTIPLY)) {
            return new Expression(multiply());
        }
        return this;
    }
}
