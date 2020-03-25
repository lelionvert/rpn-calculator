package lcdlv;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static lcdlv.Operator.ADD;
import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorTest {

    // EXEMPLES :
    // 1 => 1
    // 1 0 + =>  (1 + 0) = 1
    // 0 1 + =>  ( 0 + 1) = 1
    // 1 2 + => (1 + 2) = 3
    // 1 3 + 1 + => (1 + 3) + 1 = 4 1 + = 5
    // 20 5 / => (20 / 5) = 4
    // 4 2 + 3 - => (4 + 2) - 3 = 3
    // 3 5 8 * 7 + * => 3*((5*8) + 7) = 141


    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    public void returnsSameExpressionWhenCalculatingWithOnlyOneExpression(int value) {
        Expression uniqueExpression = new Expression(value);
        RPNCalculator rpnCalculator = new RPNCalculator();

        Expression result = rpnCalculator.calculate(uniqueExpression);

        assertThat(result).isEqualTo(uniqueExpression);
    }

    @ParameterizedTest
    @CsvSource({"1,0,1", "1,2,3"})
    public void returnsNewExpressionWhenAddingDifferentOperands(int firstOperandValue, int secondOperandValue, int expectedResult) {
        Operand firstOperand = new Operand(firstOperandValue);
        Operand secondOperand = new Operand(secondOperandValue);

        Expression expression = new Expression(firstOperand, secondOperand, Operator.ADD);

        RPNCalculator rpnCalculator = new RPNCalculator();

        Expression result = rpnCalculator.calculate(expression);

        Expression expectedExpression = new Expression(expectedResult);
        assertThat(result).isEqualTo(expectedExpression);
    }

    @Test
    void returnsNewExpressionWhenAddingOneExpressionAndOneOperand() {
        // 1 3 + 1 + => (1 + 3) + 1 = 5
        RPNCalculator rpnCalculator = new RPNCalculator();
        Expression expectedExpression = new Expression(5);

        Expression firstOperand = new Expression(new Operand(1), new Operand(3), Operator.ADD);
        Operand secondOperand = new Operand(1);
        Expression expression = new Expression(firstOperand, new Expression(new Operand(0), secondOperand, ADD), Operator.ADD);

        Expression result = rpnCalculator.calculate(expression);

        assertThat(result).isEqualTo(expectedExpression);
    }

    // 1 1 3 + + => 1 4 + => 5
    @Test
    void returnsNewExpressionWhenAddingOneOperandAndOneExpression() {

        RPNCalculator rpnCalculator = new RPNCalculator();
        Expression expectedExpression = new Expression(5);

        Operand firstOperand = new Operand(1);
        Expression secondOperand = new Expression(new Operand(1), new Operand(3), Operator.ADD);
        Expression expression = new Expression(new Expression(firstOperand, new Operand(0), ADD), secondOperand, Operator.ADD);

        Expression result = rpnCalculator.calculate(expression);

        assertThat(result).isEqualTo(expectedExpression);
    }

    // 1 2 + 3 4 + + => 3 7 + => 10
    @Test
    void returnsNewExpressionWhenAddingTwoExpressions(){
        RPNCalculator rpnCalculator = new RPNCalculator();
        Expression expectedExpression = new Expression(10);

        Expression firstOperand = new Expression(new Operand(1), new Operand(2), Operator.ADD);
        Expression secondOperand = new Expression(new Operand(3), new Operand(4), Operator.ADD);
        Expression expression = new Expression(firstOperand, secondOperand, Operator.ADD);

        Expression result = rpnCalculator.calculate(expression);

        assertThat(result).isEqualTo(expectedExpression);
    }

    // 4 1 2 + + 3 2 + +  ===> 4 (1 2 +) + (3 2 +) + ==>
    // refactor la creation des operands et des expressions

    @Test
    void returnNewExpressionWhenAddingSeveralExpressions() {

        Expression expectedExpression = new Expression(12);
        RPNCalculator rpnCalculator = new RPNCalculator();
        Expression e1 = new Expression(new Operand(1), new Operand(2), ADD);
        Expression firstExpression = new Expression(new Expression(new Operand(4), new Operand(0), ADD), e1, ADD);

        Expression secondExpression = new Expression(new Operand(3), new Operand(2), ADD);
        Expression expression = new Expression(firstExpression, secondExpression, ADD);

        Expression result = rpnCalculator.calculate(expression);
        assertThat(result).isEqualTo(expectedExpression);
    }


    // 1 1 x --> 1
    // 2 1 x --> 2
    // 1 2 x --> 2
    // 0 1 x --> 0
    // 1 0 x --> 0
    // 2 3 x --> 6
    // 2 3 x 2 x --> 12


    @Test
    void returnNewExpressionWhenHavingExpressionWithTwoOperandsToOne() {
        Expression expression = new Expression(new Operand(1),new Operand(1),Operator.MULTIPLY);
        Expression expectedExpression = new Expression(1);

        RPNCalculator rpnCalculator = new RPNCalculator();
        Expression result = rpnCalculator.calculate(expression);

        assertThat(result).isEqualTo(expectedExpression);
    }

    @Test
    void returnNewExpressionWhenHavingExpressionWithOperandOneAndOperandTwo(){
        Expression expression = new Expression(new Operand(2),new Operand(1),Operator.MULTIPLY);
        Expression expectedExpression = new Expression(2);

        RPNCalculator rpnCalculator = new RPNCalculator();
        Expression result = rpnCalculator.calculate(expression);

        assertThat(result).isEqualTo(expectedExpression);
    }
}
