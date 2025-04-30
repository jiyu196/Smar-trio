package domain;

import java.io.Serializable;
import Utils.TrioUtils;

public class CalculatorApp extends App implements Serializable {

	public CalculatorApp() {
		super("계산기");
	}

    public void run() {
        System.out.println("계산기 앱을 실행합니다.");

        while (true) {
            String math = TrioUtils.nextLine("어떤 연산을 하시겠습니까? (+, -, *, / 입력)");
            
            double num1 = TrioUtils.nextDouble("첫번째 숫자를 입력하세요:");
            double num2 = TrioUtils.nextDouble("두번째 숫자를 입력하세요:");
            double result = 0;
            boolean valid = true;

            switch (math) {
                case "+":
                    result = num1 + num2; break;
                case "-":
                    result = num1 - num2; break;
                case "*":
                    result = num1 * num2; break;
                case "/":
                    if (num2 == 0) {
                        System.out.println("0으로 나눌 수 없습니다.");
                        valid = false;
                    } 
                    else {
                        result = num1 / num2;
                    } break;
                default:
                	System.out.println("잘못된 입력입니다.");
                    valid = false;
            }

            if (valid) {
                System.out.println("결과 : " + result);
            }

            if (!TrioUtils.nextConfirm("계속하시겠습니까? (y / n)")) {
                System.out.println("계산기를 종료합니다.");
                break;
            }
        }
    }

	public String getName() {
		return name;
	}
}


