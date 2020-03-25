package lcdlv;

import java.util.Objects;

import static lcdlv.Operator.*;

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

    public Expression(Expression expression, Operand secondOperand, Operator operator) {
        this.firstOperand = new Operand(expression.firstOperand.add(expression.secondOperand));
        this.secondOperand = secondOperand;
        this.operator = operator;
    }

    public Expression(Operand firstOperand, Expression expression, Operator operator) {
        Expression e = new Expression(firstOperand, new Operand(0), ADD);

        this.firstOperand = new Operand(e.firstOperand.add(e.secondOperand));
        this.secondOperand = new Operand(expression.firstOperand.add(expression.secondOperand));
        this.operator = operator;

    }

    public Expression(Expression firstExpression, Expression secondExpression, Operator operator) {
        this.firstOperand =  new Operand(firstExpression.firstOperand.add(firstExpression.secondOperand));
        this.secondOperand = new Operand(secondExpression.firstOperand.add(secondExpression.secondOperand));
        this.operator = operator;
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
}
