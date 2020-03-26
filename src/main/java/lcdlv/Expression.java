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

        int result = secondExpression.calculate();
        int result1 = firstExpression.calculate();

        this.firstOperand = new Expression(result1).firstOperand;
        this.secondOperand = new Expression(result).firstOperand;
        this.operator = operator;
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

    @Override
    public int calculate() {
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
