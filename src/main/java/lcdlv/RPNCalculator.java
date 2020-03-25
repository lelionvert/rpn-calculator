package lcdlv;

public class RPNCalculator {

    public Expression calculate(Expression uniqueExpression) {
        if (uniqueExpression.typeOf(Operator.ADD)) {
            return new Expression(uniqueExpression.add());
        } else if (uniqueExpression.typeOf(Operator.MULTIPLY)) {
            return new Expression(uniqueExpression.multiply());
        }
        return uniqueExpression;
    }

}
