package lcdlv;

public enum Operator {
    MULTIPLY {
        @Override
        public int calculate(Operand firstOperand, Operand secondOperand) {
            return firstOperand.calculate() * secondOperand.calculate();
        }
    }, ADD {
        @Override
        public int calculate(Operand firstOperand, Operand secondOperand) {
            return firstOperand.calculate() + secondOperand.calculate();
        }
    };

    public abstract int calculate(Operand firstOperand, Operand secondOperand);
}
