package lcdlv;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorTest {

    // EXEMPLES :
    // 1 => 1
    // 1 0 + =>  (1 + 0) = 1
    // 0 1 + =>  ( 0 + 1) = 1
    // 1 2 + => (1 + 2) = 3
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

}
