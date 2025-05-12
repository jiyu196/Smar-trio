package domain;

import java.io.Serializable;

public class Calculation implements Serializable {
	
    private double num1, num2, result;
    private String operator, date;

    public Calculation(double num1, double num2, String operator, double result, String date) {
        this.num1 = num1;
        this.operator = operator;
        this.num2 = num2;
        this.result = result;
        this.date = date;
    }

    @Override
    public String toString() {
        return num1 + " " + operator + " " + num2 + " = " + result + " : " + date;
    }
}