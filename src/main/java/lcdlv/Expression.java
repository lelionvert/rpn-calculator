package lcdlv;

import java.util.Objects;

public class Expression implements Operand {

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
        this(firstExpression.calculate().firstOperand, secondExpression.calculate().firstOperand, operator);
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;

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

    public Expression calculate() {

        int result = getResult();

        return new Expression(result);
    }

    private int getResult() {
        int result;
        if (typeOf(Operator.ADD)) {

            result = firstOperand.add(secondOperand);
        } else if (typeOf(Operator.MULTIPLY)) {
            result = firstOperand.multiply(secondOperand);
        } else {
            result = firstOperand.calculate();
        }
        return result;
    }
}
