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




}
