package lcdlv;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static lcdlv.Operator.ADD;
import static lcdlv.Operator.MULTIPLY;
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

        Expression result = uniqueExpression.calculate();

        assertThat(result).isEqualTo(uniqueExpression);
    }

    @ParameterizedTest
    @CsvSource({"1,0,1", "1,2,3"})
    public void returnsNewExpressionWhenAddingDifferentOperands(int firstOperandValue, int secondOperandValue, int expectedResult) {
        Operand firstOperand = new Operand(firstOperandValue);
        Operand secondOperand = new Operand(secondOperandValue);

        Expression expression = new Expression(firstOperand, secondOperand, Operator.ADD);

        Expression result = expression.calculate();

        Expression expectedExpression = new Expression(expectedResult);
        assertThat(result).isEqualTo(expectedExpression);
    }

    @Test
    void returnsNewExpressionWhenAddingOneExpressionAndOneOperand() {
        // 1 3 + 1 + => (1 + 3) + 1 = 5
        Expression expectedExpression = new Expression(5);

        Expression firstOperand = new Expression(new Operand(1), new Operand(3), Operator.ADD);
        Operand secondOperand = new Operand(1);
        Expression expression = new Expression(firstOperand, new Expression(new Operand(0), secondOperand, ADD), Operator.ADD);

        Expression result = expression.calculate();

        assertThat(result).isEqualTo(expectedExpression);
    }

    // 1 1 3 + + => 1 4 + => 5
    @Test
    void returnsNewExpressionWhenAddingOneOperandAndOneExpression() {

        Expression expectedExpression = new Expression(5);

        Operand firstOperand = new Operand(1);
        Expression secondOperand = new Expression(new Operand(1), new Operand(3), Operator.ADD);
        Expression expression = new Expression(new Expression(firstOperand, new Operand(0), ADD), secondOperand, Operator.ADD);

        Expression result = expression.calculate();

        assertThat(result).isEqualTo(expectedExpression);
    }

    // 1 2 + 3 4 + + => 3 7 + => 10
    @Test
    void returnsNewExpressionWhenAddingTwoExpressions() {
        Expression expectedExpression = new Expression(10);

        Expression firstOperand = new Expression(new Operand(1), new Operand(2), Operator.ADD);
        Expression secondOperand = new Expression(new Operand(3), new Operand(4), Operator.ADD);
        Expression expression = new Expression(firstOperand, secondOperand, Operator.ADD);

        Expression result = expression.calculate();

        assertThat(result).isEqualTo(expectedExpression);
    }

    // 4 1 2 + + 3 2 + +  ===> 4 (1 2 +) + (3 2 +) + ==>
    // refactor la creation des operands et des expressions

    @Test
    void returnNewExpressionWhenAddingSeveralExpressions() {

        Expression expectedExpression = new Expression(12);
        Expression e1 = new Expression(new Operand(1), new Operand(2), ADD);
        Expression firstExpression = new Expression(new Expression(new Operand(4), new Operand(0), ADD), e1, ADD);

        Expression secondExpression = new Expression(new Operand(3), new Operand(2), ADD);
        Expression expression = new Expression(firstExpression, secondExpression, ADD);

        Expression result = expression.calculate();
        assertThat(result).isEqualTo(expectedExpression);
    }



    @ParameterizedTest
    @CsvSource({"1,1,1", "2,1,2", "1,2,2", "0,2,0", "2,0,0", "2,3,6", "3,3,9"})
    public void returnAnExpressionWhenMultiplyingTwoOperands(int firstValue, int secondValue, int expectedValue) {
        Expression expression = new Expression(new Operand(firstValue), new Operand(secondValue), Operator.MULTIPLY);
        Expression expectedExpression = new Expression(expectedValue);

        Expression result = expression.calculate();

        assertThat(result).isEqualTo(expectedExpression);
    }

    // 2 3 x 2 1 x x --> 12
    @Test
    public void returnAndExpressionWhenMultiplyingTwoExpressions(){

        Expression firstExpression = new Expression(new Operand(2), new Operand(3), MULTIPLY);
        Expression secondExpression = new Expression(new Operand(2), new Operand(1), MULTIPLY);
        Expression expression = new Expression(firstExpression, secondExpression, MULTIPLY);
        Expression expectedExpression = new Expression(12);

        Expression result = expression.calculate();

        assertThat(result).isEqualTo(expectedExpression);
    }

    // 1 3 x 4 2 x x
    @Test
    void name() {
        Expression firstExpression = new Expression(new Operand(1), new Operand(3), MULTIPLY);
        Expression secondExpression = new Expression(new Operand(4), new Operand(2), MULTIPLY);
        Expression expression = new Expression(firstExpression, secondExpression, MULTIPLY);
        Expression expectedExpression = new Expression(24);

        Expression result = expression.calculate();

        assertThat(result).isEqualTo(expectedExpression);
    }
}
