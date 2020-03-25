package lcdlv;

public class RPNCalculator {

    public Expression calculate(Expression uniqueExpression) {
        if (uniqueExpression.typeOf(Operator.ADD)){
            return new Expression(uniqueExpression.add());
        }
            return uniqueExpression;
    }

}
