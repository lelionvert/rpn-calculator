package lcdlv;

public class Operand {
    private int value;

    public Operand(int value) {

        this.value = value;
    }

    public int add(Operand op2) {
        return value + op2.value;
    }
}
