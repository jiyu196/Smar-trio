package app;

import java.util.ArrayList;
import java.util.List;

import domain.Calculation;
import util.TrioUtils;

public class Calculator extends App {
	
	private List<Calculation> calculationHistory = new ArrayList<>();
	
	public static void main(String[] args) {
		Calculator app = new Calculator(1);
		app.run();
	}

	public Calculator(int no) {
		super(no, "계산기");
	}

	public void run() {
		System.out.println("계산기 앱을 실행합니다.");
		boolean running = true;
		while (running) {
			String choice = TrioUtils.nextLine("1: 계산 \n2: 기록 보기 \n0: 종료");

			switch (choice) {
			case "1":
				calculate();
				break;
			case "2":
				TrioUtils.readLog("calculator", "math_log.ser");
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
		while (true) {
			String operation = TrioUtils.nextLine("어떤 연산을 하시겠습니까? (+, -, *, / 입력)");
			if (!operation.matches("[+\\-*/]")) {
				System.out.println("잘못된 연산자입니다. 다시 시도하세요.");
				continue;
			}
			double number1 = TrioUtils.nextDouble("첫번째 숫자를 입력하세요:");
			double number2 = TrioUtils.nextDouble("두번째 숫자를 입력하세요:");
			
			double result = 0;
			if (number2 == 0) {
				System.out.println("0으로 나눌 수 없습니다.");
				continue;
			} 
			
			switch (operation) {
			case "+": // 덧셈
				result = number1 + number2;
				break;
			case "-": // 뺄셈
				result = number1 - number2;
				break;
			case "*": // 곱셈
				result = number1 * number2;
				break;
			case "/": // 나눗셈
				result = number1 / number2;
			}
			Calculation calc = new Calculation(number1, number2, operation, result);
            System.out.println("결과 : " + result);
            calculationHistory.add(calc);
            TrioUtils.writeLog("calculator", "math_log.ser", calculationHistory); //calculationHistory = 내용
            break;
		}
	}
}
