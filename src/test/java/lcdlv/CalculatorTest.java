package lcdlv;

import org.junit.jupiter.api.Test;

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


    @Test
    void returnExpressionOfOneWhenCalculateWithOnlyTheExpressionOfOne() {
        Expression uniqueExpression = new Expression(1);
        RPNCalculator rpnCalculator = new RPNCalculator();

        Expression result = rpnCalculator.calculate(uniqueExpression);

        assertThat(result).isEqualTo(uniqueExpression);
    }
}
