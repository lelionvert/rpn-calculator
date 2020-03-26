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
    public void returnTheSameNumberWhenCalculatingWithOnlyOneNumber(int value) {
        Operand uniqueExpression = new SimpleNumber(value);

        int result = uniqueExpression.calculate();

        assertThat(result).isEqualTo(value);
    }

    @ParameterizedTest
    @CsvSource({"1,0,1", "1,2,3"})
    public void returnASumWhenAddingTwoSimpleNumbers(int firstOperandValue, int secondOperandValue, int expectedResult) {
        SimpleNumber firstSimpleNumber = new SimpleNumber(firstOperandValue);
        SimpleNumber secondSimpleNumber = new SimpleNumber(secondOperandValue);

        Expression expression = new Expression(firstSimpleNumber, secondSimpleNumber, Operator.ADD);

        int result = expression.calculate();

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void returnASumWhenAddingOneExpressionOfAdditionAndOneSimpleNumber() {
        // 1 3 + 1 + => (1 + 3) + 1 = 5
        int expectedResult = 5;

        Expression firstOperand = new Expression(new SimpleNumber(1), new SimpleNumber(3), Operator.ADD);
        SimpleNumber secondSimpleNumber = new SimpleNumber(1);
        Expression expression = new Expression(firstOperand, new Expression(new SimpleNumber(0), secondSimpleNumber, ADD), Operator.ADD);

        int result = expression.calculate();


        assertThat(result).isEqualTo(expectedResult);
    }

    // 1 1 3 + + => 1 4 + => 5
    @Test
    void returnASumWhenAddingOneSimpleNumberAndOneExpressionOfAddition() {

        int expectedResult = 5;

        SimpleNumber firstSimpleNumber = new SimpleNumber(1);
        Expression secondOperand = new Expression(new SimpleNumber(1), new SimpleNumber(3), Operator.ADD);
        Expression expression = new Expression(new Expression(firstSimpleNumber, new SimpleNumber(0), ADD), secondOperand, Operator.ADD);

        int result = expression.calculate();


        assertThat(result).isEqualTo(expectedResult);
    }

    // 1 2 + 3 4 + + => 3 7 + => 10
    @Test
    void returnASumWhenAddingTwoExpressionsOfAddition() {

        int expectedResult = 10;
        Expression firstOperand = new Expression(new SimpleNumber(1), new SimpleNumber(2), Operator.ADD);
        Expression secondOperand = new Expression(new SimpleNumber(3), new SimpleNumber(4), Operator.ADD);
        Expression expression = new Expression(firstOperand, secondOperand, Operator.ADD);

        int result = expression.calculate();

        assertThat(result).isEqualTo(expectedResult);
    }

    // 4 1 2 + + 3 2 + +  ===> 4 (1 2 +) + (3 2 +) + ==>


    @Test
    void returnASumWhenAddingSeveralExpressionsOfAddition() {

        int expectedResult = 12;
        Expression e1 = new Expression(new SimpleNumber(1), new SimpleNumber(2), ADD);
        Expression firstExpression = new Expression(new Expression(new SimpleNumber(4), new SimpleNumber(0), ADD), e1, ADD);

        Expression secondExpression = new Expression(new SimpleNumber(3), new SimpleNumber(2), ADD);
        Expression expression = new Expression(firstExpression, secondExpression, ADD);

        int result = expression.calculate();

        assertThat(result).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({"1,1,1", "2,1,2", "1,2,2", "0,2,0", "2,0,0", "2,3,6", "3,3,9"})
    public void returnAProductWhenMultiplyingTwoSimpleNumbers(int firstValue, int secondValue, int expectedValue) {
        Expression expression = new Expression(new SimpleNumber(firstValue), new SimpleNumber(secondValue), Operator.MULTIPLY);

        int result = expression.calculate();

        assertThat(result).isEqualTo(expectedValue);
    }


    @ParameterizedTest
    @CsvSource({"2, 3, 2, 1, 12", "1, 3, 4, 2, 24"})
    public void returnAProductWhenMultiplyingTwoExpressionsOfMultiplication(int firstOperandFirstExpression, int secondOperandFirstExpression, int firstOperandSecondExpression, int secondOperandSecondExpression, int expectedValue) {
        Expression firstExpression = new Expression(new SimpleNumber(firstOperandFirstExpression), new SimpleNumber(secondOperandFirstExpression), MULTIPLY);
        Expression secondExpression = new Expression(new SimpleNumber(firstOperandSecondExpression), new SimpleNumber(secondOperandSecondExpression), MULTIPLY);
        Expression expression = new Expression(firstExpression, secondExpression, MULTIPLY);

        int result = expression.calculate();

        assertThat(result).isEqualTo(expectedValue);
    }

    // refactor la creation des operands et des expressions
    // 2 2 2 x x ==> 8
    @Test
    void returnsAProductWhenMultiplyingASimpleNumberAndAnExpressionOfMultiplication() {
        int expectedValue = 8;

        Operand firstExpression = new SimpleNumber(2);
        Expression secondExpression = new Expression(new SimpleNumber(2), new SimpleNumber(2), MULTIPLY);
        Expression expression = new Expression(firstExpression, secondExpression, MULTIPLY);

        int result = expression.calculate();

        assertThat(result).isEqualTo(expectedValue);
    }

    // 2 2 x 2 x ==> 8
    @Test
    void returnAProductWhenMultiplyingAnExpressionOfMultiplicationAndASimpleNumber() {
        Expression first = new Expression(new SimpleNumber(2), new SimpleNumber(2), MULTIPLY);
        Operand second = new SimpleNumber(2);
        Expression expression = new Expression(first, second, MULTIPLY);
        int expectedValue = 8;

        int result = expression.calculate();

        assertThat(result).isEqualTo(expectedValue);
    }
}
