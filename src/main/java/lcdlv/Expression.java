package lcdlv;

import java.util.Objects;

public class Expression {

    private SimpleNumber firstOperand;
    private SimpleNumber secondOperand;
    private Operator operator;
    private Expression firstExpression;
    private Expression secondExpression;


    public Expression(int value) {
        this.firstOperand = new SimpleNumber(value);
    }

    public Expression(SimpleNumber firstOperand, SimpleNumber secondOperand, Operator operator) {
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
        this.operator = operator;
    }

    public Expression(Expression firstExpression, Expression secondExpression, Operator operator) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
        this.operator = operator;
        someKindOfMethod();
    }

    private void someKindOfMethod() {
        this.firstOperand = this.firstExpression.calculate().firstOperand;
        this.secondOperand = this.secondExpression.calculate().firstOperand;
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
