package domain;

import java.io.Serializable;

public class Calculation implements Serializable {
    private final double num1;
    private final double num2;
    private final String operator;
    private final double result;

    public Calculation(double num1, double num2, String operator, double result) {
        this.num1 = num1;
        this.num2 = num2;
        this.operator = operator;
        this.result = result;
    }

    @Override
    public String toString() {
        return num1 + " " + operator + " " + num2 + " = " + result;
    }
}