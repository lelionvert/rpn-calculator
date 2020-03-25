package lcdlv;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorTest {

    @Test
    public void envTest() {
        assertThat(true).isTrue();
    }

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



    @Test
    void returnsAnExpressionOfOneWhenCalculatingAdditionBetweenExpressionOneAndZero() {
        Operand op1 = new Operand(1);
        Operand op2 = new Operand(0);
        Expression expression = new Expression(op1, op2, Operator.ADD);

        RPNCalculator rpn = new RPNCalculator();

        Expression result = rpn.calculate(expression);

        Expression expectedExpression = new Expression(1);
        assertThat(result).isEqualTo(expectedExpression);
    }
}
