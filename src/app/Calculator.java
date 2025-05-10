package app;

import domain.Calculation;
import old.domain.App;
import util.TrioUtils;

public class Calculator extends App {

    public Calculator(int no) {
        super(no, "계산기");
    }

    public void run() {
        System.out.println("계산기 앱을 실행합니다.");
        boolean running = true;
        while (running) {
            String choice = TrioUtils.nextLine("1: 계산, \n2: 기록 보기, \n0: 종료");

            switch (choice) {
                case "1":
                    calculate();
                    break;
                case "2":
                    TrioUtils.readLog("calculator", "math_log.txt");
                    break;
                case "0":
                    System.out.println("종료");
                    running = false;
                    break;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }

    private void calculate() {
        String operation = TrioUtils.nextLine("어떤 연산을 하시겠습니까? (+, -, *, / 입력)");
        double number1 = TrioUtils.nextDouble("첫번째 숫자를 입력하세요:");
        double number2 = TrioUtils.nextDouble("두번째 숫자를 입력하세요:");

        double result = 0;
        boolean valid = true;

        switch (operation) {
            case "+":	// 덧셈
            	result = number1 + number2;
                break;
            case "-":	// 뺄셈
            	result = number1 - number2;
                break;
            case "*":	// 곱셈
            	result = number1 * number2;
                break;
            case "/":	// 나눗셈
                if (number2 == 0) {
                    System.out.println("0으로 나눌 수 없습니다.");
                    valid = false;
                } else {
                	result = number1 / number2;
                }
                break;
            default:
                System.out.println("잘못된 입력입니다.");
                valid = false;
        }

        if (valid) {
            Calculation calc = new Calculation(number1, number2, operation, result);
            System.out.println("결과 : " + result);
            TrioUtils.writeLog("calculator", "math_log.txt", calc.toString());
        }
    }
}
